package blackjack.domain;

public abstract class User {
    public static final int BLACKJACK = 21;
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

    public boolean isBusted() {
        return cards.getTotalScore() > BLACKJACK;
    }

    public boolean isBlackJack() {
        return cards.getTotalScore() == BLACKJACK;
    }
}
