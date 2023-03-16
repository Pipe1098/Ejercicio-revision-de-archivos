package com.example.procesador;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.poi.ss.usermodel.*;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProcesarArchivoController {
    @Autowired
    private ValidarArchivoService validarArchivoService;

    @PostMapping("/procesar-archivo")
    public ResponseEntity<String> procesarArchivo(@RequestParam("archivo") MultipartFile archivo) throws IOException {
        String extension = FilenameUtils.getExtension(archivo.getOriginalFilename());
        if (extension.equalsIgnoreCase("csv")) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(archivo.getInputStream()));
            // Operaciones para archivo CSV
            // ...
        } else if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
            Workbook workbook = WorkbookFactory.create(archivo.getInputStream());
            // Operaciones para archivo Excel
            // ...
        } else {
            return ResponseEntity.badRequest().body("Formato de archivo no soportado");
        }
        // Comunicación con el servicio de validación
        // ...
        return ResponseEntity.ok("Archivo procesado con éxito");
    }
}


