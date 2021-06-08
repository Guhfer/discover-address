package com.br.challenge.discoveraddress.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    private FileUtils() {
    }

    public static String getFile(String file) throws IOException {
        return IOUtils.resourceToString(file, StandardCharsets.ISO_8859_1);
    }
}
