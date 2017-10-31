package computer_architecture;

import java.util.Scanner;

/**
 * Created by Sang on 10/17/17.
 */
public class Driver {

    private Cache cache;
    private Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.run();
    }

    public void run(){
        cache = new Cache();
        boolean running = true;
        while (running){
            menu();
            String input = s.nextLine();
            switch (input){
                case "Q": running = false; break;
                case "R": read(); break;
                case "W": write(); break;
                case "D": display(); break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    public void read(){
        System.out.println("What address would you like read?");
        String input = s.nextLine();
        int address = Integer.parseInt(input,16);
        Result result = cache.read(address);
        System.out.printf("At that byte there is the value %x (Cache %s)\n",result.data,result.hit?"Hit":"Miss");
    }

    public void write(){
        System.out.println("What address would you like write?");
        String input = s.nextLine();
        int address = Integer.parseInt(input,16);
        System.out.println("What data would you like to write at that address?");
        input = s.nextLine();
        short data = Short.parseShort(input,16);
        Result result = cache.write(address, data);
        System.out.printf("Value %x has been written to address %x. (Cache %s)\n",result.data, address, result.hit?"Hit":"Miss");
    }

    public void display(){
        System.out.println(cache.display());
    }

    public void menu(){
        System.out.println("(R)ead, (W)rite, or (D)isplay Cache? (Q) to exit");
    }

}
