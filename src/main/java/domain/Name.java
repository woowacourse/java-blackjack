package domain;

public class Name {

    private final String name;

    public Name(final String name) {
        if (isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("이름이 입력되지 않았습니다");
        }
        this.name = name;
    }

    private static boolean isNull(String name) {
        return name == null;
    }

    public String getName() {
        return this.name;
    }
}
