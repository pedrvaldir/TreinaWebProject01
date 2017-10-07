package com.example.valdir.androidprojecti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.renderscript.Script;
import android.util.Log;

/**
 * Created by VALDIR on 26/09/2017.
 */

public class ExemploReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //toda vez que interceptar uma msg de broadcast que foi interceptada vai chamar esse metodo
        String acao = intent.getAction();

        if (acao.equals("BROADCAST_DINAMICO")){
            Log.i("RECEIVER", "Broadcast - Dinamico");
        }else if (acao.equals("BROADCAST_ESTATICO")) {
            Log.i("RECEIVER", "Broadcast-Estático");
        }else if (acao.equals("android.intent.action.BOOT_COMPLETED")) {
            Intent i = new Intent(context, MainActivity.class);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            //MENSAGEM GITHUB
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
