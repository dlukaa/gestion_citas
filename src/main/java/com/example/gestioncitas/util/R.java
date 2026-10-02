package com.example.gestioncitas.util;

import java.io.InputStream;
import java.net.URL;

public class R {
    public static URL getUI(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui/" + name);
    }
}