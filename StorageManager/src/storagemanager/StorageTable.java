package storagemanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class StorageTable extends HashMap<Integer, Storage> implements Serializable{
    
    /*Brief:
     Manually inserts a Storage object into the table using the specified key.
     Parameters:
     storageId - The unique key for the Storage object.
     Storage - The Storage object to insert into the table.
     Preconditions:
     storageId ≥ 0 and does not already exist in the table.
     Storage ≠ null
     Postconditions:
     The Storage has been inserted into the table with the indicated key.
     Throws:
     IllegalArgumentException: If the given storageID = null, or is already stored in the table.
     */
    public void putStorage(int storageId, Storage storage) throws IllegalArgumentException {
        if (storageId <= 0 || super.containsKey(storageId)) {
            throw new IllegalArgumentException("Storage ID is null or already exists");
        } else {
            super.put(storageId, storage);
        }
    }
    /*
     Brief:
        Retrieve the Storage from the table having the indicated storageID. If the requested storageID does not exist in the StorageTable, return null.
     Parameters:
        storageID - Key of the Storage to retrieve from the table.
     Returns:
        A Storage object with the given key, null otherwise.
     */

    public Storage getStorage(int storageID) {
        if(super.containsKey(storageID))
            return super.get(storageID);
        else
            return null;
    }
    
    public void serialize() throws FileNotFoundException, IOException{
        FileOutputStream file = new FileOutputStream("storage.obj");
        ObjectOutputStream outStream = new ObjectOutputStream(file);
        outStream.writeObject(this);
        outStream.close();
    }
    
    public void deserialize() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream("storage.obj");
        ObjectInputStream inStream = new ObjectInputStream(file);
        StorageTable storage;
        storage = (StorageTable) inStream.readObject();
        inStream.close();
        Iterator<Integer> i = storage.keySet().iterator();
        while(i.hasNext()){
            int storageId = i.next();
            this.putStorage(storageId, storage.getStorage(storageId));
        }
    }
    
    /**
     * Gets the String representation of the storages, which is a neatly 
     * formatted table of the storages with their content and client.
     * @return The String representation of this Storage object.
     */
    @Override
    public String toString(){
	String storages = "";
	Object[] keys = keySet().toArray();  
        for(int i = 0; i < keys.length; i++){
            int storageId = (Integer)keys[i];
            storages += String.format("%-10d%-19s%-11s\n", storageId, 
                    getStorage(storageId).getContents(), 
                    getStorage(storageId).getClient());
        }
        return String.format("\n%-10s%-19s%-11s", "Box#", "Content", "Owner")
                        + "\n----------------------------------------------------------------\n" + storages;
    }
    
    /**
     * Prints a neatly formatted table of each storage on its own line with
     * its content and client.
     */
    public void printStorageBoxes(){
        System.out.println(toString());
    }
    
    /**
     * Gets the String representation of the storages with a specific client.
     * @param client
     * @return The String representation of the storage object with specific client.
     */
    public String clientString(String client){
        String clients = "";
        Object[] keys = keySet().toArray();  
        for(int i = 0; i < keys.length; i++){
            int storageId = (Integer)keys[i];
            if(getStorage(storageId).getClient().equals(client)){
                clients += String.format("%-10d%-19s%-11s\n", storageId, 
                    getStorage(storageId).getContents(), 
                    getStorage(storageId).getClient());
            }
        }
        return String.format("\n%-10s%-19s%-11s", "Box#", "Content", "Owner")
                        + "\n----------------------------------------------------------------\n" + clients;
    }
    
    /**
     * Prints a neatly formatted table of storage(s) with a specific client on its own line with
     * its content and client.
     * @param client 
     */
    public void printClients(String client){
        System.out.println(clientString(client));
    }
}
