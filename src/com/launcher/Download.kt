package com.launcher

import java.io.*
import java.net.*
import java.util.*

/**
 * Class taken from:
 * https://www.java-tips.org/java-se-tips-100019/15-javax-swing/1391-how-to-create-a-download-manager-in-java.html
 * Slightly modified by Charlie <https://github.com/charlieSplittstoser> to download to the cache directory
 */

// This class downloads a file from a URL.
internal class Download (private val url: URL) : Observable(), Runnable {

    // Get this download's size.
    var size: Int = 0
        private set // size of download in bytes

    private var downloaded: Int = 0 // number of bytes downloaded

    var status: Int = 0
        private set // current status of download

    // Get this download's progress.
    val progress: Float
        get() = downloaded.toFloat() / size * 100

    init {
        size = -1
        downloaded = 0
        status = DOWNLOADING

        download()
    }

    // Get this download's URL.
    fun getUrl(): String {
        return url.toString()
    }

    // Pause this download.
    fun pause() {
        status = PAUSED
        stateChanged()
    }

    // Resume this download.
    fun resume() {
        status = DOWNLOADING
        stateChanged()
        download()
    }

    // Cancel this download.
    fun cancel() {
        status = CANCELLED
        stateChanged()
    }

    // Mark this download as having an error.
    private fun error() {
        status = ERROR
        stateChanged()
    }

    // Start or resume downloading.
    private fun download() {
        val thread = Thread(this)
        thread.start()
    }

    // Get file name portion of URL.
    private fun getFileName(url: URL): String {
        val fileName = url.file
        return fileName.substring(fileName.lastIndexOf('/') + 1)
    }

    // Download file.
    override fun run() {
        var file: RandomAccessFile? = null
        var stream: InputStream? = null

        try {
            // Open connection to URL.
            val connection = url.openConnection() as HttpURLConnection

            // Specify what portion of file to download.
            connection.setRequestProperty("Range",
                    "bytes=$downloaded-")

            // Connect to server.
            connection.connect()

            // Make sure response code is in the 200 range.
            if (connection.responseCode / 100 != 2) {
                error()
            }

            // Check for valid content length.
            val contentLength = connection.contentLength
            if (contentLength < 1) {
                error()
            }

            /* Set the size for this download if it hasn't been already set. */
            if (size == -1) {
                size = contentLength
                stateChanged()
            }

            // Open file and seek to the end of it.
            file = RandomAccessFile(Configuration.CLIENT_FOLDER + getFileName(url), "rw")
            file.seek(downloaded.toLong())

            stream = connection.inputStream
            while (status == DOWNLOADING) {

                /* Size buffer according to how much of the file is left to download. */
                val buffer: ByteArray
                if (size - downloaded > MAX_BUFFER_SIZE) {
                    buffer = ByteArray(MAX_BUFFER_SIZE)
                } else {
                    buffer = ByteArray(size - downloaded)
                }

                // Read from server into buffer.
                val read = stream!!.read(buffer)
                if (read == -1)
                    break

                // Write buffer to file.
                file.write(buffer, 0, read)
                downloaded += read
                stateChanged()
            }

            /* Change status to complete if this point was reached because downloading has finished. */
            if (status == DOWNLOADING) {
                status = COMPLETE
                stateChanged()
            }
        } catch (e: Exception) {
            error()
        } finally {
            // Close file.
            if (file != null) {
                try {
                    file.close()
                } catch (e: Exception) { }
            }

            // Close connection to server.
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: Exception) { }
            }
        }
    }

    // Notify observers that this download's status has changed.
    private fun stateChanged() {
        setChanged()
        notifyObservers()
    }

    companion object {

        // Max size of download buffer.
        private val MAX_BUFFER_SIZE = 1024

        // These are the status names.
        val STATUSES = arrayOf("Downloading", "Paused", "Complete", "Cancelled", "Error")

        // These are the status codes.
        val DOWNLOADING = 0
        val PAUSED = 1
        val COMPLETE = 2
        val CANCELLED = 3
        val ERROR = 4
    }
}
