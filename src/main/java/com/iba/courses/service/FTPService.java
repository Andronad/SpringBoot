package com.iba.courses.service;

import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FTPService {
   public void execute(String fileJob)  {
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
            InputStream is = ftp.retrieveFileStream("JOB08890");

            if (is != null){

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String currentLine="";
                while((currentLine = br.readLine()) != null){

                    System.out.println(currentLine);
                }

            }
            System.out.println("end");
            //NOT WORKING
            /*ftp.retrieveFileStream("USER18F.JCL(TEMPJOB)");

            String replyText = ftp.getReplyString();
            System.out.println("some " + replyText);

            String[] replies = ftp.getReplyStrings();
            String remoteFileName = replies[replies.length - 1].split(" ")[2]+ ".2";


            for(String rep :replies){

                System.out.println("checking .. " + rep);
            }

            Thread.sleep(10000);

            System.out.println("getting sysout of the file " + remoteFileName);

            InputStream is = ftp.retrieveFileStream(remoteFileName);
            replies = ftp.getReplyStrings();
            for(String rep :replies){

                System.out.println("checking 2 .. " + rep);
            }

            if (is != null){

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String currentLine="";
                while((currentLine = br.readLine()) != null){

                    System.out.println(currentLine);
                }

            }

            ftp.completePendingCommand();

            System.out.println("Done...");*/

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
    }
}
