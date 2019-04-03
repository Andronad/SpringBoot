package com.iba.courses.service;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class FTPService {
   public String execute(String fileJob)  {
        String serverName ="172.20.2.116"  ;
        String userName ="USER18F" ;
        String password ="ANDRONAD" ;
        FTPClient ftp = new FTPClient() ;
        //Connect to the server
        try {
            ftp.connect (serverName) ;
            String replyText =ftp.getReplyString()  ;
            System.out.println (replyText) ;
        }
        catch (Exception  e)  {
            e.printStackTrace () ;
        }
        //Login to the server
        try {
            ftp.login (userName, password) ;
            String replyText = ftp.getReplyString() ;
            System.out.println (replyText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Tell server that the file will have JCL records
        try {
            ftp.site ("filetype=jes") ;
            String replyText = ftp.getReplyString() ;
            System.out.println (replyText) ;
        }
        catch  (Exception e) {
            e.printStackTrace() ;
        }
        //Submit the job from the text file.Use \\ to avoid using escape notation
        try {
            //WORKING
            FileInputStream inputStream = new FileInputStream ("D:\\"+fileJob+".txt") ;
            ftp.storeFile (serverName,inputStream) ;
            String replyText = ftp.getReplyString() ;
            System.out.println("after submiting");
            System.out.println (replyText) ;
            int i=replyText.indexOf("JOB");
            String name=replyText.substring(i,i+8);
            InputStream is = ftp.retrieveFileStream(name);
            StringBuilder sb=new StringBuilder();
            if (is != null){

                BufferedReader br = new BufferedReader(new InputStreamReader(is));


                String currentLine="";
                while((currentLine = br.readLine()) != null){

                    sb.append(currentLine);
                    sb.append('\n');
                }

            }
            String response=new String (sb);
            return response;

        }
        catch  (Exception e) {
            e.printStackTrace() ;
        }
        //Quit the server
        try {
            ftp.quit() ;
        }
        catch  (Exception e) {
            e.printStackTrace() ;
        }
        return null;
    }
}
