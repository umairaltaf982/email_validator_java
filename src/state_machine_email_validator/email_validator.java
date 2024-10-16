package state_machine_email_validator;

public class email_validator {

    private String email;

    public email_validator(String email) {
        this.email = email;
    }

    public Boolean checker() {
        // Check if the first character is a letter
        if (!Character.isLetter(email.charAt(0))) {
            return false;
        }

        // Find the positions of '@' and '.'
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        // Check if '@' and '.' are in valid positions
        if (atIndex < 1 || dotIndex <= atIndex + 1) {
            return false;
        }

        // Check the local part (before '@')
        for (int i = 1; i < atIndex; i++) {
            char c = email.charAt(i);
            if (!Character.isDigit(c)) {
                return false; // Invalid character in local part
            }
        }

        // Check the domain part (between '@' and '.')
        for (int j = atIndex + 1; j < dotIndex; j++) {
            char c = email.charAt(j);
            if (!Character.isLetter(c)) {
                return false; // Invalid character in domain part
            }
        }

        // Check the TLD (after the last '.')
        for (int k = dotIndex + 1; k < email.length(); k++) {
            char c = email.charAt(k);
            if (!Character.isLetter(c)) {
                return false; // Invalid character in TLD
            }
        }

        return true; // If all checks passed, return true
    }

    public static void main(String[] args) {
    	email_validator ev = new email_validator("f223737@nu.pk");
        System.out.println("Is valid email? " + ev.checker());
    }
}
