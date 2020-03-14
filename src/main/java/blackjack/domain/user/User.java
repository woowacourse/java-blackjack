package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class User {
    protected static final String CARD = " 카드: ";
    protected static final String DELIMITER = ", ";
    protected static final String RESULT = " - 결과 : ";
    protected static final String BUSTED = "버스트";
    protected final String name;
    protected UserCards userCards;

    public User(String name) {
        this.name = name;
    }

    public void receiveInitialCards(List<Card> initialCards) {
        this.userCards = new UserCards(initialCards);
    }

    public void receiveCard(Card card) {
        userCards.add(card);
    }

    public int getTotalScore() {
        return userCards.getTotalScore();
    }

    public String getName() {
        return this.name;
    }

    public String showCardInfo() {
        return name + CARD + String.join(DELIMITER, userCards.getCardInfo());
    }

    public abstract String showInitialCardInfo();

    public String showFinalCardInfo() {
        return showCardInfo() + RESULT + parseFinalScore();
    }

    public boolean isBusted() {
        return userCards.isBusted();
    }

    public boolean isBlackJack() {
        return userCards.isBlackJack();
    }

    private String parseFinalScore() {
        int finalScore = this.getTotalScore();
        if(finalScore == 0) {
            return BUSTED;
        }
        return Integer.toString(finalScore);
    }
}
