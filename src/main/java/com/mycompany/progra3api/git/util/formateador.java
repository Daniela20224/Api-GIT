/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progra3api.git.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class formateador {
    

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** Devuelve un texto seguro, reemplazando null o vacío por "-" */
    public static String safe(String value) {
        return (value == null || value.isBlank()) ? "N/A" : value;
    }

    /** Formatea una fecha ISO (ej. 2025-10-29T11:30:00Z) a un formato legible */
    public static String formatDate(String isoDate) {
        try {
            if (isoDate == null || isoDate.isBlank()) return "N/A";
            OffsetDateTime odt = OffsetDateTime.parse(isoDate);
            return odt.format(OUTPUT_FORMAT);
        } catch (Exception e) {
            return "N/A";
        }
    }
}



