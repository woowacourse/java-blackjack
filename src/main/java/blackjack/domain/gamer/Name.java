package blackjack.domain.gamer;

import java.util.regex.Pattern;

public class Name {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[가-힣ㄱ-ㅎㅏ-ㅣ|a-z|A-Z]+");
    private final String name;

    public Name(String name){
       this.name = validateWord(name.trim());
    }

    private String validateWord(String nameValue) {
        if (!NAME_PATTERN.matcher(nameValue).matches()) {
            throw new IllegalArgumentException();
        }
        return nameValue;
    }

    @Override
    public String toString() {
        return name;
    }
}
