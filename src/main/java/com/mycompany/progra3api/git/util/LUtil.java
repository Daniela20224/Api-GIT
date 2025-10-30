/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progra3api.git.util;

import com.mycompany.progra3api.git.domain.repositorio;
import java.util.*;
import java.util.stream.Collectors;


public class LUtil {
   
    public static Map<String, Double> calcularPorcentajesLenguajes(List<repositorio> repos) {
        if (repos == null || repos.isEmpty()) {
            return Collections.emptyMap();
        }

        // Agrupa los lenguajes y cuenta ocurrencias
        Map<String, Long> conteoLenguajes = repos.stream()
                .map(repositorio::getLanguage)
                .filter(lang -> lang != null && !lang.isBlank())
                .collect(Collectors.groupingBy(lang -> lang, Collectors.counting()));

        long total = conteoLenguajes.values().stream().mapToLong(Long::longValue).sum();
        if (total == 0) return Collections.emptyMap();

        // Calcula el porcentaje correspondiente
        Map<String, Double> porcentajes = new LinkedHashMap<>();
        conteoLenguajes.forEach((lenguaje, cantidad) -> {
            double porcentaje = (cantidad * 100.0) / total;
            porcentajes.put(lenguaje, porcentaje);
        });

        return porcentajes;
    }
}


