import java.util.Set;

public class Contact{
    String name;
    Set<String> nicknames;
    Set<String> numbers;
    Set<String> emails;
    
    public void printContact(){
        System.out.println(name);
        System.out.println(nicknames);
        System.out.println(numbers);
        System.out.println(emails);
    }

    public void updateName(String oldName, String newName){
        this.name = newName;
    }

    public void addPhoneNumber(String newNumber){
        numbers.add(newNumber);
    }

    public void addNickname(String newNickname){
        nicknames.add(newNickname);
    }

    public void addEmail(String newEmail){
        emails.add(newEmail);
    }

    public void deletePhoneNumber(String number){
        numbers.remove(number);
    }

    public void deleteNickname(String nickname){
        nicknames.remove(nickname);
    }

    public void deleteEmail(String email){
        emails.remove(email);
    }

    public void updatePhoneNumber(String newNumber, String oldNumber){
        numbers.remove(oldNumber);
        numbers.add(newNumber);
    }

    public void updateNickname(String newNickname, String oldNickname){
        nicknames.remove(oldNickname);
        nicknames.add(newNickname);
    }

    public void updateEmail(String newEmail, String oldEmail){
        emails.remove(oldEmail);
        emails.add(newEmail);
    }
}

// updateName(String oldName, String newName)	Changes name + map key
// addPhoneNumber(String name, String number)	Adds to existing contact
// removePhoneNumber(String name, String number)	Removes specific number
// updatePhoneNumber(String name, String old, String new)	Replaces one number
// searchContact(String query)