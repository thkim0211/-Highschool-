import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JOptionPane;
/**
 * Slot Machine with Image with enhanced user interface and more images
 * 
 * @author (Christopher Kim) 
 * @version (Created: Feb 10, 2017 Last Modified: March 26, 2017)
 */
public class SlotMachineImage2 extends JPanel
{
    private JPanel Game;
    private JPanel TotSlot;        //Where the total of 3 slot is going to be combined
    private JLabel FirstSlot;       //The first symbol
    private JLabel SecSlot;         //The second symbol..and so on. 
    private JLabel ThirdSlot;
    private JLabel out_howmuchmoney;   //label for in_initMoney
    private JLabel MoneyImage;
    private JLabel AmountMoney;
    private JTextArea output;     //The output section for computer
    private JScrollPane out_scroll;     //Added this scroll feature b/c output runs out of space. 
    //private JTextArea input;      //The input section for the code to get info
    private JButton DecisionCont;       //The button for continue
    private JButton DecisionNo;         //The button for quit
    private JButton finishInput;
    private JTextField in_initMoney;    //input for starting money of player
    private ImageIcon[] Images;         //List of possible shape in icon      New
   // private char[] Shape;               //List of possible shape for output for slot.
    private double nowMoney;            //player's current money
    private double cost;                //entrance fee that is taken away each time the player plays a round of game. 
    private boolean yesno;
    private boolean check;
    
