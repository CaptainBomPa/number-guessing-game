package com.example.basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame {
    JFrame frame = new JFrame();
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 700;
    public JLabel label2;
    JLabel label3;
    JTextField typedValue;
    JButton buttonTry;
    JButton buttonAgain;


    public GameFrame()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Number Guessing Game");
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

        JPanel pane = new JPanel(new GridBagLayout());
        frame.setContentPane(pane);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        JLabel label1 = new JLabel("Try to guess the number!");
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setFont(new Font("", Font.BOLD, 25));
        label1.setForeground(Color.WHITE);
        label1.setBackground(Color.BLACK);
        label1.setOpaque(true);
        gbc.gridx = 0; gbc.gridy = 0; pane.add(label1, gbc);

        label2 = new JLabel("...");
        label2.setFont(new Font("", Font.BOLD, 20));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setForeground(Color.WHITE);
        label2.setBackground(Color.BLACK);
        label2.setOpaque(true);
        gbc.gridx = 0; gbc.gridy = 2; pane.add(label2, gbc);

        label3 = new JLabel("Left approaches: ");
        label3.setFont(new Font("", Font.BOLD, 20));
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setForeground(Color.WHITE);
        label3.setBackground(Color.BLACK);
        label3.setOpaque(true);
        gbc.gridx = 0; gbc.gridy = 4; pane.add(label3, gbc);

        typedValue = new JTextField();
        typedValue.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        typedValue.setBackground(Color.BLACK);
        typedValue.setOpaque(true);
        typedValue.setHorizontalAlignment(JTextField.CENTER);
        typedValue.setFont(new Font("",Font.BOLD, 40));
        typedValue.setForeground(Color.WHITE);
        typedValue.setCaretColor(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 5; pane.add(typedValue, gbc);

        buttonTry = new JButton("TRY");
        buttonTry.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        buttonTry.setFont(new Font("", Font.BOLD, 20));
        buttonTry.setForeground(Color.WHITE);
        buttonTry.setContentAreaFilled(false);
        buttonTry.setFocusPainted(false);
        buttonTry.setBackground(Color.BLACK);
        buttonTry.setOpaque(true);
        gbc.gridx = 0; gbc.gridy = 6; pane.add(buttonTry, gbc);

        buttonAgain = new JButton("Try again");
        buttonAgain.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        buttonAgain.setFont(new Font("", Font.BOLD, 20));
        buttonAgain.setForeground(Color.WHITE);
        buttonAgain.setContentAreaFilled(false);
        buttonAgain.setFocusPainted(false);
        buttonAgain.setBackground(Color.BLACK);
        buttonAgain.setOpaque(true);
        buttonAgain.setVisible(false);
        gbc.gridx = 0; gbc.gridy = 6; pane.add(buttonAgain, gbc);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startGame();
    }


    public void startGame()
    {
        Guess game = new Guess();
        label2.setText("...");
        label2.setForeground(Color.WHITE);
        label3.setText("Left approaches: " + game.approaches);
        typedValue.requestFocus();
        buttonTry.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttonTry.setBackground(Color.WHITE);
                buttonTry.setForeground(Color.BLACK);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                buttonTry.setBackground(Color.BLACK);
                buttonTry.setForeground(Color.WHITE);
            }
        });
        buttonTry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.invalidate();
                frame.validate();
                frame.repaint();
                if(typedValue.getText().equals("")) {}
                else if(Integer.parseInt(typedValue.getText()) >= 1 && Integer.parseInt(typedValue.getText()) <= 100)
                {
                    if(Integer.parseInt(typedValue.getText()) == game.secretNumber)
                    {
                        label2.setText("You won! Congrats!");
                        label2.setForeground(Color.GREEN);
                        buttonTry.setVisible(false);
                        buttonAgain.setVisible(true);
                    }
                    else if(((Integer.parseInt(typedValue.getText()) > game.secretNumber) && (Integer.parseInt(typedValue.getText()) - game.secretNumber) <= 5))
                    {
                        label2.setText("Try a little bit lower!");
                        label2.setForeground(Color.YELLOW);
                        game.approaches--;
                        label3.setText("Left approaches: " + game.approaches);
                        if(game.approaches == 0)
                        {
                            label2.setText("You lose!");
                            label2.setForeground(Color.RED);
                            buttonTry.setVisible(false);
                            buttonAgain.setVisible(true);
                        }
                    }
                    else if(Integer.parseInt(typedValue.getText()) > game.secretNumber)
                    {
                        label2.setText("Try lower!");
                        label2.setForeground(Color.YELLOW);
                        game.approaches--;
                        label3.setText("Left approaches: " + game.approaches);
                        if(game.approaches == 0)
                        {
                            label2.setText("You lose!");
                            label2.setForeground(Color.RED);
                            buttonTry.setVisible(false);
                            buttonAgain.setVisible(true);
                        }
                    }
                    else if(Integer.parseInt(typedValue.getText()) < game.secretNumber && (Integer.parseInt(typedValue.getText()) - game.secretNumber) >= -5)
                    {
                        label2.setText("Try a little bit higher!");
                        label2.setForeground(Color.YELLOW);
                        game.approaches--;
                        label3.setText("Left approaches: " + game.approaches);
                        if(game.approaches == 0)
                        {
                            label2.setText("You lose!");
                            label2.setForeground(Color.RED);
                            buttonTry.setVisible(false);
                            buttonAgain.setVisible(true);
                        }
                    }
                    else
                    {
                        label2.setText("Try higher!");
                        label2.setForeground(Color.YELLOW);
                        game.approaches--;
                        label3.setText("Left approaches: " + game.approaches);
                        if(game.approaches == 0)
                        {
                            label2.setText("You lose!");
                            label2.setForeground(Color.RED);
                            buttonTry.setVisible(false);
                            buttonAgain.setVisible(true);
                        }
                    }
                }
                else if(Integer.parseInt(typedValue.getText()) < 1 || Integer.parseInt(typedValue.getText()) > 100)
                {
                    label2.setText("Only numbers from range 1-100");
                    label2.setForeground(Color.RED);
                }
                else
                {
                    label2.setText("Input error!");
                    label2.setForeground(Color.RED);
                }
                typedValue.setText("");
                typedValue.requestFocus();
                if(!buttonTry.isVisible()) typedValue.setEnabled(false);
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }
        });
        buttonAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAgain.setVisible(false);
                buttonTry.setVisible(true);
                typedValue.setEnabled(true);
                startGame();
            }
        });
    }


    public static void main(String[] args) {
        new GameFrame();
    }
}
