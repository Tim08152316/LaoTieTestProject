package com.laotie.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.laotie.test.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()
        binding.sampleText.text = stringFromJNI()+"test"
        binding.sampleText.text = stringFromJNI()+"change on the remote main 001"
        binding.sampleText.text = stringFromJNI()+"change on the remote main 002"
        binding.sampleText.text = stringFromJNI()+"change on the remote main 003"
        
        binding.sampleText.text = stringFromJNI()+"change on the local test branch ----01"
        binding.sampleText.text = stringFromJNI()+"change on the local test branch ----02"

        binding.sampleText.text = stringFromJNI()+"change on the local test branch ----03"
        binding.sampleText.text = stringFromJNI()+"change on the local test branch ----04"

        GlobalScope.launch(context = Dispatchers.IO) {
            //延时一秒
            delay(1000)
            Log.e("MainActivity","launch");
        }
        //主动休眠两秒，防止 JVM 过快退出
        Log.e("MainActivity","start");
        Thread.sleep(2000)
        Log.e("MainActivity","end");

//        val time = measureTimeMillis {
        GlobalScope.launch  {
                val asyncA = async {
                    delay(3000)
                    1
                }
                val asyncB = async {
                    delay(4000)
                    2
                }
                Log.e(MainActivity.javaClass.name,"msg:"+asyncA.await() + asyncB.await())
            }
//        }

//        Thread.sleep(60000)

        Log.e(MainActivity.javaClass.name, "time:")
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
