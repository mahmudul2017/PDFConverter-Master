package com.example.pdfconverter_master

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gkemon.XMLtoPDF.PdfGenerator
import com.gkemon.XMLtoPDF.PdfGeneratorListener
import com.gkemon.XMLtoPDF.model.FailureResponse
import com.gkemon.XMLtoPDF.model.SuccessResponse
import com.hendrix.pdfmyxml.viewRenderer.AbstractViewRenderer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //tv_valueChange.text = "Pdf-12"

        btn_createPdf.setOnClickListener {
            createPdfView()
        }

        /*btn_createPdf.setOnClickListener {
            PdfGenerator.getBuilder()
                .setContext(this@MainActivity)
                .fromLayoutXMLSource()
                .fromLayoutXML(R.layout.activity_pdf)
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
        }*/
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
                    tv_valueChange.text = "Pdf-12"
                    Toast.makeText(this@MainActivity, "PDF Created.....", Toast.LENGTH_LONG).show()
                }
          })
     }
}