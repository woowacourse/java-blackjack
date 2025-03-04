package blackjack.domain;

public class Player {
    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String validateName(String name) {
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("[ERROR]");
        }
        if (name.split(" ").length != 1) {
            throw new IllegalArgumentException("[ERROR]");
        }
        return name;
    }


}
