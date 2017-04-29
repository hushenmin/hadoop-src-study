package cn.netty;





import org.apache.commons.lang.CharSet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by xiaohu on 2017/4/24.
 */
public class ByteBufferDemo {
    public static void main(String[] args) {

    }
    public static void   readFile(String fileName){
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            int size = fileChannel.read(byteBuffer);
            while (size>0){
                //ByteBuffer从写模式，转变读取模式
                byteBuffer.flip();
             /**  while (byteBuffer.remaining()>0){
                    System.out.println(byteBuffer.getChar());
                }*/
               // CharSet charSet = CharSet.forName('utf-8');
                Charset charset = Charset.forName("utf-8");
               // System.out.println(byteBuffer.toString()); Erro
                System.out.println(charset.newDecoder().decode(byteBuffer).toString());
                byteBuffer.clear();
                fileChannel.read(byteBuffer);
                size=fileChannel.read(byteBuffer);


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
