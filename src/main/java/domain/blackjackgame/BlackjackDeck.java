package domain.blackjackgame;

import domain.strategy.DrawStrategy;
import exception.BlackJackException;
import java.util.Deque;

public class BlackjackDeck {

    private static final String INVALID_DECK_STATE = "유효하지 않은 덱 상태입니다";

    private final DrawStrategy drawStrategy;
    private final Deque<TrumpCard> deck;

    public BlackjackDeck(Deque<TrumpCard> deck, DrawStrategy drawStrategy) {
        this.deck = deck;
        this.drawStrategy = drawStrategy;
    }

    public TrumpCard drawCard() {
        validateDrawCard();

        return drawStrategy.draw(deck);
    }

    private void validateDrawCard() {
        if (deck.isEmpty()) {
            throw new BlackJackException(INVALID_DECK_STATE);
        }
    }
}
