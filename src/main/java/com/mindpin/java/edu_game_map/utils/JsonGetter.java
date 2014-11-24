package com.mindpin.java.edu_game_map.utils;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by dd on 14-11-24.
 */
public class JsonGetter {
    public static String from_file(File file) throws FileNotFoundException {
        return new Scanner(file).useDelimiter("\\Z").next();
    }

    public static String from_url(String url) {
        return HttpRequest.get(url).body();
    }
}
