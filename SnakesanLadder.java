import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.*;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;



class Game extends JFrame implements ActionListener {

    private Token player1, player2;
    private JButton diceButton;
    private JLabel diceValue;
    private JLabel playerTurn;
    private int mode;
    Template T=new Template();
    String n1="",n2="";
    
    protected Game()
    {
        setTitle("Loading Snake&Ladder Game");
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 2, 0, 2);
        c.gridx = 0;
        c.gridy = 0;
        c.ipady=0;
        JLabel loader=new JLabel(new ImageIcon("Resources/loader.gif"));
        p.add(loader,c);
        JLabel b=new JLabel(new ImageIcon("Resources/progressbar1.gif"));
        c.gridy = 1;
        p.add(b,c);
        b.setPreferredSize(p.getSize());
        WindowListener wndCloser = new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);
        getContentPane().add(p);
        setSize(500, 450);
        setVisible(true);
        int i=0;
        try {
            Thread.sleep(10000);
            this.dispose();
            Game game=new Game("Let's GO!");
        }
        catch(Exception e){

        }

    }
    protected Game(String s)
    { 
        setTitle("Start Menu"); 
        JPanel p = new JPanel(); 
        p.setLayout(new GridBagLayout()); 
        GridBagConstraints c = new GridBagConstraints(); 
        c.insets = new Insets(2, 2, 2, 2); 
        c.gridx = 0; 
        c.gridy = 0; 
        c.ipadx = 40; 
        c.ipady = 50; 
        JButton start=new JButton("Start",new ImageIcon("Resources/play2.jpg"));
        start.setToolTipText("Click or press Spacebar to Start");
        start.setFocusPainted(false);
        //start.setBackground(Color.PINK);
        start.setOpaque(true);
        start.setBounds(700, 100, 150, 60);
        start.addActionListener(this);
        p.add(start, c); 
        c.gridy=1;
        c.ipadx=45;
        JButton Exit=new JButton("Exit",new ImageIcon("Resources/cross1.jpg"));
        Exit.setFocusPainted(false);
        //Exit.setBackground(Color.PINK);
        Exit.setOpaque(true);
        Exit.setBounds(700, 100, 150, 60);
        Exit.addActionListener(this);
        p.add(Exit, c);
        WindowListener wndCloser = new WindowAdapter() 
        { 
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
            } 
        }; 
        addWindowListener(wndCloser); 
        getContentPane().add(p); 
        setSize(600, 400); 
        setVisible(true); 
    
    }

    protected Game(String winPlName, int mode,int temp)
    {
        this.mode=mode;
        T.setTemp(temp);
        System.out.println(T.getTemp()+" "+mode);
        AudioInputStream audioInputStream;
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/lightapplause.wav").getAbsoluteFile());
            // create clip reference
            Clip clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception e)
        {
        }
        setTitle("Restart Menu");
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 40;
        c.ipady = 50;
        JLabel winner=new JLabel(winPlName+" is the winner!");
        winner.setFont(winner.getFont().deriveFont(18.0f));
        winner.setForeground(Color.PINK);
        winner.setBounds(710, 200, 140, 25);
        p.add(winner,c);
        c.gridy=1;
        JButton Restart=new JButton("Restart",new ImageIcon("Resources/Restart.jpg"));
        Restart.setToolTipText("Click or press Spacebar to Start");
        Restart.setFocusPainted(false);
        //start.setBackground(Color.PINK);
        Restart.setOpaque(true);
        Restart.setBounds(700, 100, 150, 60);
        Restart.addActionListener(this);
        p.add(Restart, c);
        c.gridx=1;
        c.ipadx=40;
        JButton MM=new JButton("Main Menu",new ImageIcon("Resources/MainMenu.jpg"));
        MM.setFocusPainted(false);
        //Exit.setBackground(Color.PINK);
        MM.setOpaque(true);
        MM.setBounds(700, 100, 150, 60);
        MM.addActionListener(this);
        p.add(MM, c);
        WindowListener wndCloser = new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);
        getContentPane().add(p);
        setSize(600, 400);
        setVisible(true);

    }
    
    protected Game(int template)
    {
        T.setTemp(template);
        setTitle("Mode Menu"); 
        JPanel p = new JPanel(); 
        p.setLayout(new GridBagLayout()); 
        GridBagConstraints c1 = new GridBagConstraints(); 
        c1.insets = new Insets(2, 2, 2, 2); 
        c1.gridx = 0; 
        c1.gridy = 0; 
        c1.ipadx = 50; 
        c1.ipady = 50; 
        JButton Player1=new JButton("1 Player");
        Player1.setToolTipText("Click or press Spacebar to roll dice");
        Player1.setFocusPainted(false);
        Player1.setBackground(Color.RED);
        Player1.setOpaque(true);
        Player1.setBounds(700, 100, 150, 60);
        Player1.addActionListener(this);
        p.add(Player1, c1); 
        c1.gridy=1;
        c1.ipadx=45;
        JButton Player2=new JButton("2 Players");
        Player2.setFocusPainted(false);
        Player2.setBackground(Color.GREEN);
        Player2.setOpaque(true);
        Player2.setBounds(700, 100, 150, 60);
        Player2.addActionListener(this);
        p.add(Player2, c1);
        WindowListener wndCloser = new WindowAdapter() { 
  
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
            } 
        }; 
        addWindowListener(wndCloser); 
        getContentPane().add(p); 
        setSize(600, 400); 
        setVisible(true); 
    }
    
    protected Game(boolean b)
    {
        setTitle("Select Template"); 
        JPanel p = new JPanel(); 
        p.setLayout(new GridBagLayout()); 
        GridBagConstraints c = new GridBagConstraints(); 
        //c.fill=GridBagConstraints.BOTH;
        //c.weightx=1;
        //c.weighty=1;
        //c.gridwidth=20;
        c.insets=new Insets(0, 0, 20, 20); 
        c.ipadx = 10; 
        c.ipady = 0; 
        JButton temp1=new JButton("Template 1",new ImageIcon("Resources/Template1.jpg"));
        temp1.setFocusPainted(false);
        //start.setBackground(Color.PINK);
        temp1.setOpaque(true);
        //start.setBounds(700, 100, 150, 60);
        temp1.addActionListener(this);
        p.add(temp1, c);
        c.ipadx = 0; 
        JButton temp2=new JButton("Template 2",new ImageIcon("Resources/Template2.jpg"));
        temp2.setFocusPainted(false);
        //start.setBackground(Color.PINK);
        temp2.setOpaque(true);
        //start1.setBounds(700, 100, 150, 60);
        temp2.addActionListener(this);
        p.add(temp2, c);
        c.gridy=1;
        JButton temp3=new JButton("Template 3",new ImageIcon("Resources/Template3.jpg"));
        temp3.setFocusPainted(false);
        //start.setBackground(Color.PINK);
        temp3.setOpaque(true);
        //start1.setBounds(700, 100, 150, 60);
        temp3.addActionListener(this);
        p.add(temp3, c);
        JButton temp4=new JButton("Template 4",new ImageIcon("Resources/Template4.jpg"));
        temp4.setFocusPainted(false);
        //start.setBackground(Color.PINK);
        temp4.setOpaque(true);
        //start1.setBounds(700, 100, 150, 60);
        temp4.addActionListener(this);
        p.add(temp4, c);
        WindowListener wndCloser = new WindowAdapter() 
        { 
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
            } 
        }; 
        addWindowListener(wndCloser); 
        getContentPane().add(p); 
        setSize(850, 550);
        setVisible(true); 
    }

    protected Game(int mode,int key) {
        player1 = new Token();
        player2 = new Token();
        this.mode=mode;
        T.setTemp(key);
        if(mode==1)
        {
           n1 = JOptionPane.showInputDialog(null,"Enter your name","Player name",JOptionPane.QUESTION_MESSAGE);
           n2="Computer";
        }
        else
        {
           n1 = JOptionPane.showInputDialog(null,"Player 1 enter your name","Player 1 name",JOptionPane.QUESTION_MESSAGE); 
           n2 = JOptionPane.showInputDialog(null,"Player 2 enter your name","Player 2 name",JOptionPane.QUESTION_MESSAGE);
        }
        setSize(880, 715);
        setTitle("Snakes And Ladders");
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);
        menu.add(newGame);

        JMenuItem exitMenu = new JMenuItem("Exit Game");
        exitMenu.addActionListener(this);
        menu.add(exitMenu);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        ImagePanel imagePanel;
        if(T.getTemp()==1)
            imagePanel=new ImagePanel(1);
        else if(T.getTemp()==2)
            imagePanel=new ImagePanel(2);
        else if(T.getTemp()==3)
            imagePanel=new ImagePanel(3);
        else
            imagePanel=new ImagePanel(4);
        imagePanel.setLayout(null);

        diceButton = new JButton("Roll Dice");
        diceButton.setToolTipText("Click or press Spacebar to roll dice");
        diceButton.setFocusPainted(false);
        diceButton.setBackground(Color.PINK);
        diceButton.setOpaque(true);
        diceButton.setBounds(700, 100, 150, 60);
        diceButton.addActionListener(this);
        imagePanel.add(diceButton);

        playerTurn = new JLabel(n1+"'s Turn");
        playerTurn.setFont(playerTurn.getFont().deriveFont(18.0f));
        playerTurn.setForeground(Color.PINK);
        playerTurn.setBounds(710, 200, 140, 25);
        add(playerTurn);

        JLabel diceValueLabel = new JLabel("Dice Value");
        diceValueLabel.setFont(playerTurn.getFont().deriveFont(18.0f));
        diceValueLabel.setForeground(Color.BLACK);
        diceValueLabel.setBounds(710, 300, 150, 25);
        add(diceValueLabel);

        diceValue = new JLabel(" -");
        diceValue.setFont(playerTurn.getFont().deriveFont(35.0f));
        diceValue.setForeground(Color.BLACK);
        diceValue.setBounds(750, 340, 40, 40);
        add(diceValue);
        
        player1.turn = 1;
        player1.playerName=n1;
        player2.playerName=n2;

        add(imagePanel);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int key=1;
        String action = e.getActionCommand();
        switch (action) {
            case "Template 1":
            this.dispose();
            key=1;
            Game temp1 = new Game(1);
            break;
            case "Template 2":
            this.dispose();
            key=2;
            Game temp2 = new Game(2);
            break;
            case "Template 3":
                this.dispose();
                key=3;
                Game temp3 = new Game(3);
                break;
            case "Template 4":
                this.dispose();
                key=4;
                Game temp4 = new Game(4);
                break;
            case "Exit Game":
            case "Main Menu":
            this.dispose();
            Game g = new Game("Go back.");
            break;
            case "New Game":
                /*repaint();
                player1.xPosition = player2.xPosition = 65;
                player1.yPosition = player2.yPosition = 630;
                player1.turn = 1;
                player1.entry = player2.entry = player3.entry = 0;
                player1.reverseMove = player2.reverseMove = 0;
                player2.turn = 0;
                diceButton.setEnabled(true);
                diceButton.requestFocusInWindow();
                playerTurn.setForeground(new Color(255,0,0));
                playerTurn.setText("Player 1 Turn");
                diceValue.setText("-");*/
                this.dispose();
            Game restartMode = new Game(key);
            break;
            case "Roll Dice":
            rollDice(mode);
            break;
            case "Start":
            this.dispose();
            //Game mode = new Game('0');
            Game template = new Game(true);

            //game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //game.setVisible(true);
            break;
            case "Exit":
            this.dispose();
            break;
            case "1 Player":
            this.dispose();
            Game game1P = new Game(1,T.getTemp());
            game1P.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            game1P.setVisible(true);
            dispTurnMsg(game1P.n1);
            break;
            case "2 Players":
            this.dispose();
            Game gamePvP = new Game(2,T.getTemp());
            gamePvP.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            gamePvP.setVisible(true);
            dispTurnMsg(gamePvP.n1);
            break;
            case "Restart":
            this.dispose();
            Game restartGame = new Game(mode,T.getTemp());
            restartGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            restartGame.setVisible(true);
            dispTurnMsg(restartGame.n1);
            break;
            default:
            break;    
        }
    }

    private void setPlayerPosition(int diceNumber) {


        Graphics graphics = getGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;
        int repeat = 0;
        if (diceNumber == 1 || diceNumber == 6) {
            repeat = 1;
        }

        if (player1.turn == 1) {

            dispDiceScore(player1.playerName,diceNumber,1,mode);
            playerTurn.setText(player1.playerName+"'s Turn");
            diceValue.setText(null);
            diceValue.setText(" -");

            if (player1.entry == 0 && diceNumber == 1) {

                graphics2D.setPaint(Color.red);
                graphics2D.fillOval(player1.xPosition, player1.yPosition, 20, 20);
                player1.entry = 1;
                player1.turn = 0;
                player2.turn = 1;
            }

            if (player1.entry == 1 && player1.turn == 1) {

                update(graphics);
                if (player1.reverseMove == 0) {

                    player1.xPosition += 60 * diceNumber;
                    if (player1.xPosition > 605) {

                        player1.yPosition -= 60;
                        player1.xPosition = 605 - 60 * (((player1.xPosition - 605) / 60) - 1);
                        player1.reverseMove = 1;
                    }
                } else {

                    player1.xPosition -= 60 * diceNumber;

                    if (player1.yPosition != 90) {

                        if (player1.xPosition < 65) {

                            player1.yPosition -= 60;
                            if (player1.xPosition > 0)
                                player1.xPosition = 65 + 60 * (((65 - player1.xPosition) / 60) - 1);
                            else
                                player1.xPosition = 65 + 60 * ((((-1 * player1.xPosition) + 65) / 60) - 1);
                            player1.reverseMove = 0;
                        }
                    } else {

                        if (player1.xPosition < 65) {

                            player1.xPosition += 60 * diceNumber;
                        } else if (player1.xPosition == 65) {

                            JOptionPane.showMessageDialog(null, player1.playerName+" Wins", "Winner", JOptionPane.INFORMATION_MESSAGE);
                            diceButton.setEnabled(false);
                            repeat=0;
                            this.dispose();
                            Game newGame = new Game(player1.playerName,mode,T.getTemp());
                            return;

                        }
                    }
                }

                graphics2D.setPaint(Color.red);
                int flag=-1;
                flag=checkPosition(player1,T.getTemp());
                if(flag==0)
                    snakeSound(player1.playerName);
                else if(flag==1)
                {
                    ladderSound((player1.playerName));
                    JOptionPane.showMessageDialog(null, player1.playerName+" Wins", "Winner", JOptionPane.INFORMATION_MESSAGE);
                    diceButton.setEnabled(false);
                    repeat=0;
                    this.dispose();
                    Game newGame = new Game(player1.playerName,mode,T.getTemp());
                    return;
                }
                else if(flag==2)
                    ladderSound((player1.playerName));
                graphics2D.fillOval(player1.xPosition, player1.yPosition, 20, 20);

                if (player2.entry == 1) {

                    graphics2D.setPaint(Color.blue);
                    graphics2D.fillOval(player2.xPosition, player2.yPosition + 20, 20, 20);
                }
            }

            if (repeat == 1) {

                repeatTurnDisp(player1.playerName,1,mode);
                //JOptionPane.showMessageDialog(null, player1.playerName+" please Roll the Dice Again");
                playerTurn.setText(player1.playerName+"'s Turn");
                player1.turn = 1;
                player2.turn = 0;
                playerTurn.setForeground(new Color(255, 0, 0));
                diceValue.setText(null);
                diceValue.setText(" -");
                diceValue.setText(null);
                diceValue.setText(" -");
                diceValue.setText(" -");
                playerTurn.setText("Player 1 Turn");
            } else {
                player2.turn = 1;
                player1.turn = 0;
                playerTurn.setForeground(new Color(0, 0, 255));
                
                if(mode==1)
                    playerTurn.setText("Computer Turn");
                else
                    playerTurn.setText(player2.playerName+"'s Turn");
                dispPlayerTurn(player2.playerName,2,mode);
                diceValue.setText(null);
                diceValue.setText(" -");
                diceValue.setText(null);
                //diceValue.setText(" -");
               /* if(mode==1){
                playerTurn.setText("Computer Turn");
                dispPlayerTurn(player2.playerName,2,mode);
                //JOptionPane.showMessageDialog(null, "It's now "+player2.playerName+"'s Turn.");
                diceValue.setText(null);
                diceValue.setText(" -");
            }
                else{
                playerTurn.setText(player2.playerName+"'s Turn");
                dispPlayerTurn(player2.playerName,2,mode);
                //JOptionPane.showMessageDialog(null, "It's now "+player2.playerName+"'s Turn. Click on Roll Dice or press spacebar");
                diceValue.setText(null);
                diceValue.setText(" -");
            }*/
            //diceValue.setText(null);
            //diceValue.setText(" -");
            //diceValue.setText(null);
           // diceValue.setText(" -");
            //diceValue.setText(" -");
                //playerTurn.setText(null);
                if(mode==1){
                playerTurn.setText("Computer Turn");
                rollDice(mode);
            }
                else{
                playerTurn.setText(player2.playerName+"'s Turn");

            }    
            diceValue.setText(null);
            diceValue.setText(" -");
            diceValue.setText(null);
            }

        } else if(player2.turn==1) {

            dispDiceScore(player2.playerName,diceNumber,2,mode);
            diceValue.setText(null);
            diceValue.setText(" -");
            diceValue.setText(null);
            if (player2.entry == 0 && diceNumber == 1) {

                graphics2D.setPaint(Color.blue);
                graphics2D.fillOval(player2.xPosition, player2.yPosition + 20, 20, 20);
                player2.entry = 1;
                player1.turn = 1;
                player2.turn = 0;
            }

            if (player2.entry == 1 && player2.turn == 1) {
                update(graphics);

                if (player2.reverseMove == 0) {

                    player2.xPosition += 60 * diceNumber;
                    if (player2.xPosition > 605) {
                        player2.yPosition -= 60;
                        player2.xPosition = 605 - 60 * (((player2.xPosition - 605) / 60) - 1);
                        player2.reverseMove = 1;
                    }
                } else {
                    player2.xPosition -= 60 * diceNumber;
                    if (player2.yPosition != 90) {
                        if (player2.xPosition < 65) {

                            player2.yPosition -= 60;
                            if (player2.xPosition > 0)
                                player2.xPosition = 65 + 60 * (((65 - player2.xPosition) / 60) - 1);
                            else
                                player2.xPosition = 65 + 60 * ((((-1 * player2.xPosition) + 65) / 60) - 1);
                            player2.reverseMove = 0;
                        }
                    } else {
                        if (player2.xPosition < 65)
                            player2.xPosition += 60 * diceNumber;
                        else if (player2.xPosition == 65) {
                            if(mode==2){
                            JOptionPane.showMessageDialog(null, player2.playerName+" Wins", "Winner", JOptionPane.INFORMATION_MESSAGE);
                        }
                            if(mode==1){
                            JOptionPane.showMessageDialog(null, "Computer Wins", "Winner", JOptionPane.INFORMATION_MESSAGE);
                        }
                            diceButton.setEnabled(false);
                            repeat=0;
                            this.dispose();
                            Game endGame = new Game(player2.playerName,mode,T.getTemp());
                            return;

                        }
                    }
                }
                graphics2D.setPaint(Color.blue);
                int flag=-1;
                flag=checkPosition(player2,T.getTemp());
                if(flag==0)
                    snakeSound(player2.playerName);
                else if(flag==1)
                {
                    if(mode==2){
                        JOptionPane.showMessageDialog(null, player2.playerName+" Wins", "Winner", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(mode==1){
                        JOptionPane.showMessageDialog(null, "Computer Wins", "Winner", JOptionPane.INFORMATION_MESSAGE);
                    }
                    diceButton.setEnabled(false);
                    repeat=0;
                    this.dispose();
                    Game endGame = new Game(player2.playerName,mode,T.getTemp());
                    return;
                }
                else if(flag==2)
                    ladderSound(player2.playerName);
                graphics2D.fillOval(player2.xPosition, player2.yPosition + 20, 20, 20);
                if (player1.entry == 1) {
                    graphics2D.setPaint(Color.red);
                    graphics2D.fillOval(player1.xPosition, player1.yPosition, 20, 20);
                }
            }
            if (repeat == 1) {
                repeatTurnDisp(player2.playerName, 2,mode);
                /*if(mode==2)
                JOptionPane.showMessageDialog(null, player2.playerName+" please Roll Again");
                else
                JOptionPane.showMessageDialog(null, player2.playerName+" will Roll Again");*/
                diceValue.setText(null);
                diceValue.setText(" -");
                diceValue.setText(null);
                player2.turn = 1;
                player1.turn = 0;
                playerTurn.setForeground(new Color(0, 0, 255));
                if(mode==1)
                rollDice(mode);
            } else {
                player1.turn = 1;
                player2.turn = 0;
                playerTurn.setForeground(new Color(255, 0, 0));
                playerTurn.setText(player1.playerName+"'s Turn");
                dispPlayerTurn(player1.playerName, 1,mode);
                //JOptionPane.showMessageDialog(null, "It's now "+player1.playerName+"'s Turn. Click on Roll Dice or press spacebar");
                diceValue.setText(null);
                diceValue.setText(" -");
                //playerTurn.setText(player1.playerName+"'s Turn");
                playerTurn.setText(null);
                playerTurn.setText(player1.playerName+"'s Turn");
                diceValue.setText(null);
                diceValue.setText(null);
                diceValue.setText(" -");
                diceValue.setText(" -");
            }
        }
        diceButton.setEnabled(true);
        diceButton.requestFocus();
        diceValue.setText(null);
        //diceValue.setText(" -");
        if(player1.turn==1)
        playerTurn.setText(player1.playerName+"'s Turn");
        else
        playerTurn.setText(player2.playerName+"'s Turn");
    }

    private int checkPosition(Token p,int index) {
        int k=0;
        if(index==1){
        if (p.xPosition == 485 && p.yPosition == 630) //8
        {
            p.xPosition = 605;
            p.yPosition = 450;
            p.reverseMove = 1;
            return 2;
        } else if (p.xPosition == 365 && p.yPosition == 570) //15
        {
            p.xPosition = 245;
            p.yPosition = 90;
            p.reverseMove = 1;
            return 2;
        } else if (p.xPosition == 245 && p.yPosition == 510) //24
        {
            p.xPosition = 65;
            p.yPosition = 630;
            p.reverseMove = 0;
            return 0;
        } else if (p.xPosition == 125 && p.yPosition == 390) //42
        {
            p.xPosition = 65;
            p.yPosition = 150;
            p.reverseMove = 0;
            return 2;
        } else if (p.xPosition == 365 && p.yPosition == 330) //55
        {
            p.xPosition = 485;
            p.yPosition = 570;
            p.reverseMove = 1;
            return 0;
        } else if (p.xPosition == 365 && p.yPosition == 270) //66
        {
            p.xPosition = 425;
            p.yPosition = 150;
            p.reverseMove = 0;
            return 2;
        } else if (p.xPosition == 605 && p.yPosition == 210) //71
        {
            p.xPosition = 545;
            p.yPosition = 510;
            p.reverseMove = 0;
            return 0;
        } else if (p.xPosition == 485 && p.yPosition == 150) //88
        {
            p.xPosition = 425;
            p.yPosition = 270;
            p.reverseMove = 0;
            return 0;
        } else if (p.xPosition == 125 && p.yPosition == 90) //99
        {
            p.xPosition = 365;
            p.yPosition = 630;
            p.reverseMove = 0;
            return 0;
        }
    }
    else if(index==2)
    {
        if(p.xPosition == 185 && p.yPosition == 630) //3
        {
            p.xPosition = 65;
            p.yPosition = 570;
            p.reverseMove = 1;
            return 2;
        }
        else if(p.xPosition == 365 && p.yPosition == 630) //6
        {
            p.xPosition = 425;
            p.yPosition = 570;
            p.reverseMove = 1;
            return 2;
        }
        else if (p.xPosition == 485 && p.yPosition == 630) //8
        {
            p.xPosition = 245;
            p.yPosition = 630;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 605 && p.yPosition == 570) //11
        {
            p.xPosition = 485;
            p.yPosition = 510;
            p.reverseMove = 0;
            return 2;
        }
        else if(p.xPosition == 365 && p.yPosition == 570) //15
        {
            p.xPosition = 425;
            p.yPosition = 450;
            p.reverseMove = 1;
            return 2;
        }
        else if(p.xPosition == 245 && p.yPosition == 570) //17
        {
            p.xPosition = 425;
            p.yPosition = 210;
            p.reverseMove = 1;
            return 2;
        }
        else if (p.xPosition == 185 && p.yPosition == 570) //18
        {
            p.xPosition = 65;
            p.yPosition = 630;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 125 && p.yPosition == 510) //22
        {
            p.xPosition = 245;
            p.yPosition = 450;
            p.reverseMove = 1;
            return 2;
        }
        else if(p.xPosition == 365 && p.yPosition == 510) //26
        {
            p.xPosition = 605;
            p.yPosition = 630;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 185 && p.yPosition == 450) //38
        {
            p.xPosition = 125;
            p.yPosition = 330;
            p.reverseMove = 1;
            return 2;
        }
        else if(p.xPosition == 125 && p.yPosition == 450) //39
        {
            p.xPosition = 305;
            p.yPosition = 630;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 545 && p.yPosition == 390) //49
        {
            p.xPosition = 425;
            p.yPosition = 270;
            p.reverseMove = 0;
            return 2;
        }
        else if(p.xPosition == 605 && p.yPosition == 330) //51
        {
            p.xPosition = 425;
            p.yPosition = 570;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 425 && p.yPosition == 330) //54
        {
            p.xPosition = 305;
            p.yPosition = 450;
            p.reverseMove = 1;
            return 0;
        }
        else if(p.xPosition == 305 && p.yPosition == 330) //56
        {
            p.xPosition = 65;
            p.yPosition = 630;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 245 && p.yPosition == 330) //57
        {
            p.xPosition = 305;
            p.yPosition = 210;
            p.reverseMove = 1;
            return 2;
        }
        else if(p.xPosition == 65 && p.yPosition == 330) //60
        {
            p.xPosition = 185;
            p.yPosition = 510;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 65 && p.yPosition == 270) //61
        {
            p.xPosition = 185;
            p.yPosition = 210;
            p.reverseMove = 1;
            return 2;
        }
        else if(p.xPosition == 485 && p.yPosition == 210) //73
        {
            p.xPosition = 365;
            p.yPosition = 150;
            p.reverseMove = 0;
            return 2;
        }
        else if(p.xPosition == 365 && p.yPosition == 210) //75
        {
            p.xPosition = 485;
            p.yPosition = 510;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 65 && p.yPosition == 150) //81
        {
            p.xPosition = 185;
            p.yPosition = 90;
            p.reverseMove = 1;
            return 2;
        }
        else if(p.xPosition == 185 && p.yPosition == 150) //83
        {
            p.xPosition = 305;
            p.yPosition = 390;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 305 && p.yPosition == 150) //85
        {
            p.xPosition = 125;
            p.yPosition = 330;
            p.reverseMove = 1;
            return 0;
        }
        else if(p.xPosition == 485 && p.yPosition == 150) //88
        {
            p.xPosition = 605;
            p.yPosition = 90;
            p.reverseMove = 1;
            return 2;
        }
        else if(p.xPosition == 605 && p.yPosition == 150) //90
        {
            p.xPosition = 485;
            p.yPosition = 390;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 545 && p.yPosition == 90) //92
        {
            p.xPosition = 305;
            p.yPosition = 510;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 245 && p.yPosition == 90) //97
        {
            p.xPosition = 425;
            p.yPosition = 150;
            p.reverseMove = 0;
            return 0;
        }
        else if(p.xPosition == 125 && p.yPosition == 90) //99
        {
            p.xPosition = 185;
            p.yPosition = 270;
            p.reverseMove = 0;
            return 0;
        }
    }
    else if(index==3)
        {
            if(p.xPosition == 485 && p.yPosition == 630)//8
            {
                p.xPosition = 365;
                p.yPosition = 510;
                p.reverseMove = 0;
                return 2;
            }
            else if(p.xPosition == 65 && p.yPosition == 510) //21
            {
                p.xPosition = 125;
                p.yPosition = 150;
                p.reverseMove = 0;
                return 2;
            }
            else if (p.xPosition == 185 && p.yPosition == 390) //43
            {
                p.xPosition = 245;
                p.yPosition = 210;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 245 && p.yPosition == 390) //44
            {
                p.xPosition = 125;
                p.yPosition = 510;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 365 && p.yPosition == 390) //46
            {
                p.xPosition = 305;
                p.yPosition = 630;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 485 && p.yPosition == 390) //48-9
            {
                p.xPosition = 545;
                p.yPosition = 630;
                p.reverseMove = 0;
                return 0;
            }
            else if (p.xPosition == 605 && p.yPosition == 390) //50-91
            {
                p.xPosition = 605;
                p.yPosition = 90;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 545 && p.yPosition == 330) //52-11
            {
                p.xPosition = 605;
                p.yPosition = 570;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 425 && p.yPosition == 330) //54-93
            {
                p.xPosition = 485;
                p.yPosition = 90;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 365 && p.yPosition == 330) //55-7
            {
                p.xPosition = 425;
                p.yPosition = 630;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 125 && p.yPosition == 330) //59-17
            {
                p.xPosition = 245;
                p.yPosition = 570;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 125 && p.yPosition == 270) //62-96
            {
                p.xPosition = 305;
                p.yPosition = 90;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 245 && p.yPosition == 270) //64-36
            {
                p.xPosition = 305;
                p.yPosition = 450;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 365 && p.yPosition == 270) //66-87
            {
                p.xPosition = 425;
                p.yPosition = 150;
                p.reverseMove = 0;
                return 2;
            }
            else if(p.xPosition == 545 && p.yPosition == 270) //69-33
            {
                p.xPosition = 485;
                p.yPosition = 450;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 485 && p.yPosition == 210) //73-1
            {
                p.xPosition = 65;
                p.yPosition = 630;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 65 && p.yPosition == 210) //80-100
            {
                p.xPosition = 65;
                p.yPosition = 90;
                p.reverseMove = 1;
                return 1;
            }
            else if(p.xPosition == 185 && p.yPosition == 150) //83-19
            {
                p.xPosition = 125;
                p.yPosition = 570;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 545 && p.yPosition == 90) //92-51
            {
                p.xPosition = 605;
                p.yPosition = 330;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 365 && p.yPosition == 90) //95-24
            {
                p.xPosition = 245;
                p.yPosition = 510;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 185 && p.yPosition == 90) //98-28
            {
                p.xPosition = 485;
                p.yPosition = 510;
                p.reverseMove = 0;
                return 0;
            }
        }
    else{
            if(p.xPosition == 485 && p.yPosition == 630)//8-14
            {
                p.xPosition = 425;
                p.yPosition = 570;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 605 && p.yPosition == 630) //10-6
            {
                p.xPosition = 365;
                p.yPosition = 630;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 605 && p.yPosition == 570) //11-32
            {
                p.xPosition = 545;
                p.yPosition = 450;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 305 && p.yPosition == 570) //16-36
            {
                p.xPosition = 305;
                p.yPosition = 450;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 245 && p.yPosition == 570) //17-3
            {
                p.xPosition = 185;
                p.yPosition = 630;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 365 && p.yPosition == 510) //26-87
            {
                p.xPosition = 425;
                p.yPosition = 150;
                p.reverseMove = 0;
                return 2;
            }
            else if(p.xPosition == 485 && p.yPosition == 510) //28-15
            {
                p.xPosition = 365;
                p.yPosition = 570;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 185 && p.yPosition == 450) //38-56
            {
                p.xPosition = 305;
                p.yPosition = 330;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 65 && p.yPosition == 390) //41-62
            {
                p.xPosition = 125;
                p.yPosition = 270;
                p.reverseMove = 0;
                return 2;
            }
            else if(p.xPosition == 605 && p.yPosition == 390) //50-72
            {
                p.xPosition = 545;
                p.yPosition = 210;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 185 && p.yPosition == 270) //63-21
            {
                p.xPosition = 65;
                p.yPosition = 510;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 485 && p.yPosition == 270) //68-49
            {
                p.xPosition = 545;
                p.yPosition = 390;
                p.reverseMove = 0;
                return 0;
            }
            else if(p.xPosition == 365 && p.yPosition == 210) //75-33
            {
                p.xPosition = 485;
                p.yPosition = 450;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 185 && p.yPosition == 210) //78-99
            {
                p.xPosition = 125;
                p.yPosition = 90;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 125 && p.yPosition == 150) //82-80
            {
                p.xPosition = 65;
                p.yPosition = 210;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 545 && p.yPosition == 150) //89-19
            {
                p.xPosition = 125;
                p.yPosition = 570;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 605 && p.yPosition == 150) //90-92
            {
                p.xPosition = 545;
                p.yPosition = 90;
                p.reverseMove = 1;
                return 2;
            }
            else if(p.xPosition == 365 && p.yPosition == 90) //95-58
            {
                p.xPosition = 185;
                p.yPosition = 330;
                p.reverseMove = 1;
                return 0;
            }
            else if(p.xPosition == 245 && p.yPosition == 90) //97-88
            {
                p.xPosition = 485;
                p.yPosition = 150;
                p.reverseMove = 0;
                return 0;
            }
        }
    return -1;
    }

    private void rollDice(int mode) {

        diceButton.setEnabled(false);
        Random rand = new Random();
        //int roll = rand.nextInt(6) + 1;
        //diceValue.setText(String.valueOf(roll));
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        final int[] secondsToWait = {1};
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Random rand = new Random();
                diceSound();
                int roll =rand.nextInt(6) + 1;
                diceValue.setText(" "+String.valueOf(roll));
                secondsToWait[0]--;
                if (secondsToWait[0] == 0) {
                    exec.shutdown();
                    setPlayerPosition(roll);
                }
            }
        };

        exec.scheduleAtFixedRate(task, 100, 100, TimeUnit.MILLISECONDS);
    

    }
    
    private void dispTurnMsg(String name)
    {
        JOptionPane.showMessageDialog(null, name+" please start the game by rolling the dice. Click on Roll Dice or press spacebar");
    }

    private void dispDiceScore(String name,int diceValue,int plId,int mode) {
        if ((plId == 1)||(mode==2))
            JOptionPane.showMessageDialog(null, name + ", you got " + diceValue);
        else
            JOptionPane.showMessageDialog(null, name + " got " + diceValue);
    }

    private void repeatTurnDisp(String name,int plId,int mode) {
        if ((plId == 1)||(mode==2))
            JOptionPane.showMessageDialog(null, name+" please Roll the Dice Again");
        else
            JOptionPane.showMessageDialog(null, name+" will Roll Again");
    }

    private void dispPlayerTurn(String name,int plId,int mode){
        if ((plId == 1)||(mode==2))
            JOptionPane.showMessageDialog(null, "It's now " + name + "'s Turn. Click on Roll Dice or press spacebar");
        else
            JOptionPane.showMessageDialog(null, "It's now "+name+"'s Turn.");
    }

    private void snakeSound(String name)
    {
        Random rand=new Random();
        int i=rand.nextInt(2)+1;
        AudioInputStream audioInputStream;
        try
        {
            if(i==1)
                audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/snakeatt.wav").getAbsoluteFile());
            else
                audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/rattlesnake.wav").getAbsoluteFile());
            // create clip reference
            Clip clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);
            clip.start();
            if(name=="Computer")
                JOptionPane.showMessageDialog(null, "A snake got "+name+" and loses some score.");
            else
                JOptionPane.showMessageDialog(null, "Oh no! A snake got "+name+" and loses some score.");
        }
        catch (Exception e)
        {

        }
    }

    private void ladderSound(String name)
    {
        AudioInputStream audioInputStream;
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/strclmb.wav").getAbsoluteFile());
            // create clip reference
            Clip clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);
            clip.start();
            if(name=="Computer")
                JOptionPane.showMessageDialog(null, name+" gets a ladder to climb and some bonus score.");
            else
                JOptionPane.showMessageDialog(null, "Nice! "+name+" gets a ladder to climb and some bonus score.");
        }
        catch (Exception e)
        {

        }
    }

    private void diceSound()
    {
        AudioInputStream audioInputStream;
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/diceroll.wav").getAbsoluteFile());
            // create clip reference
            Clip clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception e)
        {

        }
    }

    protected class Token {

        int xPosition, yPosition, turn, entry, reverseMove;
        String playerName;

        private Token() {

            xPosition = 65;
            yPosition = 630;
            turn = entry = reverseMove = 0;
        }
    }

    protected class ImagePanel extends JPanel {

        private Image image;

        private ImagePanel(int temp) {

            try {
                URL url;
                if(temp==1){
                url = this.getClass().getResource("Resources/image.jpg");
            }
                else if(temp==2){
                url = this.getClass().getResource("Resources/image1.jpg");
            }
                else if(temp==3){
                    url = this.getClass().getResource("Resources/image3.jpg");
            }
                else{
                    url = this.getClass().getResource("Resources/image2.jpg");
                }
                image = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void paintComponent(Graphics graphics) {
            if (image == null)
                return;
            graphics.drawImage(image, 50, 30, 600, 600, null);
        }
    }
    
    protected class Template {

        private int template;
        private Template() {
        template=0;
        }
        
        public void setTemp(int temp)
        {
            template=temp;
        }

        public int getTemp()
        {
            return template;
        }
    }
}
        
        
public class SnakesAndLadders {

    public static void main(String[] args) {

        Game g = new Game("Testing");
        //g.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //g.setVisible(true);

    }
    
}
