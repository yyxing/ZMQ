package com.devil.zmq.broker.utils;

import sun.misc.Unsafe;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 支持基于Java的MMap api访问文件能力（文件读写）
 * 支持指定offset内的文件映射
 * 文件读取(指定的offset)
 * 文件写入(指定的offset)
 * 释放内存
 */
public class MMapUtil {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile(new File(""), "rw");
        MappedByteBuffer map = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1);
        Unsafe.getUnsafe().invokeCleaner(map);
    }

}
