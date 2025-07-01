import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

public class JavaContactBook {

    // createContact(String name)	Adds a new Contact
    public void createContact(List<Contact> contacts, String name){
        for(Contact contact: contacts){
            if(contact.getName().equalsIgnoreCase(name)){
                System.out.println("Contact with this name already Exists!");
                return;
            }
        }
        
        Contact newContact = new Contact(name);
        contacts.add(newContact);
    }

    // deleteContact(String name)	Removes by name (or ID)
    public boolean deleteContact(List<Contact> contacts, String name){
        Iterator<Contact> iterator = contacts.iterator();
        boolean removed = false;
        while(iterator.hasNext()){
            Contact contact=iterator.next();
            if(contact.getName().equalsIgnoreCase(name)){
                iterator.remove();
                removed = true;
                System.out.println("Contact Removed");
                break;
            }
        }
        if(!removed){
            System.out.println("Contact not found");
        }
        return removed;
    }
    
    // searchContact(String query)
    public List<Contact> searchContact(List<Contact> contacts, String query){
        List<Contact> found = new ArrayList<>();
        query = query.toLowerCase();

        for(Contact contact : contacts){
            if(contact.matchesQuery(query)) found.add(contact);
        }
        if(found.isEmpty()) System.out.println("Contact not found");

        return found;
    }

    public void saveContactsToJson(List<Contact> contacts){
        try {
            FileWriter writer = new FileWriter("contacts.json", true);
            Gson gson = new Gson();
            
            gson.toJson(contacts, writer);
            writer.close();

            System.out.println("Contacts Saved.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Contact> loadContactsFromJson(){
        List<Contact> contacts = new ArrayList<>();
        File file = new File("contacts.json");
        if(!file.exists()){
            System.out.println("File Doesn't Exist!");
            return contacts;
        }
        
        try{
            FileReader reader = new FileReader("contacts.json");
            Gson gson = new Gson();

            Type listType = new TypeToken<List<Contact>>() {}.getType();
            contacts = gson.fromJson(reader,listType);

            if(contacts == null){
                System.out.println("Warning! Content is empty -- starting fresh");
                contacts = new ArrayList<>();
                return contacts;
            }

            for(Contact contact: contacts){
                contact.printContact();
            }
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return contacts;
    }


    public static void main(String[] args) {
       
    }
}