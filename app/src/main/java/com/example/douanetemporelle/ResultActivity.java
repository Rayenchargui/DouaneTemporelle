package com.example.douanetemporelle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    // ndeclariw les variable
    private TextView tvMessage;
    private View rlResultat;

    // Lezem ykoun l matricule nafssou fl activities lo5rin
    private String matricule = "7982";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // n5thou les element mn ficher XML w n7otouhom f variable
        tvMessage = findViewById(R.id.tv_message_rl);
        rlResultat = findViewById(R.id.rl_resultat_rl);

        Intent intent = getIntent();

        // l9rar (true = approuve) w l'esm eli jew mel activity 2
        boolean approuve = intent.getBooleanExtra(Utils.genererCle(matricule, "DECISION"), false);
        String nom = intent.getStringExtra(Utils.genererCle(matricule, "NOM"));

        // l'fond w l message ye5tlfou 7asb l9rar
        if (approuve) {
            rlResultat.setBackgroundResource(R.color.bouton_vert);
            tvMessage.setText("Bon voyage " + nom);
        } else {
            rlResultat.setBackgroundResource(R.color.bouton_rouge);
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }
    }

    // ki tenzel 3ala "Nouveau voyageur" nrj3ou lel MainActivity
    public void nouveauVoyageur(View v) {
        Intent i = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
