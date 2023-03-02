package blackjack.domain;

public class Name {
    private final String name;

    public Name(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }
}
