package com.example.looperhandlermessagequeue

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.looperhandlermessagequeue.ui.theme.LooperHandlerMessageQueueTheme

class MainActivity : ComponentActivity() {
    val myViewModel by viewModels<MyViewModel>()
    private var counter = 1
    private lateinit var workerThread: HandcraftedWorkerThread
    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val myMsg = msg.obj as String
            myViewModel.setMsg(myMsg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        myThread()
        workerThread = HandcraftedWorkerThread()
        //workerThread = LooperWorkerThread()
        //workerThread = HandlerWorkerThread("WorkerThread")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LooperHandlerMessageQueueTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        onButtonClick = { workThreadExecute() }, viewModel = myViewModel

                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        workerThread.quit()
    }

    private fun createMessage(text: String): Message {
        return Message.obtain().apply { obj = text }
    }

    private fun workThreadExecute() {
        workerThread.execute {
            Thread.sleep(3000)
            handler.sendMessage(createMessage("Task $counter completed"))
            counter++
        }
    }
}

private fun myThread() {
    val myThread = (object : Thread() {
        override fun run() {
            val mainThreadLooper = Looper.getMainLooper() // --> Looper of the main/UI thread
            val mainThreadHandler = Handler(mainThreadLooper) // --> Get handler to main thread
            val messageToSendToMainThread =
                Message.obtain() // --> Create a message to send to UI thread
            messageToSendToMainThread.obj = 123 // 123 -> actual msg value
            mainThreadHandler.sendMessage(messageToSendToMainThread)
        }
    })
    myThread.start()
}
