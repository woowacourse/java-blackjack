public class Name {

    private final String name;

    public Name(final String name) {
        validateLength(name);
        this.name = name;
    }

    private static void validateLength(final String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException("[ERROR] 이름의 길이는 10글자 이하여야 합니다.");
        }
    }
}
