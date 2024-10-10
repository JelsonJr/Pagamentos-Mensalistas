package br.com.uepg.pagamentosmensalistastb.erros;

import java.util.regex.Pattern;

public class GetErrorName {

    public static String getName(String name) {
        name = name.replace("Exception", "");

        var pattern = Pattern.compile("([a-z])([A-Z])");
        var matcher = pattern.matcher(name);
        name = matcher.replaceAll("$1 $2");

        return name;
    }
}
