package blackjack.domain.card;

public class AceCard extends Card{

    private static final String VALUE = "A";
    private static final int ACE_VALUE = 11;

    public AceCard(Pattern pattern) {
        super(pattern, VALUE);
    }

    @Override
    public int getValue() {
        return ACE_VALUE;
    }
}
