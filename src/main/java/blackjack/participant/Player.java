package blackjack.participant;

public class Player extends Participant{
    private final Name name;

    public Player(String inputName) {
        this(new Name(inputName));
    }

    private Player(Name name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name.toString();
    }
}