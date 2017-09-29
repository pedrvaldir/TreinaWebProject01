package com.example.valdir.androidprojecti;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ExemploReceiver exemploReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnEnviarEstatico).setOnClickListener(this);
        findViewById(R.id.btnEnviarDinamico).setOnClickListener(this);

        //instanciando variavel receiver
        exemploReceiver = new ExemploReceiver();

        registerReceiver(exemploReceiver, new IntentFilter("BROADCAST_DINAMICO"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEnviarEstatico:
                sendBroadcast(new Intent("BROADCAST_ESTATICO"));
                break;

            case R.id.btnEnviarDinamico:
                sendBroadcast(new Intent("BROADCAST_DINAMICO"));
                break;
        }
        Toast.makeText(this, "Intent Enviada", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exemploReceiver);
    }
}
