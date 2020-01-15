import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class CommandPrompt extends JTextField{

  private static final long serialVersionUID = 42l;

  public CommandPrompt(){
    setBounds(0, (int)Generator.size.getHeight()-64, (int)Generator.size.getWidth(), 32);
    addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        runCommand(getText());
        hideCP();
      }
    });
  }

  public void showCP(){
    setVisible(true);
    requestFocus();
  }

  public void hideCP(){
    setVisible(false);
    Generator.frame.requestFocus();
  }

  public boolean runCommand(String command){
    String args[] = command.split("\\s");
    switch(args[0]){
      case "set":
        Generator.cellList[Integer.valueOf(args[1])][Integer.valueOf(args[2])].setCol(Integer.valueOf(args[3]));
        return true;
      case "gen":
        Generator.generate(Integer.valueOf(args[1]));
        return true;
      default:
        return false;
    }
  }

}
