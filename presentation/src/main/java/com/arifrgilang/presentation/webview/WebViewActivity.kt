package com.arifrgilang.presentation.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.databinding.ActivityWebViewBinding
import com.arifrgilang.presentation.util.base.BaseBindingActivity
import com.arifrgilang.presentation.util.gone
import com.arifrgilang.presentation.util.logFirebase
import com.arifrgilang.presentation.util.visible

class WebViewActivity : BaseBindingActivity<ActivityWebViewBinding>() {

    companion object {
        const val TITLE = "WEB_VIEW_TITLE"
        const val URL = "WEB_VIEW_URL"
        fun startActivity(context: Context, title: String, url: String) {
            context.apply {
                startActivity(
                    Intent(this, WebViewActivity::class.java).apply {
                        putExtra(TITLE, title)
                        putExtra(URL, url)
                    }
                )
            }
        }
    }

    private var url = ""
    override fun contentView(): Int = R.layout.activity_web_view

    override fun setupData(savedInstanceState: Bundle?) {
        intent?.let{ intentValue ->
            with(intentValue){
                getStringExtra(URL)?.let { url = it }
                getStringExtra(TITLE)?.let { binding.title = it }
            }
        }
        binding.webView.loadUrl(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupView() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        with(binding.webView){
            settings.apply { javaScriptEnabled = true }
            webViewClient = generateWebViewClient()
        }
    }

    private fun generateWebViewClient() = object: WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progressBar.visible()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressBar.gone()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN){
            when(keyCode){
                KEYCODE_BACK -> {
                    if(binding.webView.canGoBack())
                        binding.webView.goBack()
                    else
                        finish()
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}