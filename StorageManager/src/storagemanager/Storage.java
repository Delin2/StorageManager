
package storagemanager;

import java.io.Serializable;

public class Storage implements Serializable{
    private int id;
    private String client;
    private String contents;
    
    /**
     * Creates a new Storage object with blank parameters
     */
    public Storage(){
        id = 0;
        client = null;
        contents = null;
    }
    
    /**
     * Creates a new Storage object using given values
     * @param id
            *       id of Storage
     * @param client
            *       Owner of Storage
     * @param contents 
            *       Contents of Storage 
     */
    public Storage(int id, String client, String contents){
        this.id = id;
        this.client = client;
        this.contents = contents;
    }
    
    /**
     * Getter for ID
     * @return 
     *      ID of Storage
     */
    public int getID(){
        return id;
    }
    
    /**
     * Setter for ID
     * @param id 
            * new ID 
     */
    public void setID(int id){
        this.id = id;
    }
    
    /**
     * Getter for client
     * @return 
     *      client of Storage
     */
    public String getClient(){
        return client;
    }
    
    /**
     * Setter for Client(owner)
     * @param client
            * new client 
     */
    public void setClient(String client){
        this.client = client;
    }
    
    /**
     * Getter for contents
     * @return 
     *      contents of Storage
     */
    public String getContents(){
        return contents;
    }
    
    /**
     * Setter for contents
     * @param contents 
            * new contents for storage 
     */
    public void setContents(String contents){
        this.contents = contents;
    }
}
