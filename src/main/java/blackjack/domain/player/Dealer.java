package blackjack.domain.player;

public class Dealer extends Player {
    private static final int SCORE_LOWER_BOUND = 16;
    private static final int FIRST_CARD_INDEX = 0;

    private Dealer(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Dealer create() {
        return new Dealer(Name.createDealerName(), new Hand());
    }

    @Override
    public boolean isDrawable() {
        return hand.isPlayable() && hand.calculateScore() <= SCORE_LOWER_BOUND;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public Result compareHandTo(final Hand hand) {
        throw new UnsupportedOperationException();
    }

    public Hand getHand() {
        return hand;
    }

    public int getCardCount() {
        return hand.getCardLetters().size();
    }

    public String getFirstCardLetter() {
        return hand.getCardLetters().get(FIRST_CARD_INDEX);
    }
}
