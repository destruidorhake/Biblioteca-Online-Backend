package gustavo.gomes.bibliotecaOnlineBackend.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordUtilsService {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%&*()_+-=[]|,./?><";
    private static final int PASSWORD_LENGTH = 8;

    public static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        // Ensure the password contains at least one of each required type
        password.append(getRandomCharacter(LOWERCASE, random));
        password.append(getRandomCharacter(UPPERCASE, random));
        password.append(getRandomCharacter(DIGITS, random));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS, random));

        // Fill the rest of the password length with random characters from all categories
        String allCharacters = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;
        for (int i = password.length(); i < PASSWORD_LENGTH; i++) {
            password.append(getRandomCharacter(allCharacters, random));
        }

        // Shuffle the result to ensure randomness
        return shuffleString(password.toString(), random);
    }

    private static char getRandomCharacter(String characterSet, SecureRandom random) {
        int index = random.nextInt(characterSet.length());
        return characterSet.charAt(index);
    }

    private static String shuffleString(String input, SecureRandom random) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        java.util.Collections.shuffle(characters, random);
        StringBuilder shuffled = new StringBuilder(input.length());
        for (char c : characters) {
            shuffled.append(c);
        }
        return shuffled.toString();
    }
}
