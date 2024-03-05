package domain;

public class Dealer {

    private static final int INIT_CARD_NUMBER = 2; //TODO 이름 생각해보기
    private final Players players;
    private final CardDeck cardDeck;

    public Dealer(final Players players, final CardDeck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public void startDeal() {
        cardDeck.shuffle();

        for (int i = 0; i < INIT_CARD_NUMBER; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
        }
    }
}
