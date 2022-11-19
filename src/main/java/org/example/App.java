package org.example;
import javax.swing.JScrollPane;
import java.awt.*;
import java.io.*;
import java.lang.Thread;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
//import java.time.LocalDateTime;
//import java.time.format;
//import java.time.format.DateTimeFormatter;


public class App extends JPanel{

     // no initial slash in file.txt

    //fields
    //private JButton reg;
    //private JButton custom;
    private static final boolean TRACE_MODE = false;
    static String botName = "super";
    private static Scanner scan = new Scanner(System.in);
    private static File myObj = new File("Frankie.txt");
    private static File file = new File("Transcript.txt");
    private static JTextArea chat = new JTextArea(25,30);
    private static JTextField Frankie = new JTextField(40);
    private static URL resourcesPath;
    private static boolean firstTime = true;
    //constructor
    public App() {
        //reg = new JButton("Button");
        //add(reg);
        //setLayout(new FlowLayout());
        try {
            if(myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }else {
                System.out.println("File already exists");
            }
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            }else{
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }


    }






    //methods
    public static void sentenceComprehension(String input) {

        ArrayList<String> wordsIn = readingFile();

        //String input = obtainingChat();
        int firstSplit = 0;
        int secondSplit = 0;
        String word;
        String finalSection;
        System.out.println(input);
        input.replace(",", " ");
        if(input.contains(" ")) {
            for(int i = 0; i < input.length(); i++) {



                if(input.charAt(i) == (' ') || input.charAt(i) == ('.') || input.charAt(i) == ('!') || input.charAt(i) == ('?')|| input.charAt(i) == (',')) {
                    secondSplit = i;
                    if(firstSplit == 0) {
                        word = input.substring(firstSplit, secondSplit);
                    }else {
                        word = input.substring(firstSplit + 1, secondSplit);
                    }
                    firstSplit = i;

//    				if(word.indexOf(word) != word.lastIndexOf(word)) {
//    					input.replace(word, "");
//    					input.concat(" " + word);
//    				}


                    try {
                        if(wordsIn.contains(word) == false) {
                            FileWriter myWriter = new FileWriter("Frankie.txt", true);
                            BufferedWriter bw = new BufferedWriter(myWriter);
                            bw.write(word);
                            bw.newLine();
                            bw.close();
                            //System.out.println(word);

                        }
                    }catch(IOException e) {
                        System.out.println("An error has occured");
                        e.printStackTrace();
                    }

                }
            }
            finalSection = input.substring(firstSplit + 1);
            if(finalSection.length() > 0) {
                try {
                    if(wordsIn.contains(finalSection) == false) {
                        FileWriter myWriter = new FileWriter("Frankie.txt", true);
                        BufferedWriter bw = new BufferedWriter(myWriter);

                        bw.write(finalSection);
                        bw.newLine();
                        bw.close();
                        //System.out.println(finalSection);
                    }
                } catch(IOException e) {
                    System.out.println("An error has occured");
                    e.printStackTrace();
                }
            }
        }else {
            try {
                if(wordsIn.contains(input) == false) {
                    FileWriter myWriter = new FileWriter("Frankie.txt", true);
                    BufferedWriter bw = new BufferedWriter(myWriter);

                    bw.write(input);
                    bw.newLine();
                    bw.close();
                }
            } catch(IOException e) {
                System.out.println("An error has occured");
                e.printStackTrace();
            }
            //System.out.println(input);
        }
    }

