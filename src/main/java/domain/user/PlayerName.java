package domain.user;

public class PlayerName {

    private final String name;

    public PlayerName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String target) {
        if (target.length() < 1 || target.length() > 5) {
            throw new IllegalArgumentException("[ERROR]");
        }
    }

    public String getName() {
        return name;
    }

    public boolean isSameWith(String otherName) {
        return otherName.equals(this.name);
    }
}
