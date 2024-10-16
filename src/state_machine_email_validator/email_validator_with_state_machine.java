package state_machine_email_validator;

public class email_validator_with_state_machine {

    private String email;

    // Define possible states
    private enum State {
        START,
        LOCAL,
        DOMAIN,
        TLD,
        INVALID
    }

    public email_validator_with_state_machine(String email) {
        this.email = email;
    }

    public Boolean checker() {
        State state = State.START;

        // Validate email length
        if (email.length() == 0) {
            return false;
        }

        // Check if the first character is a letter
        if (!Character.isLetter(email.charAt(0))) {
            return false;
        }

        for (int i = 1; i < email.length(); i++) {
            char c = email.charAt(i);
            switch (state) {
                case START:
                    if (c == '@') {
                        return false; // Invalid if we directly go to '@'
                    } else if (Character.isDigit(c)) {
                        state = State.LOCAL; // Valid start of the local part
                    } else {
                        return false; // Invalid character
                    }
                    break;

                case LOCAL:
                    if (c == '@') {
                        state = State.DOMAIN; // Transition to domain part
                    } else if (Character.isDigit(c)) {
                        // Allow digits in the local part
                    } else {
                        return false; // Invalid character in local part
                    }
                    break;

                case DOMAIN:
                    if (c == '.') {
                        state = State.TLD; // Transition to TLD part
                    } else if (!Character.isLetter(c)) {
                        return false; // Invalid character in domain part
                    }
                    break;

                case TLD:
                    if (!Character.isLetter(c)) {
                        return false; // Invalid character in TLD
                    }
                    break;
            }
        }

        // Ensure that we end in the TLD state
        return state == State.TLD;
    }

    public static void main(String[] args) {
    	email_validator_with_state_machine ev = new email_validator_with_state_machine("f223737@nu.pk");
        System.out.println("Is valid email? " + ev.checker());
    }
}
