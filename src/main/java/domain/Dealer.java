package domain;

public class Dealer {

    private final CardDeck cardDeck;
    private final Cards ownedCards; // 변수명 추천

    private Dealer(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        this.ownedCards = Cards.of();
    }

    public static Dealer of(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    public void passCard(Player player) {
        Card card = cardDeck.popCard();
        player.receive(card);
    }
}