    public static ArrayList<String> readingFile(){
        ArrayList<String> listOfWords = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Frankie.txt"));
            try {
                String line = br.readLine();
                while (line != null) {
                    listOfWords.add(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        return listOfWords;
    }

    public static String fileReader() {
        String data = "";
        try {
            Scanner myReader = new Scanner(myObj);
            if(myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String nameGetting(String input) {
        String data = fileReader();

        if(data.length() == 0) {
            try {
                //System.out.println("What is your name?");
                String name1 = input;
                //if(myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter("Frankie.txt");
                BufferedWriter bw = new BufferedWriter(myWriter);
                bw.write("Name: " + name1);
                bw.newLine();
                bw.close();
                //System.exit(0);
                //}else {
                //System.out.println("File already exists");
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(data);
        //System.out.println(data);
        java.util.Date date = new java.util.Date();
        String dateTime = date + "";
        int location = dateTime.indexOf(":");
        int lastLocation = dateTime.lastIndexOf(":");
        //System.out.println(dateTime);
        String time = dateTime.substring(location-2, lastLocation+3);
        //System.out.println(time);
        String hour = dateTime.substring(location-2, location);
        //System.out.println(hour);
        int hourAsInt = Integer.parseInt(hour);
        String requiredName = "";
        try {
            Scanner reader = new Scanner(myObj);
            requiredName = reader.nextLine();

        } catch(IOException e){
            e.printStackTrace();
        }
        int namePortion = requiredName.indexOf(":");
        String nameReal = requiredName.substring(namePortion + 2);
        if(hourAsInt > 12 && hourAsInt < 18) {
            System.out.println("Good afternoon " + nameReal);
        } else if(hourAsInt > 18) {
            System.out.println("Good evening " + nameReal);
        } else {
            System.out.println("Good morning " + nameReal);



        }
        return nameReal + "|" + hour;
    }

    //private void frankensteinResponse(String string) {
    //    chat.append("Frankie: " + string + "\n");
    //}

    private static String frankieResponse(ArrayList<String> possibleInputs){
        String response = "";
        int size = possibleInputs.size();
        int random = (int)(Math.random() * size);
        if(possibleInputs.get(random).contains("|") == false) {
            if(possibleInputs.get(random).contains(":")) {
                int colonPlacement = possibleInputs.get(random).indexOf(":");
                response += possibleInputs.get(random).substring(colonPlacement+1);
                //response += possibleInputs.get(random);
            }
        }
        return response;
    }
    private static String greetings(String name, int hour) {

        String[] morningGreetings = {
                "Good morning " + name + ".",
                "Did you see the sunrise " + name + "?",
                "What a lovely morning " + name + "."
        };

        String[] afternoonGreetings = {
                "The sun is pretty high right now " + name + ".",
                "Good afternoon " + name + ".",
                "It's pretty bright out " + name + "."
        };

        String[] nightGreetings = {
                "Good evening " + name + ".",
                "It's quite late out " + name + ".",
                "It's almost time to call it a day " + name + "."
        };


        int random = (int)(Math.random() * 3);

        String greeting = "";

        if(hour <= 12) {
            System.out.println(morningGreetings[random]);
            greeting += morningGreetings[random];
        }else if(hour > 12 && hour < 18) {
            System.out.println(afternoonGreetings[random]);
            greeting += afternoonGreetings[random];

        }else {
            System.out.println(nightGreetings[random]);
            greeting += nightGreetings[random];

        }


        return greeting;
    }




    public static ArrayList<String> transcriptReader() throws FileNotFoundException {
        ArrayList<String> listOfQuestions = new ArrayList<>();

        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()) {
                //if (myReader.nextLine().contains("?") || myReader.nextLine().contains("what") || myReader.nextLine().contains("how")||myReader.nextLine().contains("where")||myReader.nextLine().contains("when")||myReader.nextLine().contains("who")){

                System.out.println(myReader.nextLine());
                listOfQuestions.add(myReader.nextLine());


            }
            if(myReader.hasNextLine() == false){
                listOfQuestions.add("I don't feel like talking right now");
            }


            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(listOfQuestions);
        return listOfQuestions;
    }


    public static void frameCreation() {
        JFrame frame = new JFrame();

        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLayout(new FlowLayout());
        frame.setSize(600,600);
        frame.setTitle("The Creation");
        frame.add(chat);
        frame.add(Frankie);
        final JButton b = new JButton("Talk to the Creation");
        final JButton b1 = new JButton("Get transcript");
        b.setBounds(50,100,95,30);
        b1.setBounds(50,120,95,30);
        frame.add(b);
        frame.add(b1);
        //JScrollPane s = new JScrollPane(chat, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //frame.add(s);
        chat.setSize(500, 400);
        chat.setLocation(2, 2);
        //JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //pane.setViewportView(Frankie);
        //Frankie.add(pane);
		//JScrollPane scroll = new JScrollPane(
		//		chat,
		//		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		//		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
		//		);
		//frame.add(scroll);
        Frankie.setSize(540, 30);
        Frankie.setLocation(2, 600);
        String data = fileReader();
        System.out.println(data);
        chat.setEditable(false);

        //String greeting = "";
        Frankie.setVisible(true);
        chat.setLineWrap(true);
        //frame.getContentPane().add(s);
        if(data.length() == 0) {
            System.out.println("Nothing is in the file, initiating tutorial");
            chat.append("Victor: Ah hello, I haven't seen you around here before, what is your name? \n");

        }


        String resourcesPath = getResourcesPath();
        System.out.println(resourcesPath);

        MagicBooleans.trace_mode = TRACE_MODE;
        final Bot bot = new Bot("super", resourcesPath);
        final Chat chatSession = new Chat(bot);
        bot.brain.nodeStats();
        final String textLine = "";
        final boolean[] firstMessage = {true};
        final boolean[] talkingToVictor = {true};

        b1.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    if (!Desktop.isDesktopSupported()) {
                        System.out.println("not supported");
                        return;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    if (file.exists()) {
                        desktop.open(file);
                    }
                } catch(Exception e1){
                    e1.printStackTrace();
                }
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                talkingToVictor[0] = !talkingToVictor[0];
                System.out.println("Button success");

                if(talkingToVictor[0] == true){
                    b.setText("Talk to the Creation");
                } else{
                    b.setText("Talk to Victor");
                }

            }
        });

        Frankie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String data = fileReader();
                String greeting = "";
                String name = "";
                String response1 = "";

                int hour = 0;

                if(data.length() == 0) {

                    System.out.println("check one passed");
                    String gtext = Frankie.getText();
                    String abc = nameGetting(gtext);
                    //String combined = nameGetting(gtext);
                    System.out.println(abc);
                    int locationSplit = abc.lastIndexOf("|");
                    name += abc.substring(0, locationSplit);
                    hour += Integer.parseInt(abc.substring(locationSplit + 1));
                    System.out.println(name);
                    System.out.println(hour);
                    greeting += greetings(name, hour);
                    chat.append("You: " + name + "\n");
                    Frankie.setText("");
                    chat.append("Victor: " + greeting + "\n Say, would you like to see my creation? \n");
                    chat.append("Victor: Ah... it needs a bit of time to itself, how about we talk for now?\n");

                }
                if(firstTime && talkingToVictor[0] == false) {

                    name = getName("real!?!");
                    hour = getHour();
                    greeting += greetings(name, hour);
                    java.util.Date date = new java.util.Date();
                    System.out.println("!?!?");
                    try {
                        FileWriter myWriter = new FileWriter("Transcript.txt", true);
                        BufferedWriter bw = new BufferedWriter(myWriter);
                        bw.write("|"+ date + "| \n");
                        //bw.newLine();
                        bw.close();
                        chat.append("Victor: " + greeting + "\n Nice meeting you again. \n");
                        System.out.println(data.length());
                        //sentenceComprehension(gtext);
                        Frankie.setText("");
                        firstTime = false;
                    }catch (IOException e){
                        e.printStackTrace();


                    //String gtext = Frankie.getText();

                    //chat.append("You: " + gtext + "\n");




                    }

                }
                if(talkingToVictor[0] == true){
                    String gtext = Frankie.getText();
                    try {
                        FileWriter myWriter = new FileWriter("Transcript.txt", true);
                        BufferedWriter bw = new BufferedWriter(myWriter);
                        bw.write("You: " + gtext + "\n");
                        //bw.newLine();
                        bw.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                    Frankie.setText("");
                    chat.append("You: " + gtext + "\n");
                    if ((gtext == null) || (gtext.length() < 1))
                        gtext = MagicStrings.null_input;
                    if (gtext.equals("Shut down this run")) {
                        System.exit(0);
                    } else {
                        String request = gtext;
                        if (MagicBooleans.trace_mode)
                            chat.append(
                                    "STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0)
                                            + ":TOPIC=" + chatSession.predicates.get("topic"));
                        String response = chatSession.multisentenceRespond(request);
                        while (response.contains("&lt;"))
                            response = response.replace("&lt;", "<");
                        while (response.contains("&gt;"))
                            response = response.replace("&gt;", ">");
                        try {
                            FileWriter myWriter = new FileWriter("Transcript.txt", true);
                            BufferedWriter bw = new BufferedWriter(myWriter);
                            bw.write("Victor: " + response + "\n");
                            //bw.newLine();
                            bw.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        chat.append("Victor: " + response + "\n");
                    }


                }else {


                    if(firstMessage[0]){
                        chat.append("Victor: Ready to talk to my creation? \n \n");
                        firstMessage[0] = false;
                    }
                    String gtext = Frankie.getText();
                    try {
                        FileWriter myWriter = new FileWriter("Transcript.txt", true);
                        BufferedWriter bw = new BufferedWriter(myWriter);
                        bw.write("You: " + gtext + "\n");
                        //bw.newLine();
                        bw.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Frankie.setText("");
                    chat.append("You: " + gtext + "\n");
                    //String response = null;
                    try {
                        response1 = frankieResponse(transcriptReader());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(response1);
                    //try {
                    //    FileWriter myWriter = new FileWriter("Transcript.txt", true);
                    //    BufferedWriter bw = new BufferedWriter(myWriter);
                    //    bw.write("The Creation: " + response1 + "\n");
                    //    //bw.newLine();
                    //    bw.close();
                    //}catch (IOException e){
                     //   e.printStackTrace();
                    //}
                    chat.append("The Creation: " + response1 + "\n");}
            }







        });






    }

    public static String getName(String input) {
        String requiredName = "";
        try {
            Scanner reader = new Scanner(myObj);
            requiredName = reader.nextLine();

        } catch(IOException e){
            e.printStackTrace();
        }
        int namePortion = requiredName.indexOf(":");
        String nameReal = requiredName.substring(namePortion + 2);
        return nameReal;
    }

    public static int getHour() {
        java.util.Date date = new java.util.Date();
        String dateTime = date + "";
        int location = dateTime.indexOf(":");
        int lastLocation = dateTime.lastIndexOf(":");
        //System.out.println(dateTime);
        String time = dateTime.substring(location-2, lastLocation+3);
        //System.out.println(time);
        String hour = dateTime.substring(location-2, location);
        //System.out.println(hour);
        int hourAsInt = Integer.parseInt(hour);
        return hourAsInt;
    }

    public static void creatingCreation() {
        frameCreation();

    }

    public void run() {
        creatingCreation();
        //sentenceComprehension(obtainingChat());

        //ArrayList<String> abc = readingFile();
        //System.out.println(abc);
    }

	/*TO DO:
	Create Victor Frankenstein tutorial essentially done
	create button to alternate between Victor and creation
	*/
    public BufferedReader getResourceReader(String resName){
        return new BufferedReader(new InputStreamReader(App.class.getResourceAsStream(resName)));
    }

    public static void main(String args[]) {
        try {

            String resourcesPath = getResourcesPath();

            System.out.println(resourcesPath);
            System.out.println("Check 2 successful");
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot("super", resourcesPath);

            bot.writeAIMLFiles();

        } catch (Exception e) {
            e.printStackTrace();
        }
        App run = new App();
        run.run();
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";

        return resourcesPath;
    }


}