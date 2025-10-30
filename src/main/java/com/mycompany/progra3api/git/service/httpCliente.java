/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progra3api.git.service;


import java.net.http.httpCliente;
import java.time.Duration;


public final class httpCliente {

    private httpCliente() {} 

    public static httpCliente create() {
        return httpCliente.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
}
