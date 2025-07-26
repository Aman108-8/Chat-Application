package clientworking;

import gui.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import gui.guichat;
import java.io.*;
import javax.swing.*;
import java.net.*;

public class clientworking
{
    guichat g;
    Runnable r1;
    PrintWriter writ;
    BufferedReader read;
    Socket client;
    Runnable r3;
    
    clientworking()
    {
        g=new guichat();
        g.l1.setText("Client");
        connection();
    }
    void connection()
    {
        try
        {
            //g.tf2.setText("sending request to server...");
            int i=JOptionPane.showConfirmDialog(g.f,"Do you want to send request to server","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(i==JOptionPane.YES_OPTION)
            {
                client=new Socket("127.0.0.1",5555);
                g.tf2.append("Connection done \n");
                g.tf2.setText(" ");
            }
            if(i==JOptionPane.NO_OPTION)
            {
                JOptionPane.showMessageDialog(g.f, "OK");
                System.exit(0);
                client.close();
                
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(g.f, "connection failed");
            System.err.println(e);
            connection();
        }
        
        try
        {
            writ=new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
        
        try
        {
            read=new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
        writing();
        reading();
    }
    
    void writing()
    {
        g.tf.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e)
            {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10)
                {
                    try
                    {

                        String msgw=g.tf.getText();
                        writ.println(msgw);
                        writ.flush();
                        g.tf2.append("me:"+msgw +"\n");
                        g.tf.setText(" ");
                    }
                    catch(Exception e1)
                    {
                        JOptionPane.showMessageDialog(g.f, "failed to send.");
                        System.out.println(e1);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        });
        
    }
    
    void reading()
    {
        r3=()->
        {
            while(true)
            {
                try
                {
                    String content=read.readLine();
                    if(content.equals("exit"))
                    {
                        JOptionPane.showMessageDialog(g.f, "server shutdown the system");
                    }
                    g.tf2.append("Client:"+content+"\n");
                }
                catch(Exception e)
                {
                    //int i=JOptionPane.showConfirmDialog(g.f,"Do you want to send request to server","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    int i=JOptionPane.showConfirmDialog(g.f, "cleint send something but you can't read due somereason.please try again otherwise close","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    System.out.print(e);
                    if (i==JOptionPane.YES_OPTION)
                    {
                        try
                        {
                            connection();
                        }
                        catch(Exception e1)
                        {
                            int i2;
                            i2 = JOptionPane.showConfirmDialog(g.f, "please try again otherwise close","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                            if (i2==JOptionPane.YES_OPTION)
                            {
                                connection();
                            }
                            else
                            {
                                System.exit(0);
                            }
                        }
                    }
                    else
                    {
                        System.exit(0);
                    }
                }
                
            }
        };
        new Thread(r3).start();
    }
    public static void main(String args[])
    {
        new clientworking();
    }
}
