package datastructure.arrayandlist.homework;
import java.io.*;
import java.util.Scanner;


/**
 * Created by Sang on 2017/10/9.
 */
public class Driver {

    private static final String LINE = "*************************************************\n";
    private DLList list = new DLList();

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.start();
    }
    //run the program
    public void start(){
        //run forever util 'exit' was input
        while(true){
            String command = readInput(menu(),"[apsedwr]","Invalid command!");
            switch (command){
                case "a":this.add();break;
                case "p":this.print();break;
                case "s":this.searchForName();break;
                case "e":this.searchForEmail();break;
                case "d":this.delete();break;
                case "w":this.writeToFile();break;
                case "r":this.readFromFile();break;
                default:break;
            }
            hold();
        }
    }
    //build the menu to string
    public String menu(){
        StringBuffer menu = new StringBuffer();
        menu.append(LINE);
        menu.append("Enter exit at any time to exit the program.\n");
        menu.append("'a': Add phone number to the list\n");
        menu.append("'p': Print the entire List\n");
        menu.append("'s': Search for Name\n");
        menu.append("'e': Search for email address\n");
        menu.append("'d': Delete an Entry\n");
        menu.append("'w': Write the list to file\n");
        menu.append("'r': Read from file\n");
        menu.append("What do you want: ");
        return menu.toString();
    }

    //this method take a hint that will be sent to user, the pattern you expect as answer and a warn if the answer
    //does not fit the pattern
    public String readInput(String text, String regex, String warn){
        System.out.println(text);
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        if(input.equals("exit")){
            System.exit(0);
        }
        if(input.matches(regex)){
            return input;
        }else{
            System.out.println(warn);
            return readInput(text,regex,warn);
        }
    }

    //just want the user to control when to input next instruction
    public void hold(){
        readInput("Press enter to continue..","","");
    }

    //ask user to input the information we need and call the linked list's add method
    public void add(){
        String name = readInput("Please input the full name:","\\w+[ ]{0,1}\\w+","Only Characters, numbers, spaces are accepted!");
        String email = readInput("Please input the email:","\\w+@\\w+.\\w+","The pattern should be xxx@yy.zz!");
        String phone = readInput("Please input the phone number:","\\d{3}-\\d{3}-\\d{4}","The phone number should be 000-000-0000!");
        Node node = new Node(name,email,phone);
        list.add(node);
        System.out.println("The phone number has been added!");
    }


    public void print(){
        System.out.println(LINE);
        System.out.println(list.toString());
    }

    //ask the user for a keyword and call the list's search method
    public void searchForName(){
        String keyword = readInput("Please input the name you want to find:","[\\w ]*","No such names found!");
        String node = list.searchForName(keyword);
        if(node != null){
            System.out.println(node);
        }else{
            System.out.println("No result are found!");
        }
    }



    public void searchForEmail(){
        String keyword = readInput("Please input the email you want to find:","[\\w@.]*","email are made of characters, numbers, @ and .");
        String node = list.searchForEmail(keyword);
        if(node != null){
            System.out.println(node);
        }else{
            System.out.println("No result are found!");
        }
    }

    public void delete(){
        print();
        String input = readInput("Input the index of the entry to delete or N to cancle","\\w+","Invalid input!");
        if(input.equals("N")){
            return;
        }
        try {
            int index = Integer.parseInt(input);
            list.delete(index);
        }catch (NumberFormatException e){
            System.out.println("Invalid input!");
            delete();
        }
    }

    // use built in method to serialize the linked list
    public void writeToFile(){
        String input = readInput("Please input a file name:","[a-z]+.[a-z]+","File name is illegal!");
        try {
            FileOutputStream fout = new FileOutputStream(input);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(list);
            oos.close();
            fout.close();
            System.out.println("Phone list has been stored in file " + input);
        }catch (FileNotFoundException e){
            System.out.println("File name is illegal!");
            writeToFile();
        }catch (IOException e){
            System.out.println("Fail to write to file, please try again!");
        }

    }

    public void readFromFile(){
        String input = readInput("Please input a file name:","[a-z]+.[a-z]+","File does not exist!");
        try {
            FileInputStream fit = new FileInputStream(input);
            ObjectInputStream ois = new ObjectInputStream(fit);
            DLList l = (DLList)ois.readObject();
            this.list = l;
            ois.close();
            fit.close();
            System.out.println("Phone list has been successfully read from file!");
        }catch (FileNotFoundException e){
            System.out.println("File name is illegal!");
            writeToFile();
        }catch (IOException e){
            System.out.println("Fail to write to file, please try again!");
        }catch (ClassNotFoundException e){
            System.out.println("Something unexpected happened!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
