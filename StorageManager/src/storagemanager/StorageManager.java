package storagemanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StorageManager {

    private static StorageTable storageTable;
    
    public static void main(String[] args) {
        StorageTable storageTable = new StorageTable();
        try {
            storageTable.deserialize();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found");
        }
        Scanner input = new Scanner(System.in);
        boolean programOn = true;
        String response;
        System.out.println("Hello, and welcome to Rocky Stream Storage Manager");

        while (programOn) {
            System.out.print("\nP - Print all storage boxes"
                    + "\nA - Insert into storage box"
                    + "\nR - Remove contents from a storage box"
                    + "\nC - Select all boxes owned by a particular client"
                    + "\nF - Find a box by ID and display its owner and contents"
                    + "\nQ - Quit and save workspace"
                    + "\nX - Quit and delete workspace\n\n"
                    + "Please select an option:");
            response = input.next().toUpperCase();
            System.out.println();
            input.nextLine();
            try {
                switch (response) {
                    case "P":
                        storageTable.printStorageBoxes();
                        break;
                    case"A":
                        try {
                            System.out.println("Please enter id:");
                            int id = input.nextInt();
                            input.nextLine();
                        
                            System.out.println("Please enter client:");
                            String client = input.nextLine();
                        
                            System.out.println("Please enter content:");
                            String content = input.nextLine();
                            Storage storage = new Storage(id, client, content);
                            storageTable.putStorage(id, storage);
                            System.out.println("\nStorage " +id+ " set!");                  
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nStorage ID is null or already exists, Storage not set.");
                        }
                        break;
                    case "R":
                        System.out.println("Please enter ID:");
                        int id = input.nextInt();
                        storageTable.remove(id);
                        System.out.println("Box " +id+ " is now removed.");
                        break;
                    case "C":
                        System.out.println("Please enter the name of the client:");
                        String client = input.nextLine();
                        storageTable.printClients(client);
                        break;
                    case "F":
                        System.out.println("Please enter ID:");
                        id = input.nextInt();
                        System.out.println("Box: " +id);
                        System.out.println("Contents: " +storageTable.getStorage(id).getContents());
                        System.out.println("Owner: " + storageTable.getStorage(id).getClient());
                        break;
                    case "Q":
                        storageTable.serialize();
                        System.out.println("Storage Manager is quitting, current storage is saved for next session.");
                        programOn = false;
                        break;
                    case "X":
                        storageTable = new StorageTable();
                        storageTable.serialize();
                        System.out.println("Storage Manager is quitting, all data is being erased.");
                        programOn = false;
                        break;
                    default:
                        System.out.println("\"" + response + "\" is not one of the menu options.\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found\n");
            } catch(IOException e){
                System.out.println("Error");
            }
        }
    }
}
