package com.example.procesador;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

@Service
public class ValidarArchivoService {

    private final static String EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    private final static LocalDate MIN_FECHA_NACIMIENTO = LocalDate.of(1980, 1, 1);
    private final static Set<String> JOB_TITLES_VALIDOS = Set.of(
            "Haematologist", "Phytotherapist", "Building surveyor",
            "Insurance account manager", "Educational psychologist"
    );

    public boolean validarArchivo(Archivo archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo.getRuta()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                if (campos.length != 3) {
                    return false; // El archivo no tiene la estructura correcta
                }
                if (!validarEmail(campos[0])) {
                    return false; // El email no es válido
                }
                if (!validarFechaNacimiento(campos[1])) {
                    return false; // La fecha de nacimiento no es válida
                }
                if (!validarJobTitle(campos[2])) {
                    return false; // El título del trabajo no es válido
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean validarEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    private boolean validarFechaNacimiento(String fechaNacimiento) {
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
        return fechaNac.isAfter(MIN_FECHA_NACIMIENTO);
    }

    private boolean validarJobTitle(String jobTitle) {
        return JOB_TITLES_VALIDOS.contains(jobTitle);
    }
}

