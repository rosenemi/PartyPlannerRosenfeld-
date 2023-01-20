import java.util.Scanner; // Import the Scanner class to read text files
/**
  * @author Emily Rosenfeld 
  * This runs the part.java file and creates the user interface that would allow     
  * people to actually run and use the program 
**/
class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Party hope = new Party();
    hope.Upload(); //upload data first 
    System.out.println("Welcome to the Planning Party Database for the IHRPS. \nWould you like to add an new party guests? \nType 1 if yes. Type 2 if no.");
    int addQ = scan.nextInt();
    while (addQ == 1){
      hope.Add();
      System.out.println("Would you like to add another person? \nType 1 if yes. Type 2 if no.");
      addQ = scan.nextInt();
    }
    hope.Sort();
    System.out.println("All guests have been placed at tables.");
    System.out.println("How would you like to view the roster, by Company or Table or individual. \nType 1 for Company and 2 for Table. \nType 3 if you want to look up an individual person. \nType 4 if you are finished viewing the database.");
    int view = scan.nextInt();
    while (view < 4){
      if(view == 1){
        hope.PrintComp();
      }
      if (view == 2){
        hope.PrintTable();
      }
      if (view == 3){
        hope.FindPerson();
      }
      System.out.println("How would you like to view the roster, by Company or Table or individual. \nType 1 for Company and 2 for Table. \nType 3 if you want to look up an individual person. \nType 4 if you are finished viewing the database.");
    view = scan.nextInt();
    }
    
  }
}