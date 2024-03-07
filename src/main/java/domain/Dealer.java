package domain;

import domain.card.Card;
import java.util.List;

public class Dealer {
    private static final String NAME = "딜러";
    private static final int INIT_HANDS_SIZE = 2;

    private final CardDeck cardDeck;
    private final Hands hands;

    public Dealer(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        this.hands = Hands.createEmptyPacket();
    }

    public Dealer(final CardDeck cardDeck, final Hands hands) {
        this.cardDeck = cardDeck;
        this.hands = hands;
    }

    public void startDeal(final Players players) {
        for (int i = 0; i < INIT_HANDS_SIZE; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
            hands.add(cardDeck.pop());
        }
    }

    public void deal(final Player player, final Answer answer) {
        if (Answer.HIT.equals(answer)) {
            player.add(cardDeck.pop());
        }
    }

    public void deal() {
        hands.add(cardDeck.pop());
    }

    public List<String> getCards() {
        return hands.getCards()
                .stream()
                .map(Card::toString)
                .toList();
    }

    public int getTotalCardSum() {
        return hands.sum();
    }

    public Hands getHands() {
        return hands;
    }

    public String getName() {
        return NAME;
    }
}
