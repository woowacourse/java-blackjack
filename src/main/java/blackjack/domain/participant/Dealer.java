package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.hand.Score;
import java.util.stream.IntStream;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;
    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;

    public Dealer(final Deck deck) {
        super(new Name(DEALER_NAME));
        this.deck = deck;
    }

    public void dealInitialCards(final Players players) {
        IntStream.range(0, INITIAL_CARD_COUNT)
                .forEach(i -> players.getPlayers()
                        .forEach(player -> player.receiveCard(deck.draw())));
        receiveCard(deck.draw());
    }

    public Card drawCard() {
        return deck.draw();
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore().isLessThanOrEqualTo(new Score(HIT_THRESHOLD));
    }

    public Card getOpenCard() {
        return getCards().get(0);
    }
}
