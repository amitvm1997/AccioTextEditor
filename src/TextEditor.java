import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame; //creates basic frame
    JMenuBar menuBar; // creates menubar
    JMenu file, edit; // initialize add option in menubar
    JMenuItem newFile, openFile, saveFile; // initialize the options file and edit menu contains
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea; // gives space to write in textarea

    TextEditor(){ //contructor
        //Initialized this frame
        frame = new JFrame();

        //Initialize Menu
        menuBar = new JMenuBar();

        file = new JMenu("File"); // shows name file in the menu bar
        edit  = new JMenu("Edit"); // shows name edit in the menu bar

        menuBar.add(file); //adding file and edit option in menubar
        menuBar.add(edit);

        //Initialize Text area
        textArea = new JTextArea();

        //Initialize menu items
        newFile = new JMenuItem("New file");
        openFile = new JMenuItem("Open file");
        saveFile = new JMenuItem("Save file");

        newFile.addActionListener(this); // initializing that these options will contain -----
        openFile.addActionListener(this); //----some function after clicking
        saveFile.addActionListener(this);


        file.add(newFile); // adding menu items in the options of file menubar
        file.add(openFile);
        file.add(saveFile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        cut.addActionListener(this);   // initializing that these options will contain -----
        copy.addActionListener(this);  //----some function after clicking
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        edit.add(cut);   // adding menu items in the options of edit menubar
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Initializing a scroll panel if too ma text is written and setting the diemnsions of the scroll panel
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        panel.add(scrollPane);
        frame.add(panel); //panel added to frame


        frame.setJMenuBar(menuBar); // menubar will be shown and added to frame
        frame.setBounds(100, 100, 400 , 400);
        frame.setVisible(true); // this will help to see all the added items on the result
        frame.setTitle("Text Editor"); // gives title to the program

    }
    public static void main(String[] args){
        TextEditor textEditor = new TextEditor(); // creating new file
    }

    @Override
    public void actionPerformed(ActionEvent e) // used to add the function needs to be performed
    {                                          // by all the menu items
        if(e.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor(); // creates new file
        }
        if(e.getSource()==saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            fileChooser.setApproveButtonText("Save");
            int chooseOption = fileChooser.showSaveDialog(null);

            if(chooseOption== JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                String filePath = file.getPath();
                try{
                    BufferedWriter outfile = null;
                    outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                }catch (Exception exception){
                    System.out.println(exception);
                }
            }
        }
        if(e.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption== JFileChooser.APPROVE_OPTION){
                File file  = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String intermediate = "", output = "";
                    while((intermediate = bufferedReader.readLine())!=null){
                        output += intermediate+ "\n";
                    }
                    textArea.setText(output);

                }catch (Exception exception){
                    System.out.println(exception);
                }
            }
        }
        if(e.getSource()==cut){       //predefined functions for cut
            textArea.cut();
        }
        if(e.getSource()==copy){                //predefined functions for copy
            textArea.copy();
        }
        if(e.getSource()==paste){               //predefined functions for paste
            textArea.paste();
        }
        if(e.getSource()==selectAll){           //predefined functions for selecting all the text
            textArea.selectAll();
        }
        if(e.getSource()==close){           // predefined functions for exiting the program
            System.exit(0);
        }

    }
}