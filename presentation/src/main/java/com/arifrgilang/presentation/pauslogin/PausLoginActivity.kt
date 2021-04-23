package com.arifrgilang.presentation.pauslogin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.app.ActivityCompat.startActivityForResult
import com.arifrgilang.presentation.BuildConfig
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.databinding.ActivityPausLoginBinding
import com.arifrgilang.presentation.util.Constant
import com.arifrgilang.presentation.util.Constant.LOGIN_PAUS_REQUEST
import com.arifrgilang.presentation.util.Constant.LOGIN_PAUS_RESULT
import com.arifrgilang.presentation.util.base.BaseBindingActivity
import com.arifrgilang.presentation.util.gone
import com.arifrgilang.presentation.util.visible
import timber.log.Timber
import java.util.*

class PausLoginActivity : BaseBindingActivity<ActivityPausLoginBinding>() {
    override fun contentView(): Int = R.layout.activity_paus_login

    override fun setupData(savedInstanceState: Bundle?) {
        val state = UUID.randomUUID().toString()
        binding.webView.loadUrl(Constant.pausOAuthUri(state).toString())
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupView() {
        binding.title = "Login PAuS"
        binding.btnBack.setOnClickListener {
            finish()
        }
        with(binding.webView){
            settings.apply { javaScriptEnabled = true }
            webViewClient = generateWebViewClient()
        }
    }

    private fun generateWebViewClient() = object: WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            request?.let{
                val url = it.url.toString()
                if(url.startsWith(BuildConfig.REDIRECT_URI)){
                    val code = it.url.getQueryParameter("code")
                    setResult(
                        Activity.RESULT_OK,
                        Intent().apply { putExtra(LOGIN_PAUS_RESULT, code) }
                    )
                    finish()
                }
            }
            return super.shouldOverrideUrlLoading(view, request)
        }

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
                KeyEvent.KEYCODE_BACK -> {
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