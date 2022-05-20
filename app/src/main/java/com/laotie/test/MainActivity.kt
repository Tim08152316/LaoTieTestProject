package com.laotie.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.laotie.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()
        binding.sampleText.text = stringFromJNI()+"test"
    }

    fun testQuoteClick(view: View) {
        testQuote()
    }

    fun testWaitLockClick(view: View) {
        testWaitLock()
    }

    /**
     * A native method that is implemented by the 'Test' native library,
     * which is packaged with this application.
     */
    private external fun stringFromJNI(): String
    private external fun testQuote()
    private external fun testWaitLock()

    companion object {
        // Used to load the 'Test' library on application startup.
        init {
            System.loadLibrary("Test")
        }
    }
}
