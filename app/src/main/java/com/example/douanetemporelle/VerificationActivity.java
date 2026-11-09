package com.example.douanetemporelle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VerificationActivity extends AppCompatActivity {

    // ndeclariw les variable
    private TextView tvNomVerif;
    private TextView tvNaissVerif;
    private TextView tvDestVerif;
    private TextView tvMotifVerif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // n5thou les information mn ficher XML w n7otouhom f variable 
        tvNomVerif = findViewById(R.id.tv_nom_verif);
        tvNaissVerif = findViewById(R.id.tv_naiss_verif);
        tvDestVerif = findViewById(R.id.tv_dest_verif);
        tvMotifVerif = findViewById(R.id.tv_motif_verif);


        Intent intent = getIntent();

        // Lezem ykoun l matricule nafssou fl MainActivity
        String matricule = "7982";


        String nomRecu = intent.getStringExtra(Utils.genererCle(matricule, "NOM"));

        // lezem ykoun par defaut l valeur 0
        int naissRecue = intent.getIntExtra(Utils.genererCle(matricule, "NAISSANCE"), 0);
        int destRecue = intent.getIntExtra(Utils.genererCle(matricule, "DESTINATION"), 0);

        String motifRecu = intent.getStringExtra(Utils.genererCle(matricule, "MOTIF"));

        // permet d'afficher les information des voyageurs sur l'ecran
        tvNomVerif.setText(nomRecu);
        tvNaissVerif.setText(String.valueOf(naissRecue));
        tvDestVerif.setText(String.valueOf(destRecue));
        tvMotifVerif.setText(motifRecu);
    }
}
