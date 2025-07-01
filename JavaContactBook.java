import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
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
    public boolean searchContact(List<Contact> contacts, String query){
        for(Contact contact : contacts){
            if(contact.getName().equalsIgnoreCase(query)){
                contact.printContact();
            }
        }
        return true
    }


    public static void main(String[] args) {
        try{
            FileReader reader = new FileReader("contacts.json");
            Gson gson = new Gson();

            Type listType = new TypeToken<List<Contact>>() {}.getType();
            List<Contact> contacts = gson.fromJson(reader,listType);
            for(Contact contact: contacts){
                contact.printContact();
            }
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}