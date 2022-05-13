package com.owisalhammoud.gpastudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    NotificationManager nm;
    EditText[] nh;
    EditText[] m;
    EditText[] mo;
    EditText n_h_o, GPAa_o;
    TextView TV_GPAs, TV_GPAa;
    int[] numh;
    int s = 0, sum_h_o;
    double[] mark;
    double[] old_mark;
    double GPAs;
    double GPAa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Notific();
        xml2java();

    }

    private void xml2java() {
        nh = new EditText[]{
                (EditText) findViewById(R.id.editTextNumberDecimal8),
                (EditText) findViewById(R.id.editTextNumberDecimal11),
                (EditText) findViewById(R.id.editTextNumberDecimal14),
                (EditText) findViewById(R.id.editTextNumberDecimal17),
                (EditText) findViewById(R.id.editTextNumberDecimal20),
                (EditText) findViewById(R.id.editTextNumberDecimal23),
                (EditText) findViewById(R.id.editTextNumberDecimal26),
                (EditText) findViewById(R.id.editTextNumberDecimal29),
                (EditText) findViewById(R.id.editTextNumberDecimal32),
                (EditText) findViewById(R.id.editTextNumberDecimal35),

        };
        m = new EditText[]{
                (EditText) findViewById(R.id.editTextNumberDecimal7),
                (EditText) findViewById(R.id.editTextNumberDecimal10),
                (EditText) findViewById(R.id.editTextNumberDecimal13),
                (EditText) findViewById(R.id.editTextNumberDecimal16),
                (EditText) findViewById(R.id.editTextNumberDecimal19),
                (EditText) findViewById(R.id.editTextNumberDecimal22),
                (EditText) findViewById(R.id.editTextNumberDecimal25),
                (EditText) findViewById(R.id.editTextNumberDecimal28),
                (EditText) findViewById(R.id.editTextNumberDecimal31),
                (EditText) findViewById(R.id.editTextNumberDecimal34),
        };

        mo = new EditText[]{
                (EditText) findViewById(R.id.editTextNumberDecimal6),
                (EditText) findViewById(R.id.editTextNumberDecimal9),
                (EditText) findViewById(R.id.editTextNumberDecimal12),
                (EditText) findViewById(R.id.editTextNumberDecimal15),
                (EditText) findViewById(R.id.editTextNumberDecimal18),
                (EditText) findViewById(R.id.editTextNumberDecimal21),
                (EditText) findViewById(R.id.editTextNumberDecimal24),
                (EditText) findViewById(R.id.editTextNumberDecimal27),
                (EditText) findViewById(R.id.editTextNumberDecimal30),
                (EditText) findViewById(R.id.editTextNumberDecimal33),
        };

        for (int i = 0; i < 10; i++)
            mo[i].setText("");
        GPAa_o = (EditText) findViewById(R.id.editTextNumberDecimal);
        n_h_o = (EditText) findViewById(R.id.editTextNumberDecimal2);
        TV_GPAs = (TextView) findViewById(R.id.textView9);
        TV_GPAa = (TextView) findViewById(R.id.textView10);


    }

    private void Notific() {
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder nb;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(new NotificationChannel("1", "Calculator", nm.IMPORTANCE_DEFAULT));
            nb = new NotificationCompat.Builder(this, "1");
        } else {
            nb = new NotificationCompat.Builder(this);

        }

        nb.setSmallIcon(R.drawable.i)
                .setContentTitle("Calculation GPA for UOK student")
                .setContentText("It's programmed by : Owis Al_hammoud")
                /*  .setContentIntent(
                          PendingIntent.getActivity(
                                  this,
                                  0,
                                  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/aouees")),
                                  PendingIntent.FLAG_UPDATE_CURRENT))*/
                .addAction(
                        R.drawable.icon, "Whatsapp",
                        PendingIntent.getActivity(
                                this,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+963936973634&text=Hello Owis , I have a message for you : ")),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(
                        R.drawable.icon, "Facebook",
                        PendingIntent.getActivity(
                                this,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/aouees/")),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(
                        R.drawable.icon, "Instagram",
                        PendingIntent.getActivity(
                                this,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/aouees_ah/")),
                                PendingIntent.FLAG_UPDATE_CURRENT));

        nm.notify(1, nb.build());
    }

    private void fill() {
        numh = new int[10];
        mark = new double[10];
        old_mark = new double[10];
        for (int i = 0; i < 10; i++) {
            //&& !mo[i].getText().toString().equals("-2")
            if (!nh[i].getText().toString().equals("") && !m[i].getText().toString().equals("")) {
                numh[i] = Integer.parseInt(nh[i].getText().toString());
                mark[i] = Double.parseDouble(m[i].getText().toString());
                if (!mo[i].getText().toString().equals(""))
                    old_mark[i] = Double.parseDouble((mo[i].getText().toString()));
                else
                    old_mark[i] = -2;
            } else {
                numh[i] = -1;
                mark[i] = -1;
                old_mark[i] = -2;
            }
        }
    }

    private double point(int n, double m) {
        double p;
        if (m >= 98) p = n * 4;
        else if (m >= 95) p = n * 3.75;
        else if (m >= 90) p = n * 3.5;
        else if (m >= 85) p = n * 3.25;
        else if (m >= 80) p = n * 3;
        else if (m >= 75) p = n * 2.75;
        else if (m >= 70) p = n * 2.5;
        else if (m >= 65) p = n * 2.25;
        else if (m >= 60) p = n * 2;
        else if (m >= 55) p = n * 1.75;
        else if (m >= 50) p = n * 1.5;
        else p = 0;
        return p;
    }


    public void Rest(View view) {
        try {
            for (int i = 0; i < 10; i++) {
                nh[i].setText("");
                m[i].setText("");
                mo[i].setText("");
            }
            TV_GPAa.setText("");
            TV_GPAs.setText("");
            GPAa_o.setText("");
            n_h_o.setText("");
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public void GPAa(View view) {
        try {


            GPAs(view);
            double gpa = Double.parseDouble(GPAa_o.getText().toString());
            int num = Integer.parseInt(n_h_o.getText().toString());
            double p_o = gpa * num;
            double p_n = GPAs * s;
            double p_f = 0;
            sum_h_o = 0;
            for (int i = 0; i < 10; i++) {
                if (old_mark[i] != -2 && (mark[i] != -1 && old_mark[i] != -1))
                    sum_h_o += numh[i];
                p_f += point(numh[i], old_mark[i]);
            }

            GPAa = (p_o + p_n - p_f) / ((num + s) - sum_h_o);
            if (GPAa >= 2)
                TV_GPAa.setTextColor(Color.parseColor("#25EC0F"));
            else
                TV_GPAa.setTextColor(Color.parseColor("#F11010"));

            TV_GPAa.setText(new DecimalFormat("##.##").format(GPAa));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public void GPAs(View view) {
        try {
            fill();
            double sum = 0;
            double m;
            s = 0;
            for (int i = 0; i < 10; i++) {
                //&& (old_mark[i] != -2)
                if ((numh[i] != -1) && (mark[i] != -1)) {
                    m = Math.max(mark[i], old_mark[i]);
                    sum += point(numh[i], m);
                    s += numh[i];
                }
            }
            GPAs = sum / s;
            if (GPAs >= 2)
                TV_GPAs.setTextColor(Color.parseColor("#25EC0F"));
            else
                TV_GPAs.setTextColor(Color.parseColor("#F11010"));
            TV_GPAs.setText(new DecimalFormat("##.##").format(GPAs));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
