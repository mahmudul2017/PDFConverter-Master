package com.example.pdfconverter_master

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private val RECORD_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        val edtPdfName = edt_pdf_name.text.toString()

        btn_showPdf.setOnClickListener {
            viewPdf(edtPdfName +".pdf", "mypdf/")
        }

        /*btn_createPdf.setOnClickListener {
            val pdfBuilder = PDFBuilder()
                .setPageWidth(1300)
                .setDirectoryPath("/")
                .setPageHeight(1800)
                .setEmail("dave@live.com")
                .setEmailSubject("Subject test")
                .setPdfFileName("testName")
                .setEmailText("email body")

            val generator = PDFGenerator(this, pdfBuilder)
            generator.savePdf(R.layout.activity_pdf_test)
        }*/

        /* btn_createPdf.setOnClickListener {
             //tv_valueChange.text = "Pdf-12"
             //createPdfView()
             //createPdfViewApply()
             startActivity(Intent(this, PdfCreatorActivity::class.java))
         }

     private fun createPdfView() {
         PdfGenerator.getBuilder()
             .setContext(this@MainActivity)
             .fromLayoutXMLSource()
             .fromLayoutXML(R.layout.activity_main)
             .setDefaultPageSize(PdfGenerator.PageSize.A4)
             .setFileName("Test-PDF")
             .setFolderName("FolderA/FolderB/FolderC")
             .openPDFafterGeneration(true)
             .build(object : PdfGeneratorListener() {
                 override fun onFailure(failureResponse: FailureResponse) {
                     super.onFailure(failureResponse)
                     Toast.makeText(this@MainActivity, "PDF Failed.....", Toast.LENGTH_LONG).show()
                 }

                 override fun showLog(log: String) {
                     super.showLog(log)

                 }

                 override fun onSuccess(response: SuccessResponse) {
                     super.onSuccess(response)
                     Toast.makeText(this@MainActivity, "PDF Created.....", Toast.LENGTH_LONG).show()
                 }
           })
     }

     private fun createPdfViewApply() {
         val pdfDocument = PdfDocument()
         val paint = Paint()

         val pageInfo = PdfDocument.PageInfo.Builder(250, 400, 1).create()
         val startPage = pdfDocument.startPage(pageInfo)

         val canvas = startPage.canvas

         canvas.drawText("Welcomt to PDF", 40f, 50f, paint)
         pdfDocument.finishPage(startPage)

         val file = File(Environment.getExternalStorageDirectory(), "/FirstPdf.pdf")

         try {
             pdfDocument.writeTo(FileOutputStream(file))
         } catch (e: IOException) {
             e.printStackTrace()
         }
         pdfDocument.close()
     }

     private fun setupPermissions() {
         val permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

         if (permission != PackageManager.PERMISSION_GRANTED) {
             Log.d("TAG", "Permission to record denied")
         }
     }

     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<String>,
         grantResults: IntArray
     ) {
         when (requestCode) {
             RECORD_REQUEST_CODE -> {
                 if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                     Log.d("TAG-D", "Permission has been denied by user")
                 } else {
                     Log.d("TAG-G", "Permission has been granted by user")
                 }
             }
         } */

        btn_createPdf.setOnClickListener {
            createPdf(edittext.text.toString(), edt_pdf_name.text.toString())

            /*tv_valueChange.text = "12"
            PdfGenerator.getBuilder()
                .setContext(this@MainActivity)
                .fromLayoutXMLSource()
                .fromLayoutXML(R.layout.activity_main)
                .setDefaultPageSize(PdfGenerator.PageSize.A4)
                .setFileName("Test-PDF")
                .setFolderName("FolderA/FolderB/FolderC")
                .openPDFafterGeneration(true)
                .build(object : PdfGeneratorListener() {
                    override fun onFailure(failureResponse: FailureResponse) {
                        super.onFailure(failureResponse)
                        Toast.makeText(this@MainActivity, "PDF Failed.....", Toast.LENGTH_LONG).show()
                    }

                    override fun showLog(log: String) {
                        super.showLog(log)
                    }

                    override fun onSuccess(response: SuccessResponse) {
                        super.onSuccess(response)
                        Toast.makeText(this@MainActivity, "PDF Created.....", Toast.LENGTH_LONG).show()
                 }
            })*/
        }
    }

    private fun createPdf(sometext: String, pdfName: String) {
        // create a new document
        val document = PdfDocument()
        // crate a page description
        var pageInfo = PageInfo.Builder(300, 600, 1).create()
        // start a page
        var page = document.startPage(pageInfo)
        var canvas: Canvas = page.canvas
        var paint = Paint()
        // paint.color = Color.RED
        // canvas.drawCircle(50F, 50F, 30F, paint)

        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())

        paint.color = Color.BLACK
        canvas.drawText("Date: " + currentDate, 20F, 50F, paint)
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC))
        canvas.drawText(sometext, 180F, 50F, paint)
        // canvas.draw
        // finish the page
        document.finishPage(page)
        // draw text on the graphics object of the page
        // Create Page 2
        pageInfo = PageInfo.Builder(300, 600, 2).create()
        page = document.startPage(pageInfo)
        canvas = page.canvas
        paint = Paint()
        // paint.color = Color.BLUE
        // canvas.drawCircle(100F, 100F, 100F, paint)
        document.finishPage(page)
        // write the document content
        val directory_path = Environment.getExternalStorageDirectory().path + "/mypdf/"
        val file = File(directory_path)
        if (!file.exists()) {
            file.mkdirs()
        }
        val targetPdf = directory_path + pdfName + ".pdf"
        val filePath = File(targetPdf)
        try {
            document.writeTo(FileOutputStream(filePath))
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            Log.e("main", "error $e")
            Toast.makeText(this, "Something wrong: $e", Toast.LENGTH_LONG).show()
        }
        // close the document
        document.close()
    }

    // Method for opening a pdf file
    private fun viewPdf(file: String, directory: String) {
        val pdfFile = File(
            Environment.getExternalStorageDirectory()
                .toString() + "/" + directory + "/" + file
        )
        val path: Uri = Uri.fromFile(pdfFile)

        // Setting the intent for pdf reader
        val pdfIntent = Intent(Intent.ACTION_VIEW)
        pdfIntent.setDataAndType(path, "application/pdf")
        pdfIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        try {
            startActivity(pdfIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Can't read pdf file", Toast.LENGTH_SHORT).show()
        }
    }
}