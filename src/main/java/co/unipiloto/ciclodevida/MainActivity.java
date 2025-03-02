package co.unipiloto.ciclodevida;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int segundos = 0;
    private boolean corriendo;
    private TextView vueltas;

    private int numeroVueltas = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    public void onClickIniciar(View view) {
        corriendo = true;
    }

    public void onClickParar(View view) {
        corriendo = false;
    }

    public void onClickReiniciar(View view) {
        corriendo = false;
        segundos = 0;
    }

    public void onClickVuelta(View view) {
        if(numeroVueltas !=5){
            vueltas = findViewById(R.id.textVuelta);
            int horas = segundos / 3600;
            int minutes = (segundos % 3600) / 60;
            int segs = segundos % 60;
            String time = String.format(Locale.getDefault(), "%d:%02d:%02d", horas, minutes, segs);
            if (numeroVueltas ==0){
                String salida ="Vuelta" + ++numeroVueltas + ": " + time;
                vueltas.setText(salida);
            }else{
                String salida =  vueltas.getText() + "\n" + "Vuelta" + ++numeroVueltas + ": " + time;
                vueltas.setText(salida);
            }
        }
    }

    private void runTimer(){
        final TextView timeView= findViewById(R.id.textCrono);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int horas = segundos / 3600;
                int minutes = (segundos % 3600) / 60;
                int segs = segundos % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", horas, minutes, segs);
                timeView.setText(time);
                if (corriendo) {
                    segundos++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


}