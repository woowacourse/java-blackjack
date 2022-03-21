package domain.player;

import domain.MatchResult;
import domain.card.PlayingCard;
import java.util.List;

public final class Dealer extends Player {
    private static final int MORE_CARD_CRITERIA = 16;
    private static final int FIRST_CARD_OPEN_INDEX = 0;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_DEALER_NAME);
    }

    public Dealer(String name) {
        super(name);
    }

    public Dealer(List<PlayingCard> cards) {
        super(DEFAULT_DEALER_NAME, cards);
    }

    @Override
    public boolean isHittable() {
        return getScore() <= MORE_CARD_CRITERIA;
    }

    @Override
    public List<PlayingCard> getOpenCards() {
        return List.of(playingCards.getCards().get(FIRST_CARD_OPEN_INDEX));
    }

    @Override
    public MatchResult match(Player gambler) {
        if (gambler.isBust()) {
            return MatchResult.WIN;
        }
        if (this.isBust()) {
            return MatchResult.LOSE;
        }
        return getMatchResultAfterBustCheck(gambler);
    }
}
