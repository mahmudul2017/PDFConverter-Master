package com.example.pdfconverter_master

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.tejpratapsingh.pdfcreator.activity.PDFCreatorActivity
import com.tejpratapsingh.pdfcreator.utils.PDFUtil.PDFUtilListener
import com.tejpratapsingh.pdfcreator.views.PDFBody
import com.tejpratapsingh.pdfcreator.views.PDFHeaderView
import com.tejpratapsingh.pdfcreator.views.PDFTableView
import com.tejpratapsingh.pdfcreator.views.PDFTableView.PDFTableRowView
import com.tejpratapsingh.pdfcreator.views.basic.PDFHorizontalView
import com.tejpratapsingh.pdfcreator.views.basic.PDFImageView
import com.tejpratapsingh.pdfcreator.views.basic.PDFLineSeparatorView
import com.tejpratapsingh.pdfcreator.views.basic.PDFTextView
import java.io.File
import java.lang.String
import java.util.*

class PdfCreatorActivity : PDFCreatorActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        createPDF("test", object : PDFUtilListener {
            override fun pdfGenerationSuccess(savedPDFFile: File) {
                Toast.makeText(this@PdfCreatorActivity, "PDF Created", Toast.LENGTH_SHORT).show()
            }

            override fun pdfGenerationFailure(exception: Exception) {
                Toast.makeText(this@PdfCreatorActivity, "PDF NOT Created", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onNextClicked(savedPDFFile: File?) {
        /*val pdfUri: Uri = Uri.fromFile(savedPDFFile)

        val intentPdfViewer = Intent(this@PdfCreatorActivity, PdfViewerActivity::class.java)
        intentPdfViewer.putExtra(PdfViewerActivity.PDF_FILE_URI, pdfUri)

        startActivity(intentPdfViewer)*/
    }

    override fun getHeaderView(pageIndex: Int): PDFHeaderView {
        val headerView = PDFHeaderView(applicationContext)

        val pdfTextViewPage = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
        pdfTextViewPage.setText(String.format(Locale.getDefault(), "Page: %d", pageIndex + 1))
        pdfTextViewPage.setLayout(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 0F
            )
        )
        pdfTextViewPage.view.gravity = Gravity.CENTER_HORIZONTAL

        headerView.addView(pdfTextViewPage)

        val horizontalView = PDFHorizontalView(applicationContext)

        val pdfTextView =
            PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.HEADER)
        val word = SpannableString("INVOICE")
        word.setSpan(
            ForegroundColorSpan(Color.DKGRAY),
            0,
            word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        pdfTextView.text = word
        pdfTextView.setLayout(
            LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1F
            )
        )
        pdfTextView.view.gravity = Gravity.CENTER_VERTICAL
        pdfTextView.view.setTypeface(pdfTextView.view.typeface, Typeface.BOLD)

        horizontalView.addView(pdfTextView)

        val imageView = PDFImageView(applicationContext)
        val imageLayoutParam = LinearLayout.LayoutParams(60, 60, 0F)
        imageView.setImageScale(ImageView.ScaleType.CENTER_INSIDE)
        imageView.setImageResource(R.mipmap.ic_launcher)
        imageLayoutParam.setMargins(0, 0, 10, 0)
        imageView.setLayout(imageLayoutParam)

        horizontalView.addView(imageView)

        headerView.addView(horizontalView)

        val lineSeparatorView1 =
            PDFLineSeparatorView(applicationContext).setBackgroundColor(Color.WHITE)
        headerView.addView(lineSeparatorView1)

        return headerView
    }

    override fun getBodyViews(): PDFBody {
        val pdfBody = PDFBody()

        val pdfCompanyNameView =
            PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.H3)
        pdfCompanyNameView.setText("Company Name")
        pdfBody.addView(pdfCompanyNameView)
        val lineSeparatorView1 =
            PDFLineSeparatorView(applicationContext).setBackgroundColor(Color.WHITE)
        pdfBody.addView(lineSeparatorView1)
        val pdfAddressView =
            PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
        pdfAddressView.setText("Address Line 1\nCity, State - 123456")
        pdfBody.addView(pdfAddressView)

        val textInTable = arrayOf("1", "2", "3", "4")

        val lineSeparatorView2 =
            PDFLineSeparatorView(applicationContext).setBackgroundColor(Color.WHITE)
        pdfBody.addView(lineSeparatorView2)
        val pdfTableTitleView =
            PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
        pdfTableTitleView.setText("Table Example")
        pdfBody.addView(pdfTableTitleView)

        val tableHeader = PDFTableRowView(applicationContext)
        for (s in textInTable) {
            val pdfTextView =
                PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
            pdfTextView.setText("Header Title: $s")
            tableHeader.addToRow(pdfTextView)
        }
        val tableRowView1 = PDFTableRowView(applicationContext)
        for (s in textInTable) {
            val pdfTextView =
                PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
            pdfTextView.setText("Row 1 : $s")
            tableRowView1.addToRow(pdfTextView)
        }

        val tableView =
            PDFTableView(applicationContext, tableHeader, tableRowView1)
        for (i in 0..39) {
            // Create 10 rows
            val tableRowView = PDFTableRowView(applicationContext)
            for (s in textInTable) {
                val pdfTextView =
                    PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
                pdfTextView.setText("Row " + (i + 2) + ": " + s)
                tableRowView.addToRow(pdfTextView)
            }
            tableView.addRow(tableRowView)
        }
        pdfBody.addView(tableView)

        val lineSeparatorView3 =
            PDFLineSeparatorView(applicationContext).setBackgroundColor(Color.BLACK)
        pdfBody.addView(lineSeparatorView3)

        val pdfIconLicenseView =
            PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.H3)
        val icon8Link =
            Html.fromHtml("Icon from <a href='https://icons8.com'>https://icons8.com</a>")
        pdfIconLicenseView.view.text = icon8Link
        pdfBody.addView(pdfIconLicenseView)

        return pdfBody
    }
}