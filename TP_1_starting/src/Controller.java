import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author maya5348
 *
 */
public class Controller {
    public static ArrayList<Copy> Copies = new ArrayList();
    public static ArrayList<Patron> Patrons= new ArrayList();
    public static Worker Worker;
    public static Log log;

    public static Copy getCopyById(String id){
        for(Copy c: Copies){
            if(c.getBarcode().equals(id)){
                return c;
            }
        }
        return null;
    }

    public static Patron getPatronById(String id){
        for(Patron p: Patrons){
            if(p.Id.equalsIgnoreCase(id)){
                return p;
            }
        }
        return null;
    }

    public static void AddBooksAndPatrons() throws FileNotFoundException {
        File patronsFile = new File("patrons.txt");
        File copiesFile = new File("copies.txt");
        Scanner patronsScanner = new Scanner(patronsFile);
        Scanner copiesScanner = new Scanner(copiesFile);

        while (patronsScanner.hasNextLine()){
            String line = patronsScanner.nextLine();
            String[] splittedLine = line.split("/");
            String id = splittedLine[0];
            String name = splittedLine[1];
            Patron p = new Patron(name, id);
            Patrons.add(p);
        }

        while (copiesScanner.hasNextLine()){
            String line = copiesScanner.nextLine();
            String[] splittedLine = line.split("/");
            String id = splittedLine[0];
            String title = splittedLine[1];
            String barcode = splittedLine[2];
            String status = splittedLine[3];
            Copy c = new Copy(id, title, barcode, status);
            Copies.add(c);
        }
    }

    public static boolean LogInWorker(String name, String password)throws FileNotFoundException {
        File f = new File("workers.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] splitedLine = line.split("/");
            if(splitedLine[0].equals(name) && splitedLine[1].equals(password)){
                Worker w = new Worker(name,password);
                Worker = w;
            }
        }
        if(Worker == null){
            System.out.println("Login failed. Please enter correct credentials");
            return false;
        }else{
            System.out.println("Worker successfully logged in");
            return  true;
        }

    }

    public static void AddCopy(Copy c){
        Copies.add(c);
    }

    public static void CheckIn(String copyId, String patronId){
        Copy c = getCopyById(copyId);
        Patron p = getPatronById(patronId);
        
        Worker.CheckInBook(c, p);
    }

    public static void CheckOut(String copyId, String patronId){
        Copy c = getCopyById(copyId);
        Patron p = getPatronById(patronId);
        Worker.CheckOutBook(c, p);
    }

    public static void AddHold(String fine, String copyId, String patronId){
        Copy c = getCopyById(copyId);
        Patron p = getPatronById(patronId);
        Manager.AddHold(fine, c, p);
    }

    public static void ClearHold(String copyId, String patronId){
        Copy c = getCopyById(copyId);
        Patron p = getPatronById(patronId);
        Worker.ClearHold(c, p);
        Manager.ClearHold(c, p);
    }

    public static void PrintOverdue(){
        Manager.PrintHold();
    }

    public static void PrintCopies(){

        for(Copy c : Copies){
        	
            System.out.println("Title: " + c.getTitle() + " Barcode:  " + c.getBarcode() + " Status:  " + c.getStatus());
          
        }

    }

    public static void PrintPatrons(){
        for(Patron p : Patrons){
            System.out.println("Name:  " + p.getName() + "  Id: " + p.getId());
        }
    }
}

