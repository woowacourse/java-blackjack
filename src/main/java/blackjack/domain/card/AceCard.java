package blackjack.domain.card;

public class AceCard extends Card{

    private static final String ACE_SYMBOL = "A";
    private static final int ACE_VALUE = 11;

    public AceCard(Pattern pattern) {
        super(pattern, ACE_SYMBOL);
    }

    @Override
    public int getValue() {
        return ACE_VALUE;
    }
}
