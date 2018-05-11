package br.ufg.normas.service;

import java.util.regex.Pattern;

public class Validacao {

    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^[_a-z0-9]+(\\.[_a-z0-9]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$");
        return pattern.matcher(email).matches();
    }

    public static boolean validarSenha(String senha) {
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}$");
        return (p.matcher(senha).matches());
    }


}

