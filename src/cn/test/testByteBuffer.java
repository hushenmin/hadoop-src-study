package cn.test;

import cn.netty.ByteBufferDemo;
import org.junit.Test;
/**
 * Created by xiaohu on 2017/4/24.
 */
public class testByteBuffer {
    @Test
    public void testByteBufferRW(){

       ByteBufferDemo.readFile("file/1.txt");
    }

}
