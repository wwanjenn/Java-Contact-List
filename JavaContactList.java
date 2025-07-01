import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JavaContactList {

    // createContact(String name)	Adds a new Contact
    public void createContact(List<Contact> contacts, String name){
        Contact newContact = new Contact()
    }
    // deleteContact(String name)	Removes by name (or ID)
    // searchContact(String query)


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