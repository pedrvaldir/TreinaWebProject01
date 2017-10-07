package com.example.valdir.androidprojecti;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by VALDIR on 07/10/2017.
 */

public class ExemploBindService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //irá interagir com o servico
        IBinder iBinder = new LocalBinder();
        return iBinder;

    }


    //herdara a classe bainder
    public class LocalBinder extends Binder{
        ExemploBindService getService(){
            return ExemploBindService.this;
        }
    }

    public String gethoras(){
        Date data = new Date();
        return data.toString();
    }
}
