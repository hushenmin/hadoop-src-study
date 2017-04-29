package cn.netty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xiaohu on 2017/4/24.
 */
public class nio {
    public static void main(String[] args) {

    }
    public  void   readFile(String fileName){
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            int size = fileChannel.read(byteBuffer);
            while (size>0){
                //ByteBuffer从写模式，转变读取模式
                byteBuffer.flip();
                while (byteBuffer.remaining()>0){
                    System.out.println(byteBuffer.getChar());
                }
                byteBuffer.clear();
                fileChannel.read(byteBuffer);
            }
            fileChannel.close();
            randomAccessFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
