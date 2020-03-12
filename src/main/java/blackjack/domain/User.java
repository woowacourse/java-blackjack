package blackjack.domain;

public abstract class User {
    public static final int BLACKJACK = 21;
    protected final String name;
    protected UserCards cards;


    public User(String name) {
        this.name = name;
    }

    public void receiveInitialCards(UserCards initialCards) {
        this.cards = initialCards;
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
        return cards.getCardInfo().size() == 2
                && cards.getTotalScore() == BLACKJACK;
    }

    public String getName() {
        return this.name;
    }

    public String showCardInfo() {
        return String.join(", ", cards.getCardInfo());
    }
}
