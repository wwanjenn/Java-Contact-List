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
import java.util.Iterator;

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
    public void searchContact(List<Contact> contacts, String query){
        List<Contact> found = new ArrayList<>();
        query = query.toLowerCase();

        for(Contact contact : contacts){
            if(contact.matchesQuery(query)) found.add(contact);
        }
        if(found.isEmpty()) System.out.println("Contact not found");
        for(Contact f: found){
            f.printContact();
        }
    }

    public void saveContactsToJson(List<Contact> contacts, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);
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
        int maxID = 0;
        for(Contact contact: contacts){
            if(contact.getID() > maxID) maxID = contact.getID();
        }
        Contact.setNextID(maxID);
        return contacts;
    }

    public void loopConsole(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        boolean saved = true;

        System.out.println("=== Load File Name ===");
        String fileName = parseStringChoice(scanner, "Filename: ");

        List<Contact> contacts = loadContactsFromJson(fileName);
        if(contacts == null) contacts = new ArrayList<>();

        while(running){
            System.out.println("=== Contact Book ===");
            System.out.println("1. Create contact");
            System.out.println("2. Search contact");
            System.out.println("3. Update contact");
            System.out.println("4. Delete contact");
            System.out.println("5. View all contacts");
            System.out.println("6. Save");
            System.out.println("7. Exit");
    
            int choice = parseIntChoice(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.println("Create contact selected");
                    String createName = parseStringChoice(scanner, "Name of Contact: ");
                    createContact(contacts, createName);
                    saved = false;                    
                    break;
                case 2:
                    System.out.println("Search contact selected");
                    String searchQuery = parseStringChoice(scanner, "Search Query: ");
                    searchContact(contacts, searchQuery);
                    break;
                case 3:
                    Contact updateContact = null;
                    System.out.println("Update contact selected");
                    String updateName = parseStringChoice(scanner, "Update Name: ");

                    for(Contact contact: contacts) {
                        if(contact.getName().equalsIgnoreCase(updateName)){
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
                    System.out.println("5. Add Number");
                    System.out.println("6. Add Email");
                    System.out.println("7. Add Nickname");
                    System.out.println("8. Delete Number");
                    System.out.println("9. Delete Email");
                    System.out.println("0. Delete Nickname");
                    
                    int choiceUpdate = parseIntChoice(scanner, "Choose an option: ");
                    switch(choiceUpdate){
                        case 1:
                            System.out.println("=== Update Name ===");
                            System.out.println("Old Name: " + updateContact.getName());
                            System.out.println("New Name: ");
                            String newName = parseStringChoice(scanner, "New Name: ");
                            updateContact.updateName(newName);
                            saved = false;
                            break;
                        case 2:
                            System.out.println("=== Update Number ===");
                            System.out.println("Old Numbers: " + updateContact.getNumbers());
                            String oldNumber = parseStringChoice(scanner, "Old Number: ");
                            String newNumber = parseStringChoice(scanner, "New Number: ");
                            updateContact.updatePhoneNumber(newNumber, oldNumber);
                            saved = false;
                            break;
                        case 3:
                            System.out.println("=== Update Email ===");
                            System.out.println("Old Emails: " + updateContact.getEmails());
                            String oldEmail = parseStringChoice(scanner, "Old Email: ");
                            String newEmail = parseStringChoice(scanner, "New Email: ");
                            updateContact.updateEmail(newEmail, oldEmail);
                            saved = false;
                            break;
                        case 4:
                            System.out.println("=== Update Nickname ===");
                            System.out.println("Old Nicknames: " + updateContact.getNicknames());
                            String oldNickname = parseStringChoice(scanner, "Old Nickname: ");
                            String newNickname = parseStringChoice(scanner, "New Nickname: ");
                            updateContact.updateNickname(newNickname, oldNickname);
                            saved = false;
                            break;
                        case 5:
                            System.out.println("=== Add Number ===");
                            System.out.println("Old Numbers: " + updateContact.getNumbers());
                            String addNumber = parseStringChoice(scanner, "New Number: ");
                            updateContact.addPhoneNumber(addNumber);
                            saved = false;
                            break;
                        case 6:
                            System.out.println("=== Add Email ===");
                            System.out.println("Old Emails: " + updateContact.getEmails());
                            String addEmail = parseStringChoice(scanner, "New Email: ");
                            updateContact.addEmail(addEmail);
                            saved = false;
                            break;
                        case 7:
                            System.out.println("=== Add Nickname ===");
                            System.out.println("Old Nicknames: " + updateContact.getNicknames());
                            String addNickname = parseStringChoice(scanner, "New Nickname: ");
                            updateContact.addNickname(addNickname);
                            saved = false;
                            break;
                        case 8:
                            System.out.println("=== Delete Number ===");
                            System.out.println("Old Numbers: " + updateContact.getNumbers());
                            String delNumber = parseStringChoice(scanner, "Delete Number: ");
                            updateContact.deletePhoneNumber(delNumber);
                            saved = false;
                            break;
                        case 9:
                            System.out.println("=== Delete Email ===");
                            System.out.println("Old Emails: " + updateContact.getEmails());
                            String delEmail = parseStringChoice(scanner, "Delete Email: ");
                            updateContact.deletePhoneNumber(delEmail);
                            saved = false;
                            break;
                        case 0:
                            System.out.println("=== Update Nickname ===");
                            System.out.println("Old Nicknames: " + updateContact.getNicknames());
                            String delNickname = parseStringChoice(scanner, "Delete Nickname: ");
                            updateContact.deleteNickname(delNickname);
                            saved = false;
                            break;

                        default:
                            System.out.println("Invalid update choice");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Delete contact selected");
                    Contact deleteContact = null;
                    System.out.println("Delete Contact Name: ");
                    String deleteContactName = parseStringChoice(scanner, "Delete Contact Name: ");

                    for(Contact contact: contacts) {
                        if(contact.getName().equalsIgnoreCase(deleteContactName)){
                            deleteContact = contact;
                        }
                    }
                    if(deleteContact == null){
                        System.out.println("Contact not found");
                        break;
                    }
                    deleteContact(contacts, deleteContactName);
                    saved = false;                    
                    break;
                case 5:
                    System.out.println("Contact List: " + fileName);
                    System.out.println("Total Contacts: " + contacts.size());
                    for(Contact contact: contacts){
                        contact.printContact();
                    }
                    break; 
                case 6:
                    System.out.println("Saving...");
                    saveContactsToJson(contacts, fileName);
                    saved = true;
                    break;
                case 7:
                    System.out.println("Exiting...");
                    if(saved) running = false;
                    else System.out.println("Save changes before exiting");
                    break;
                default:
                    System.out.println("Invalid option");

                    break;
            }
        }
    }

    public int parseIntChoice(Scanner scanner, String prompt){
        while(true){
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number");
            }
        }
    }
    
    public String parseStringChoice(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("Input cannot be empty. Please try again.");
            } else {
                return input;
            }
        }
    }

    public Boolean parseConfirmation(Scanner scanner, String prompt){
        while (true) {
            System.out.println(prompt + "%nAre you sure?(Y/N)");
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("y")) return true;
            else if(input.equalsIgnoreCase("n")) return false;
            else System.out.println("Please enter Y or N");
        }
    }


    public static void main(String[] args) {
       new JavaContactBook().loopConsole();
    }
}

// javac -cp "lib/gson-2.10.1.jar" JavaContactBook.java Contact.java
// java -cp ".;lib/gson-2.10.1.jar" JavaContactBook