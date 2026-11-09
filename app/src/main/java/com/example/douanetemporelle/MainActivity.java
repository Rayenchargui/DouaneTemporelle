package com.example.douanetemporelle;


// LES IMPORTS
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    // Déclaration des variables
    private EditText etNom;
    private EditText etNaiss;
    private EditText etDest;
    private RadioGroup rgMotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison des variables avec le XML
        etNom = findViewById(R.id.et_nom_ab);
        etNaiss = findViewById(R.id.et_naissance_ab);
        etDest = findViewById(R.id.et_destination_ab);
        rgMotif = findViewById(R.id.rg_motif_ab);
    }

    // Méthode ki tenzel 3ala "Inspecter Profil"
    public void inspecterProfil(View v) {
        // Récupération des informations
        String chNom = etNom.getText().toString().trim();
        String chNaiss = etNaiss.getText().toString().trim();
        String chDest = etDest.getText().toString().trim();

        // Tthabet est ce que vide wala le
        if (chNom.isEmpty() || chNaiss.isEmpty() || chDest.isEmpty()) {

            // Alerte lel l'utilisateur
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Attention !");
            alertDialog.setMessage("Veuillez saisir toutes les informations !");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        } else {

            int naiss;
            int dest;

            try {
                naiss = Integer.parseInt(chNaiss);
                dest = Integer.parseInt(chDest);
            } catch (NumberFormatException e) {
                etNaiss.setError("Année invalide");
                etDest.setError("Année invalide");
                return;
            }

            // el naissance max 2024 w destination awka ihot li i7eb
            if (naiss <= 0 || naiss > 2024) {
                etNaiss.setError("Année invalide");
            } else if (dest <= 0 || dest > 3000) {
                etDest.setError("Année invalide");
            } else {

                int selectedId = rgMotif.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    return;
                }

                RadioButton rbSelected = findViewById(selectedId);
                String motif = rbSelected.getText().toString();

                Intent i = new Intent(MainActivity.this, VerificationActivity.class);

                // Matricule mte3na
                String matricule = "7982";

                // Génération automatique des clés avec la classe Utils
                i.putExtra(Utils.genererCle(matricule, "NOM"), chNom);
                i.putExtra(Utils.genererCle(matricule, "NAISSANCE"), naiss);
                i.putExtra(Utils.genererCle(matricule, "DESTINATION"), dest);
                i.putExtra(Utils.genererCle(matricule, "MOTIF"), motif);

                startActivity(i);
            }
        }
    }
}