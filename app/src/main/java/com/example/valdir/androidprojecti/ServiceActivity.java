package com.example.valdir.androidprojecti;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnIniciarService;
    private TextView textViewStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btnIniciarService = (Button) findViewById(R.id.btnIniciarService);
        btnIniciarService.setOnClickListener(this);

        textViewStatus = (TextView) findViewById(R.id.textViewStatus);

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
