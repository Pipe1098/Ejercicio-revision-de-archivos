package com.example.procesador;

public class Archivo {

    private String nombreArchivo;
    private String rutaArchivo;

    public Archivo(String nombreArchivo, String rutaArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
    }

    public String getRuta() {
    return rutaArchivo;
    }
}
