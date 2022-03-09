package blackjack.domain;

public class Gamer {
    private final CardGroup cardGroup = new CardGroup();

    public int getCardsSize() {
        return cardGroup.getSize();
    }

    public void addTwoCards(Card firstCard, Card secondCard) {
        cardGroup.addTwoCards(firstCard, secondCard);
    }

    public void addCard(Card card) {
        cardGroup.addCard(card);
    }
}
