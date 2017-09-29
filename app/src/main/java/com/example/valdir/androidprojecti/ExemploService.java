package com.example.valdir.androidprojecti;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by VALDIR on 28/09/2017.
 */

public class ExemploService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

   @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //será imprimido no log informação
       Log.i("SERVICE", "Serviço iniciado");//será imprimido no log serviço iniciado
       horas();
        return super.onStartCommand(intent, flags, startId);//código que será exec quando o service for chamado
   }

    //método horas para imprimir quando o serviço for chamado
    public void horas(){
        for (int i =0; i<5; i++){
            try {
                Thread.sleep(1000);
                Date data = new Date();
                Log.i("SERVICE", "Hora:"+data.toString());// será imprimido a hora durante 5 segundos
            } catch (InterruptedException e) {
                Log.e("SERVICE", "Error: " + e.getMessage());
            }
        }
    }
    //depois de finalizado o serviço chama o metodo onDestroy
    public  void onDestroy(){
        super.onDestroy();
    Log.i("SERVICE","Serviço encerrado!");
    }

    //após serviço pronto, devemos registrar no manifest
}
