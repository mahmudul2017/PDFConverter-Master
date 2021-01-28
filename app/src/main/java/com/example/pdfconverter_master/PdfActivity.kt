package com.example.pdfconverter_master

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hendrix.pdfmyxml.PdfDocument
import com.hendrix.pdfmyxml.viewRenderer.AbstractViewRenderer
import kotlinx.android.synthetic.main.activity_pdf.view.*
import java.io.File

class PdfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

       /* val page: AbstractViewRenderer = object : AbstractViewRenderer(this@PdfActivity, R.layout.activity_pdf) {
            private var _text: String? = null

            fun setText(text: String?) {
                _text = text
            }

            override fun initView(view: View) {
                view.tv_hello.text = _text
            }
        }
        page.isReuseBitmap = true

        val doc = PdfDocument(this)

        doc.addPage(page)
        doc.setRenderWidth(2115)
        doc.setRenderHeight(1500)
        doc.setOrientation(PdfDocument.A4_MODE.LANDSCAPE)
        doc.setProgressTitle(R.string.app_loading)
        doc.setProgressMessage(R.string.app_name)
        doc.setFileName("test")
        doc.setSaveDirectory(this.getExternalFilesDir(null))
        doc.setInflateOnMainThread(false)
        doc.setListener(object : PdfDocument.Callback {
            override fun onComplete(file: File?) {
                Log.i(PdfDocument.TAG_PDF_MY_XML, "Complete")
            }

            override fun onError(e: Exception?) {
                Log.i(PdfDocument.TAG_PDF_MY_XML, "Error")
            }
        })

        doc.createPdf(this) */
    }
}