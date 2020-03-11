package blackjack.domain;

public abstract class User {
    protected final String name;
    protected UserCards cards;


    public User(String name, UserCards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public int getTotalScore() {
        return cards.getTotalScore();
    }
}
