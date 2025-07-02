import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    public void saveContactsToJson(List<Contact> contacts, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName, true);
            Gson gson = new Gson();
            
            gson.toJson(contacts, writer);
            writer.close();

            System.out.println("Contacts Saved.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Contact> loadContactsFromJson(String fileName){
        List<Contact> contacts = new ArrayList<>();
        File file = new File(fileName);
        
        if(!file.exists()){
            System.out.println("File Doesn't Exist!");
            return contacts;
        }
        try{
            FileReader reader = new FileReader(fileName);
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

    public void loopConsole(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Load File Name ===");
        String fileName = scanner.nextLine();
        scanner.nextLine();

        List<Contact> contacts = loadContactsFromJson(fileName);

        while(running){
            System.out.println("=== Contact Book ===");
            System.out.println("1. Create contact");
            System.out.println("2. Search contact");
            System.out.println("3. Update contact");
            System.out.println("4. Delete contact");
            System.out.println("5. Exit");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Create contact selected");
                    System.out.println("Name of Contact:");
                    String createName = scanner.nextLine();
                    createContact(contacts, createName);
                    break;
                case 2:
                    System.out.println("Search contact selected");
                    System.out.println("Search Query:");
                    String searchQuery = scanner.nextLine();
                    searchContact(contacts, searchQuery);
                    break;
                case 3:
                    Contact updateContact = null;
                    System.out.println("Update contact selected");
                    System.out.println("Update Name: ");
                    String updateName = scanner.nextLine();

                    for(Contact contact: contacts) {
                        if(contact.matchesQuery(updateName)){
                            updateContact = contact;
                        }
                    }

                    if(updateContact == null){
                        System.out.println("Contact not found");
                        break;
                    }

                    System.out.println("=== Update Type ===");
                    System.out.println("1. Update Name");
                    System.out.println("2. Update Number");
                    System.out.println("3. Update Email");
                    System.out.println("4. Update Nickname");
                    int choiceUpdate = scanner.nextInt();
                    scanner.nextLine();
                    switch(choiceUpdate){
                        case 1:
                            System.out.println("=== Update Name ===");
                            System.out.println("New Name:");
                            String newName = scanner.nextLine();
                            updateContact.updateName(newName);
                            break;
                        case 2:
                            System.out.println("=== Update Number ===");
                            System.out.println("Old Number:");
                            String oldNumber = scanner.nextLine();
                            System.out.println("New Number:");
                            String newNumber = scanner.nextLine();
                            updateContact.updatePhoneNumber(newNumber, oldNumber);
                            break;
                        case 3:
                            System.out.println("=== Update Email ===");
                            System.out.println("Old Email:");
                            String oldEmail = scanner.nextLine();
                            System.out.println("New Email:");
                            String newEmail = scanner.nextLine();
                            updateContact.updateEmail(newEmail, oldEmail);
                            break;
                        case 4:
                            System.out.println("=== Update Nickname ===");
                            System.out.println("Old Nickname:");
                            String oldNickname = scanner.nextLine();
                            System.out.println("New Nickname: ");
                            String newNickname = scanner.nextLine();
                            updateContact.updateNickname(newNickname, oldNickname);
                            break;
                        default:
                            System.out.println("Invalid update choice");
                            break;
                    }

                    break;
                case 4:
                    System.out.println("Delete contact selected");
                    Contact deleteContact = null;
                    System.out.println("Delete Name: ");
                    String deleteName = scanner.nextLine();

                    for(Contact contact: contacts) {
                        if(contact.matchesQuery(deleteName)){
                            deleteContact = contact;
                        }
                    }

                    if(updateContact == null){
                        System.out.println("Contact not found");
                        break;
                    }
                    
                    break;
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public static void main(String[] args) {
       
    }
}