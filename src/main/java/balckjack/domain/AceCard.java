package balckjack.domain;

public class AceCard extends Card{

    public AceCard(Pattern pattern) {
        super(pattern, "A");
    }

    @Override
    protected int getValue() {
        return 11;
    }
}
