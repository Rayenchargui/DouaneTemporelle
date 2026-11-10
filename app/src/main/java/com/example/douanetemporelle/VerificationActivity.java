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

        // Lezem ykoun l matricule nafssou fl MainActivity
        String matricule = "7982";


        String nomRecu = intent.getStringExtra(Utils.genererCle(matricule, "NOM"));

        // lezem ykoun par defaut l valeur 0
        int naissRecue = intent.getIntExtra(Utils.genererCle(matricule, "NAISSANCE"), 0);
        int destRecue = intent.getIntExtra(Utils.genererCle(matricule, "DESTINATION"), 0);

        String motifRecu = intent.getStringExtra(Utils.genererCle(matricule, "MOTIF"));

        // Calcul de l'âge temporel
        int ageTemporel = destRecue - naissRecue;

        // permet d'afficher les information des voyageurs sur l'ecran
        tvNomVerif.setText(nomRecu);
        tvAgeVerif.setText(String.valueOf(ageTemporel));
        tvMotifVerif.setText(motifRecu);

        // Respect de la contrainte 3 (Désactivation si motif et nombre premier)
        if (motifRecu != null && motifRecu.equals("Modification du passé") && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // Utilisation de setOnClickListener comme vu dans le cours (Chapitre 4)
        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VerificationActivity.this, ResultActivity.class);
                i.putExtra(Utils.genererCle(matricule, "DECISION"), "APPROUVÉ");
                startActivity(i);
            }
        });

        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VerificationActivity.this, ResultActivity.class);
                i.putExtra(Utils.genererCle(matricule, "DECISION"), "REFUSÉ");
                startActivity(i);
            }
        });
    }

    // Algorithme  pour le test du nombre premier
    private boolean estPremier(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
} //AA