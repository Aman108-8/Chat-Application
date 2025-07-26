package gui;

import java.awt.*;
import java.awt.Font;
//import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class guichat 
{
    public JFrame f;
    public JTextField tf;
    public JLabel l1;
    public JTextArea tf2;
    public Font font1,font;
    public JButton exit;
    public guichat()
    {
        f=new JFrame("server chat");
        exit=new JButton("EXIT");
        tf2=new JTextArea();
        tf=new JTextField();
        l1=new JLabel("SERVER");
        font=new Font("Roboto",Font.BOLD,20);
        font1=new Font("Roboto",Font.ITALIC,20);
        
        ad();
        sett();
        siz();
    }
    public void sett()
    {
        tf2.setEditable(false);
        l1.setFont(font);
        l1.setHorizontalAlignment(SwingConstants.CENTER);       //Screenshot (37)
        l1.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 10));
        //l1.setIcon(new ImageIcon("icon.jpg"));
        //l1.setHorizontalTextPosition(SwingConstants.BOTTOM);
        f.setBackground(Color.red);
        tf2.setFont(font1);
        tf.setFont(font1);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        tf2.setLineWrap(true);
    }
    public void ad()
    {
        f.setLayout(new BorderLayout());
        f.add(l1,BorderLayout.NORTH);
        f.add(tf2,BorderLayout.CENTER);
        f.add(tf,BorderLayout.SOUTH);
        f.add(exit,BorderLayout.EAST);
        
        exit.addActionListener(new ActionListener()
         {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 try
                 {
                     //client.close();
                     System.exit(0);
                        
                 }
                 catch(Exception e1)
                 {
                     System.out.println("closed");
                 }
             }
         });
        
    }
    public void siz()
    {
        f.setSize(400,400);
        
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    /*public static void main(String args[]) 
    {
        new guichat();
    }*/
}
