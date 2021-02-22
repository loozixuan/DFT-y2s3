
public class Password {

    private String password;

    public Password(String password) throws InvalidPasswordException {
        if (password == null || password.equals("")) {
            throw new InvalidPasswordException("Invalid Password: password cannot be null or empty");
        }

        int countDigits = 0;
        int countLetters = 0;
        for (int i = 1; i < password.length(); i++) {
            char chr = password.charAt(i);
            if (Character.isDigit(chr)) {
                countDigits++;
            } else if (Character.isLetter(chr)) {
                countLetters++;
            }
        }

        int countAlphaNumeric = countDigits + countLetters;

        StringBuilder sb = new StringBuilder();
        if (countDigits == 0) {
            sb.append("Your password should have at least 1 digit.\n");
        }

        if (countLetters == 0) {
            sb.append("Your password should have at least 1 letter.\n");
        }

        if (countAlphaNumeric < 7) {
            sb.append("Your password should have at least 7 alpha-numeric characters.\n");
        }

        String errMsg = sb.toString();
        if (!errMsg.equals("")) {
            throw new InvalidPasswordException(errMsg);
        }
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
