package com.example.douanetemporelle;

public class Utils {

    // Matricule mte3na
    public static String genererCle(String matricule, String cleDeBase) {
        return matricule + "_" + cleDeBase;
    }
}