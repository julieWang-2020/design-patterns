package com.wzh.socket.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author wzh
 * @date 2020-06-10 23:38
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket s=new Socket("127.0.0.1",8888);
        s.getOutputStream().write("HelloServer".getBytes());
        s.getOutputStream().flush();
        System.out.println("write over, waiting for msg back");
        byte[] bytes=new byte[1024];
        int len=s.getInputStream().read(bytes);
        System.out.println(new String(bytes,0,len));
        s.close();
    }
}
