package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.hand.Score;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;
    private static final Score HIT_THRESHOLD_SCORE = new Score(HIT_THRESHOLD);

    private final Deck deck;

    public Dealer(final Deck deck) {
        super(new Name(DEALER_NAME));
        this.deck = deck;
    }

    public void dealInitialCards(final Players players) {
        dealRoundTo(players);
        dealRoundTo(players);
        drawAndReceive();
        drawAndReceive();
    }

    private void dealRoundTo(final Players players) {
        players.getPlayers().forEach(player -> player.receiveCard(deck.draw()));
    }

    public void dealCardTo(final Player player) {
        player.receiveCard(deck.draw());
    }

    public void drawAndReceive() {
        receiveCard(deck.draw());
    }

    public Card getOpenCard() {
        return getCards().getFirst();
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore().isLessThanOrEqualTo(HIT_THRESHOLD_SCORE);
    }
}
