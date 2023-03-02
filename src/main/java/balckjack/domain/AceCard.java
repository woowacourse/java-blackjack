package balckjack.domain;

public class AceCard extends Card{

    public AceCard() {
        super("A");
    }

    @Override
    protected int getValue() {
        return 11;
    }
}
