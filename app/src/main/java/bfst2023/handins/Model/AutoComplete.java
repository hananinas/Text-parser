package bfst2023.handins.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.common.base.Utf8;

public class AutoComplete {

    private ArrayList<String> posibleSuggestion;

    public static void main(String[] args) throws IOException {
        AutoComplete auto = new AutoComplete();
        for (String string : auto.posibleSuggestion) {
            System.out.println(string);
        }

    }

    public AutoComplete() throws IOException {
        posibleSuggestion = new ArrayList<>();

        Scanner sc = new Scanner(
                new FileReader("/assets/citynames.txt", StandardCharsets.UTF_8));

        while (sc.hasNextLine()) {
            posibleSuggestion.add(sc.nextLine());
        }
    }

    public ArrayList<String> getPosibleSuggestion() {
        return posibleSuggestion;
    }
}