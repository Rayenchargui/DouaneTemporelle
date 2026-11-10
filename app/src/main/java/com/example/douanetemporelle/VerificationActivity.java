package com.example.douanetemporelle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerificationActivity extends AppCompatActivity {

    // ndeclariw les variable
    private TextView tvNomVerif;
    private TextView tvAgeVerif;
    private TextView tvMotifVerif;
    private Button btnApprouver;
    private Button btnRefuser;

    // Lezem ykoun l matricule nafssou fl MainActivity
    private String matricule = "7982";

    // l'esm lezem yet3ada lel ResultActivity
    private String nomRecu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // n5thou les information mn ficher XML w n7otouhom f variable
        tvNomVerif = findViewById(R.id.tv_nom_rl);
        tvAgeVerif = findViewById(R.id.tv_age_rl);
        tvMotifVerif = findViewById(R.id.tv_motif_rl);
        btnApprouver = findViewById(R.id.btn_approuver_rl);
        btnRefuser = findViewById(R.id.btn_refuser_rl);


        Intent intent = getIntent();


        nomRecu = intent.getStringExtra(Utils.genererCle(matricule, "NOM"));

        // lezem ykoun par defaut l valeur 0
        int naissRecue = intent.getIntExtra(Utils.genererCle(matricule, "NAISSANCE"), 0);
        int destRecue = intent.getIntExtra(Utils.genererCle(matricule, "DESTINATION"), 0);

        String motifRecu = intent.getStringExtra(Utils.genererCle(matricule, "MOTIF"));

        // n7sbou l 3omr zamani (l far9 bin sanet l wasl w sanet l miled)
        int ageTemporel = destRecue - naissRecue;

        // twari information des voyageurs sur l'ecran
        tvNomVerif.setText(nomRecu);
        tvAgeVerif.setText("" + ageTemporel);
        tvMotifVerif.setText(motifRecu);

        // contrainte 3 : ken l motif "Modification du passé" w l far9 3adad awwali, l bouton Approuver yet3attal
        if (motifRecu != null && motifRecu.equals("Modification du passé") && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }
    }

    // hedhi l methode eli tet3ayat 3liha mel XML b android:onClick
    public void approuver(View v) {
        Intent i = new Intent(VerificationActivity.this, ResultActivity.class);
        i.putExtra(Utils.genererCle(matricule, "DECISION"), true);
        i.putExtra(Utils.genererCle(matricule, "NOM"), nomRecu);
        startActivity(i);
        finish();
    }

    public void refuser(View v) {
        Intent i = new Intent(VerificationActivity.this, ResultActivity.class);
        i.putExtra(Utils.genererCle(matricule, "DECISION"), false);
        i.putExtra(Utils.genererCle(matricule, "NOM"), nomRecu);
        startActivity(i);
        finish();
    }

    // algorithme lel test mta3 l 3adad l awwali
    private boolean estPremier(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}