package com.example.valdir.androidprojecti;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnIniciarService, btnIniciarBindService;
    private TextView textViewStatus;
    private ExemploBindService exemploBindService;
    private boolean statusBind = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btnIniciarService = (Button) findViewById(R.id.btnIniciarService);
        btnIniciarService.setOnClickListener(this);

        textViewStatus = (TextView) findViewById(R.id.textViewStatus);

        btnIniciarBindService = (Button) findViewById(R.id.btnIniciarBindService);
        btnIniciarBindService.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intentBindService = new Intent (this, ExemploBindService.class);
        bindService(intentBindService, serviceConnection, BIND_AUTO_CREATE);

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //criar varivel que irá acessar a classe bindservice
            ExemploBindService.LocalBinder localBinder = (ExemploBindService.LocalBinder)service;
            exemploBindService = localBinder.getService();
            statusBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            statusBind = false;
        }
    };


    @Override
    protected void onStop() {
        super.onStop();
        if (statusBind);{
            unbindService(serviceConnection);
            statusBind = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIniciarService:
                Intent intentService = new Intent(this, ExemploService.class);
                if (isRunningService(intentService)){
                    stopService(intentService);
                    textViewStatus.setText("Serviço parado!");
                }else {
                    startService(intentService);
                    textViewStatus.setText("Serviço Executado!");
                }
            break;
            case R.id.btnIniciarBindService:
                if(statusBind) {
                    String hora = exemploBindService.gethoras();
                    textViewStatus.setText("Data e hora do serviço: " + hora);

                }
                break;
        }


    }
    private boolean isRunningService(Intent intent){

        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: manager.getRunningServices(Integer.MAX_VALUE)){
            if (service.service.getClassName().equals("com.example.valdir.androidprojecti.ExemploService"))
                return true;
        }
        return false;

    }
}
