package blackjack.domain.player;

public class Gambler extends Player {
    private static final int GAMBLER_GET_CARD_UPPER_BOUND = 21;

    public Gambler(final String name) {
        super(name);
    }

    @Override
    public boolean isFinished() {
        return playingCards.getCardSum() >= GAMBLER_GET_CARD_UPPER_BOUND;
    }
}
