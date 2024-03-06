package domain;

public class Dealer {

    private static final int INIT_CARD_NUMBER = 2; //TODO 이름 생각해보기

    private final Players players;
    private final CardDeck cardDeck;
    private final Hands hands;

    public Dealer(final Players players, final CardDeck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
        this.hands = Hands.createEmptyPacket();
    }

    public void startDeal() {
        for (int i = 0; i < INIT_CARD_NUMBER; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
            hands.add(cardDeck.pop());
        }
    }
}
