package blackjack.model;

public class User {

    private final String name;
    private final CardStatus cardStatus;

    public User(String name) {
        this.name = name;
        this.cardStatus = new CardStatus();
    }

    public String getName() {
        return name;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void addCard(Card card) {
        cardStatus.addCard(card);
    }
}