    public SlotMachineImage2()
    {
        super();
        Game = new JPanel();
        TotSlot = new JPanel();
        FirstSlot = new JLabel("");
        SecSlot = new JLabel("");
        ThirdSlot = new JLabel("");
        out_howmuchmoney = new JLabel("How much money are you going to start with?");
        MoneyImage = new JLabel("");
        MoneyImage.setIcon(new ImageIcon("Images/Money.jpg"));
        AmountMoney = new JLabel("0");
        in_initMoney = new JTextField(10);
        output = new JTextArea(5,20);
        out_scroll = new JScrollPane();
        finishInput = new JButton("Click to finish entering info");
        DecisionCont = new JButton("");
        DecisionCont.setIcon(new ImageIcon("Images/Up.jpg"));
        DecisionNo = new JButton("Quit");
        Images = new ImageIcon[10];
        yesno = true;   
        check = false;
        
        
        DecisionNo.addActionListener(new QuitListen());
        finishInput.addActionListener(new FinishListen());
        DecisionCont.addActionListener(new ContListen());
        DecisionCont.setEnabled(false);            //User must input information to click for game. 
        DecisionNo.setEnabled(false);
        out_scroll.setViewportView(output);        //Sets the focus of the scrollbar to the most recent output
        output.setEditable(false);
        TotSlot.add(FirstSlot);
        TotSlot.add(SecSlot);
        TotSlot.add(ThirdSlot);
        Game.add(TotSlot);
        for(int x=0;x<10;x++)
        {
            char filename = (char)(x+'A');              //I have saved my image file name as A / B / C / D / E / F / G / H / J .jpg 
            String path = "Images/"+Character.toString(filename)+".jpg";        //In order to avoid redundancy and to use for loop, I came up with this algorithm to set this string as "Images/A.jpg" "Images/B.jpg" and so on 
            Images[x] = new ImageIcon(path,Character.toString(filename));                                //ImageIcon can take String type to retrieve the image in the specified path (the string) 
        }
        
        
        
        
        //Layout
        javax.swing.GroupLayout TotSlotLayout = new javax.swing.GroupLayout(TotSlot);
        TotSlot.setLayout(TotSlotLayout);
        TotSlotLayout.setHorizontalGroup(
            TotSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotSlotLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(FirstSlot)
                .addGap(305, 305, 305)
                .addComponent(SecSlot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
                .addComponent(ThirdSlot)
                .addGap(187, 187, 187))
        );
        TotSlotLayout.setVerticalGroup(
            TotSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotSlotLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(TotSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SecSlot)
                    .addComponent(FirstSlot)
                    .addComponent(ThirdSlot))
                .addContainerGap(69, Short.MAX_VALUE))      
        );
        
        //output.setColumns(20);
        //output.setFont(new java.awt.Font("Miriam", 1, 48)); // NOI18N
        //output.setRows(5);
      
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TotSlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DecisionCont)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(DecisionNo)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(AmountMoney)
                                .addGap(65, 65, 65)
                                .addComponent(MoneyImage)
                                .addGap(144, 144, 144))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(out_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(out_howmuchmoney)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(in_initMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(finishInput))
                                .addGap(49, 49, 49))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(TotSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(DecisionCont)))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AmountMoney)
                    .addComponent(MoneyImage))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(out_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(out_howmuchmoney)
                            .addComponent(in_initMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(finishInput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(DecisionNo))))
        );
        
    }
    public void getInfo()
    {
        nowMoney = Double.parseDouble(in_initMoney.getText());
        AmountMoney.setText(Double.toString(nowMoney));
        cost = nowMoney/1000.0;
        //output.append("The entrance fee is taken from your balance everytime you play this game. The fee calculated based on your balance is: $"+cost+"\n");
        //output.append("Let the Game start."+"\n");
    }
    public void modMoney(boolean check)
    {
        if(!check)
        {
            output.setText("FAIL");
            AmountMoney.setText(Double.toString(nowMoney));
            //output.setCaretPosition(output.getDocument().getLength());   //sets focus to the end line of output 
        }
        else
        {
            int moneyWon = (int)((Math.random()*9999)+1);
            nowMoney+=moneyWon;
            AmountMoney.setText(Double.toString(nowMoney));
            JOptionPane.showMessageDialog(null, "Congratulations, You Won $"+moneyWon);

            //output.append("You won $"+moneyWon+"!  Now you have"+nowMoney+"\n");
            //output.setCaretPosition(output.getDocument().getLength());
        }
    }
    public boolean checkWin()
    {
        String first = ((ImageIcon)FirstSlot.getIcon()).getDescription();           //I set the description as the file name in order to compare if user won or not
        String second = ((ImageIcon)SecSlot.getIcon()).getDescription();
        String third = ((ImageIcon)ThirdSlot.getIcon()).getDescription();
        return (first.equals(second) && first.equals(third));
        //return (FirstSlot.getIcon().getDescription().equals(SecSlot.getIcon().getDescription()) && FirstSlot.getIcon().getDescription().equals(ThirdSlot.getIcon().getDescription()));
        
    }
    public void Fee()
    {
        nowMoney-=cost;
    }
    public void endshow()
    {
        //output.append("You have chosen to end the Game. Your balance is $"+nowMoney+"\n");
        //output.setCaretPosition(output.getDocument().getLength());              //forces the scrollbar on the output section to stick to the bottom. 
        String message = "You have $"+nowMoney+"left.";
        JOptionPane.showMessageDialog(null, message);
    }
    public void theGame()
    {
        Fee();
        AmountMoney.setText(Double.toString(nowMoney));
        FirstSlot.setText("");
        SecSlot.setText("");
        ThirdSlot.setText("");
        int first = (int)(Math.random()*10);
        int sec = (int)(Math.random()*10);
        int third = (int)(Math.random()*10);
        int time = 500;
        /*for(int x = 0;x<100;x++)
        {
            try{
                Thread.sleep(time);
            }catch(InterruptedException ex){
                FirstSlot.setIcon(Images[x%10]);
                SecSlot.setIcon(Images[Math.abs((x%10-1))]);
                ThirdSlot.setIcon(Images[Math.abs((x%10-3))]);
                time+=10;
            }
            
            
        }*/
            
        FirstSlot.setIcon(Images[first]);
        try{
            Thread.sleep(time);
        }catch(InterruptedException ex){
        }
        SecSlot.setIcon(Images[sec]);
        try{
            Thread.sleep(time);
        }catch(InterruptedException ex){
        }
        ThirdSlot.setIcon(Images[third]);
        modMoney(checkWin());
    }  
    private class ContListen implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DecisionCont.setIcon(new ImageIcon("Images/Down.jpg"));
            yesno = true;
            DecisionNo.setEnabled(true);
            theGame();
            Timer time = new Timer(400,new ActionListener()            //sets delay when the slotmachine handle gets pushed down and come back up. User needs to be able to see
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    DecisionCont.setIcon(new ImageIcon("Images/Up.jpg"));
                }
            }
            );
            time.setRepeats(false);
            time.start();
        }
    }
    private class QuitListen implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            yesno = false;
            endshow();
        }
    }
    private class FinishListen implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            getInfo();
            DecisionCont.setEnabled(true);
        }
    }
    public static void main()
    {
        JFrame frame = new JFrame("SlotMachine with Image v2 -Christopher Kim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SlotMachineImage2());    
        frame.setSize(1493,733);
        frame.setVisible(true);
    }
}


