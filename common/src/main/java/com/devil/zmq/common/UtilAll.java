package com.devil.zmq.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import io.netty.util.internal.PlatformDependent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class UtilAll {

    /**
     * Free direct-buffer's memory actively.
     *
     * @param buffer buffer Direct buffer to free.
     */
    public static void cleanBuffer(final ByteBuffer buffer) {
        if (null == buffer) {
            return;
        }
        if (!buffer.isDirect()) {
            return;
        }
        PlatformDependent.freeDirectBuffer(buffer);
    }

    public static String readFromFile(String path) {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            StringBuffer sb = new StringBuffer();
            while (in.ready()) {
                sb.append(in.readLine());
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.createNewFile();
        }
    }

    public static void writeToFile(String filename, String json) throws IOException {
        String bakFile = filename + ".bak";
        String prevContent = readFromFile(filename);
        if (!prevContent.isEmpty()) {
            string2FileNotSafe(prevContent, bakFile);
        }
        string2FileNotSafe(json, filename);
    }

    public static void string2FileNotSafe(final String str, final String fileName) throws IOException {
        File file = new File(fileName);
        File fileParent = file.getParentFile();
        if (fileParent != null) {
            fileParent.mkdirs();
        }
        FileUtil.writeString(str, file, Charset.defaultCharset());
    }
}
