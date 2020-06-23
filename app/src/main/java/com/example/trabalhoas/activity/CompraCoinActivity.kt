package com.example.trabalhoas.activity

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trabalhoas.Coin
import com.example.trabalhoas.R
import com.example.trabalhoas.httpCoin
import kotlinx.android.synthetic.main.activity_compra_coin.*

class CompraCoinActivity : AppCompatActivity() {

    var apelido : String ="BTC"
    private var asyncTask: CoinTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra_coin)

        apelido = intent.getStringExtra("apelido")
        carregarDados()

    }

    fun carregarDados(){
        if(asyncTask==null){
            if (httpCoin.hasConnetcion(this)){
                if(asyncTask?.status!=AsyncTask.Status.RUNNING){
                    asyncTask = CoinTask()
                    asyncTask?.execute()
                }
            }
        }
    }

    inner class CoinTask: AsyncTask<Void, Void, Coin?>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void?): Coin? {
            return httpCoin.loadCoin(apelido)
        }

        private fun update(result: Coin?){
            if(result != null){
                txtValor.text = result.last.toString()
            }
        }

    }
}