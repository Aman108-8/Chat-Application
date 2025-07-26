package serverworking;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import gui.*;
import java.io.*;
import javax.swing.*;
import java.net.*;

public class serverworking
{
    private Socket client;
    private ServerSocket server;
    PrintWriter write;
    BufferedReader read;
    guichat g;
    Runnable r1,r2;
    serverworking()
    {
        g= new guichat();
        //g.exit.addActionListener((ActionListener) this);
        
        try
        {
            server=new ServerSocket(5555);
            
            g.tf2.setText("connection is ready.... \n waiting....");
            client=server.accept();
            g.tf2.setText("connection establish");
        }
        catch(IOException e)
        {
            System.out.print(e);
        }
        
        try
        {
            Thread.sleep(500);
            g.tf2.setText(" ");
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        
        System.out.println("now you can chat.");
        try
        {
            write=new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        try
        {
            read=new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        writing();
        reading();
    }
    
    void reading()
    {
        r2=()->
        {
            try
            {
                while(true)
                {
                    String re=read.readLine();
                    
                    if(re.equals("exit"))
                    //if(!client.isClosed())
                    {
                        JOptionPane.showMessageDialog(g.f,"server terminated");
                        g.tf.setEnabled(false);
                        
                    }
                    g.tf2.append("Client:"+re+"\n");
                }
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
        };
        new Thread(r2).start(); 
    }
    
    public void writing()
    {
         g.tf.addKeyListener(new KeyListener() {
             //@Override
             public void keyTyped(KeyEvent e) 
             {
                 //System.out.println("keyTyped"+e.getKeyCode());
             }

             @Override
             public void keyPressed(KeyEvent e) 
             {
                 //System.out.println("keyPressed"+e.getKeyCode());
                 if(e.getKeyCode()==10)
                 {
                     String content=g.tf.getText();
                     g.tf2.append("me:"+content+"\n");
                     write.println(content);
                     write.flush();
                     g.tf.setText(" ");
                     g.tf2.requestFocus();
                     if (content.equals("exit"))
                    {
                       int i=JOptionPane.showConfirmDialog(g.f, "you are disconnected", "ERROR",JOptionPane.YES_OPTION,JOptionPane.ERROR_MESSAGE);
                       if (i==JOptionPane.YES_OPTION)
                       {
                           System.exit(0);
                       }

                    }
                 }
             }

             //@Override
             public void keyReleased(KeyEvent e)
             {
                 
             }
         });         
    }
    
    public static void main(String args[]) 
    {
        new serverworking();
    }
}
