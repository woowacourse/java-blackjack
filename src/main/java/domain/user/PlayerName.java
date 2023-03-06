package domain.user;

public class PlayerName {

    private final String name;

    public PlayerName(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String target) {
        if (target.length() < 1 || target.length() > 5) {
            throw new IllegalArgumentException("[ERROR]");
        }
    }

    public String getName() {
        return name;
    }

    public boolean isSameWith(final String otherName) {
        return otherName.equals(this.name);
    }
}
