package domain;

import java.util.List;

public class Dealer {

    private static final int INIT_CARD_NUMBER = 2; //TODO 이름 생각해보기

    private final CardDeck cardDeck;
    private final Hands hands;

    public Dealer(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        this.hands = Hands.createEmptyPacket();
    }

    public void startDeal(final Players players) {
        for (int i = 0; i < INIT_CARD_NUMBER; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
            hands.add(cardDeck.pop());
        }
    }

    public void deal(final Player player, final Answer answer) {
        if (Answer.HIT.equals(answer)) {
            player.add(cardDeck.pop());
        }
    }

    public List<String> getCards() {
        return hands.getCards()
                .stream()
                .map(Card::toString)
                .toList();
    }
}
