package br.ufg.normas.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.UrlValidator;


public class Validacao {

    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^[_a-z0-9]+(\\.[_a-z0-9]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$");
        return pattern.matcher(email).matches();
    }

    public static boolean validarSenha(String senha) {
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}$");
        return (p.matcher(senha).matches());
    }

    public static boolean validarURL(String url)
    {
        Pattern p = Pattern.compile("((https?:\\/\\/)|(ftp:\\/\\/)|(^))([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+([a-zA-Z]{2,9})(:\\d{1,4})?([-\\w\\/#~:.?+=&amp;%@~]*)");
        return (p.matcher(url).matches());
    }

    public static boolean ExisteURL(URL url) throws IOException
    {
        // We want to check the current URL
        HttpURLConnection.setFollowRedirects(false);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // We don't need to get data
        httpURLConnection.setRequestMethod("HEAD");

        // Some websites don't like programmatic access so pretend to be a browser
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
        int responseCode = httpURLConnection.getResponseCode();

        // We only accept response code 200
        return responseCode == HttpURLConnection.HTTP_OK;
    }
}

