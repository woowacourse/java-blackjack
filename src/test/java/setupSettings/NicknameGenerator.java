package setupSettings;

import model.participant.Nickname;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class NicknameGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();

    private static final HashSet<Nickname> CACHE = new HashSet<>();

    public static Nickname generateNicknameBy(String nickname) {
        return new Nickname(nickname);
    }

    public static Nickname generateNickname() {
        return generateNicknames(1).getFirst();
    }

    public static List<Nickname> generateNicknames(int size) {
        if (CACHE.size() >= size) {
            return CACHE.stream().toList().subList(0, size);
        }
        for (int i = CACHE.size(); i < size; i++) {
            CACHE.add(generate());
        }
        return CACHE.stream().toList().subList(0, size);
    }

    private static Nickname generate() {
        String randomNickname = generateRandomNickname();
        return new Nickname(randomNickname);
    }

    private static String generateRandomNickname() {
        int length = RANDOM.nextInt(9) + 2;
        StringBuilder nickname = new StringBuilder();
        for (int i = 0; i < length; i++) {
            nickname.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return nickname.toString();
    }
}
