import java.util.HashSet;
import java.util.Set;

public class Contact{
    private String name;
    Set<String> nicknames;
    Set<String> numbers ;
    Set<String> emails ;

    // no args for Gson
    public Contact(){
        nicknames = new HashSet<>();
        numbers = new HashSet<>();
        emails = new HashSet<>();
    }

    public Contact(String name) {
        this.name = name;
        nicknames = new HashSet<>();
        numbers = new HashSet<>();
        emails = new HashSet<>();
    }

    public String getName(){
        return name;
    }

    public Set<String> getNumbers(){
        return numbers;
    }
    
    public Set<String> getNicknames(){
        return nicknames;
    }

    public Set<String> getEmails(){
        return emails;
    }

    public boolean matchesQuery(String query){
        if(name.toLowerCase().contains(query)) return true;
        for(String nickname : nicknames){
            if(nickname.toLowerCase().contains(query)) return true;
        }
        for(String number : numbers){
            if(number.toLowerCase().contains(query)) return true;
        }
        for(String email : emails){
            if(email.toLowerCase().contains(query)) return true;
        }
        return false;
    }

    public void printContact(){
        System.out.println(name);
        System.out.println(nicknames);
        System.out.println(numbers);
        System.out.println(emails);
    }

    public void updateName(String newName){
        System.out.println("Updated Name: " + newName);
        this.name = newName;
    }

    public void addPhoneNumber(String newNumber){
        System.out.println("Added Number: " + newNumber);
        numbers.add(newNumber);
    }

    public void addNickname(String newNickname){
        System.out.println("Added Nickname: " + newNickname);
        nicknames.add(newNickname);
    }

    public void addEmail(String newEmail){
        System.out.println("Added Email: " + newEmail);
        emails.add(newEmail);
    }

    public void deletePhoneNumber(String number){
        if(numbers.contains(number)){
            numbers.remove(number);
            System.out.println("Number removed");
        } else {
            System.out.println("Number not found");
        }
        
    }

    public void deleteNickname(String nickname){
        if(nicknames.contains(nickname)){
            nicknames.remove(nickname);
            System.out.println("Nickname removed");
        } else {
            System.out.println("Nickname not found");
        }
    }

    public void deleteEmail(String email){
        if(emails.contains(email)){
            emails.remove(email);
            System.out.println("Email removed");
        } else {
            System.out.println("Email not found");
        }
    }

    public void updatePhoneNumber(String newNumber, String oldNumber){
        if(numbers.contains(oldNumber)){
            numbers.remove(oldNumber);
            numbers.add(newNumber);
            System.out.println("Updated Number: " + numbers);
        } else {
            System.out.println("Number not found");
        }
        
    }

    public void updateNickname(String newNickname, String oldNickname){        
        if(nicknames.contains(oldNickname)){
            nicknames.remove(oldNickname);
            nicknames.add(newNickname);
            System.out.println("Updated Nickname: " + nicknames);
        } else {
            System.out.println("Nickname not found");
        }
        
    }

    public void updateEmail(String newEmail, String oldEmail){
        if(emails.contains(oldEmail)){
            emails.remove(oldEmail);
            emails.add(newEmail);
            System.out.println("Updated Email: " + emails);
        } else {
            System.out.println("Email not found");
        }
        
    }
}

// updateName(String oldName, String newName)	Changes name + map key
// addPhoneNumber(String name, String number)	Adds to existing contact
// removePhoneNumber(String name, String number)	Removes specific number
// updatePhoneNumber(String name, String old, String new)	Replaces one number
// searchContact(String query)