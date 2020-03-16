package domain.user;

public class Name {
    private String name;

    Name(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
        this.name = name;
    }

    public boolean isSame(String name) {
        return this.name.equals(name);
    }

    String getName() {
        return name;
    }
}
