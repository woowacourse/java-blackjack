package blackjack.domain.user;

import blackjack.domain.card.Card;

public abstract class User {
    protected static final String CARD = " 카드: ";
    protected static final String DELIMITER = ", ";
    protected static final String RESULT = " - 결과 : ";
    protected static final String BUSTED = "버스트";
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

    public String getName() {
        return this.name;
    }

    public String showCardInfo() {
        return name + CARD + String.join(DELIMITER, cards.getCardInfo());
    }

    public abstract String showInitialCardInfo();

    public String showFinalCardInfo() {
        return showCardInfo() + RESULT + parseFinalScore();
    }

    public boolean isBusted() {
        return cards.isBusted();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    private String parseFinalScore() {
        int finalScore = this.getTotalScore();
        if(finalScore == 0) {
            return BUSTED;
        }
        return Integer.toString(finalScore);
    }
}
