package player;

public class Name {
    public Name(String value) {
        if (value.length() > 5) {
            throw new IllegalArgumentException("이름은 1글자 이상 5글자 이하만 가능합니다.");
        }
    }
}
