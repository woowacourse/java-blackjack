package model.card;

public class AceCard extends Card {
    private static final int MAX_ACE_VALUE = 11;

    public AceCard(CardSuit cardSuit) {
        super(CardRank.ACE, cardSuit);
    }

    @Override
    public boolean isAce() {
        return true;
    }

    public static int getAceMaxValue() {
        return MAX_ACE_VALUE;
    }
}
