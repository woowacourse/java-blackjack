package blackjack.domain.player;

import java.util.regex.Pattern;

public class Name {
    private static final Pattern NON_SPECIAL_CHARACTERS = Pattern.compile("^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣\\s]*$");

    private final String name;

    public Name(String name) {
        checkNameBlank(name);
        checkNameSpecialCharacters(name);
        this.name = name;
    }

    private void checkNameBlank(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    private void checkNameSpecialCharacters(String name) {
        if (!NON_SPECIAL_CHARACTERS.matcher(name).matches()) {
            throw new IllegalArgumentException("[ERROR] 이름에는 특수문자가 들어갈 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
