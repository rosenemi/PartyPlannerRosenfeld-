import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; 
/**
  *@author Emily Rosenfeld
  * This program allows users to add and view the seating arrangments for a party
  * data is imported from companies.txt

**/

public class Party{
  Scanner scan = new Scanner(System.in);
  /* Static variables in the program*/
  int max = 100;
  int numT = 10;
  int numS = 10;
  int numP = 0;
  People[] ppl = new People[max]; //Array of all the people registred to come to the party
  int[][] room = new int[numT][numS]; //Array of the tables and chairs that people will be asignned to

  /* This is a method that uploads the data provided in partyguests.txt */
  public void Upload(){
    try {
      File myObj = new File("partyguests.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String[] compS = myReader.nextLine().split(",");
        numP = Integer.parseInt(compS[0]);
        String uName = compS[1];
        String uFname = compS[2];
        int uComp = Integer.parseInt(compS[3]);
        People x = new People(uFname, uName, -1, numP, uComp, 0); //creates a new object, called people, for each line in the data file
        ppl[numP-1] = x; // adds that People object to the array; the -1 is for indexing purposes
        
      }
      myReader.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
  
/* This method assigns each person a table and seat. If the data is in order by company no two ppl from the same company will be sat at the same table */
  public void Sort(){
    int i = 0;
    while (i < numP){
      int tId = (i+numT)%numT; 
      int sId = (i+numS)/numS -1;
      room[tId][sId] = ppl[i].pId; //places the persons ID (unique to them) at that postion at the table
      ppl[i].table = tId; //updates the persons table number 
      ppl[i].seat = sId; // same as above but with eat 
      i++;
    }
  }

  /* This method allows users to add more guests to the party */
  public void Add(){
    System.out.println("What is the first name of the person you want to register");
    Scanner ss = new Scanner(System.in);
    String nFname = ss.nextLine();
    System.out.println("What is their last name");
    String nLname = ss.nextLine();
    System.out.println("What company do they work for? Use the table below to put in the company Id of the person.");
    System.out.println("01-Wal-Mart, 02-Kroger, 03-Amazon, 04-Lowes, 05-Best Western, 06-KMart, 07-Fusian, 08-Heinz 09-Gucci 10-Prada, 11-Nike, 12-Dodge, 13-Maserati, 14-Razor, 15-AMD, 16-Razer");
    int nComp = scan.nextInt();
    numP++;
    People x = new People(nFname, nLname, 0, numP, nComp, 0);
    ppl[numP-1] = x;
    
  }

  /* This method allows users to print out the names of the people sitting at a table */
  public void PrintTable(){
    System.out.println("What table do you want to print out?");
    int fTable = scan.nextInt() - 1;
    for (int i = 0; i < numP; i++){
      if (ppl[i].table == fTable){
        System.out.println(ppl[i].fName + " " + ppl[i].lName);
      } // closes if
    } // closes for
    
  } // closes method PrintTable 

  /* This method allows users to print out the names and table and seat places of the people in that company */
  public void PrintComp(){
    System.out.println("What company do you want to print out? Use the table below to put in the company Id of the person.");
    System.out.println("01-Wal-Mart, 02-Kroger, 03-Amazon, 04-Lowes, 05-Best Western, 06-KMart, 07-Fusian, 08-Heinz 09-Gucci 10-Prada, 11-Nike, 12-Dodge, 13-Maserati, 14-Razor, 15-AMD, 16-Razer");
    int fComp = scan.nextInt();
    for (int x = 0; x < numP; x++){
      if (ppl[x].companyNum == fComp){
        System.out.println(ppl[x].fName + " " + ppl[x].lName + " Table " + (ppl[x].table + 1) + " Seat " + (ppl[x].seat + 1)); // the plus one on table allows the indexing to be correct in the array while still appearing normal for users, ie no seat or table zero
      } // closes if
    } // closes for
    
  } // closes method PrintComp

  /* This method allows users to find an indiviudal person's seat assingment */
  public void FindPerson(){
    Scanner s = new Scanner(System.in);
    boolean pFound = false;
    System.out.println("What person do you want to find?");
    String fFname = s.nextLine();
    for (int x = 0; x < numP; x++){
      if (ppl[x].fName.equals(fFname)){
        System.out.println(ppl[x].fName + " " +  ppl[x].lName + " Table " + (ppl[x].table + 1) + " Seat " + (ppl[x].seat + 1));
        pFound = true;
      }
    }
    if (pFound = false){ // I added this after the for loop so it didn't print out person not found 99 times (which is what it did before)
      System.out.println("Person not found");
    }
    
  }

}
/* every person uploaded or added manually becomes a people object */
class People{
  String fName;
  String lName;
  int pId;
  int table;
  int seat;
  int companyNum;
  public People(String n, String l, int t, int p, int c, int s){
    fName = n;
    lName = l;
    pId = p;
    table = t;
    companyNum = c;
    seat = s;
  }

/* Orginal idea that I didn't end up using
class Company{
  String[] company = new String[10];
  int compNum;
  public Company(String[] c, int n){
    company = c;
    compNum = n;
  } */
}