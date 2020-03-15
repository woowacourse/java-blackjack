package blackjack.domain.user;

import blackjack.domain.card.Card;

public abstract class User {
    private static final int MAX_VALID_SUM = 21;
    private static final String CARD = " 카드: ";
    private static final String DELIMITER = ", ";
    private static final String RESULT = " - 결과 : ";
    private static final String BUSTED = "버스트";
    private static final int BUSTED_VAL_RESET = 0;
    private static final String NO_CARD = "카드가 없습니다";
    protected final String name;
    protected UserCards cards;


    public User(String name) {
        this.name = name;
    }

    public void receiveInitialCards(UserCards initialCards) {
        validateInitialCards(initialCards);
        this.cards = initialCards;
    }

    private void validateInitialCards(UserCards initialCards) {
        if (initialCards == null) {
            throw new NullPointerException(NO_CARD);
        }
    }

    public void receiveCard(Card card) {
        validateCard(card);
        cards.addCard(card);
    }

    private void validateCard(Card card) {
        if (card == null) {
            throw new NullPointerException(NO_CARD);
        }
    }

    public int getTotalScore() {
        return cards.getTotalScore();
    }

    public boolean isBusted() {
        return cards.getTotalScore() == BUSTED_VAL_RESET;
    }

    public boolean isBlackJack() {
        return cards.getCardInfo().size() == 2
                && cards.getTotalScore() == MAX_VALID_SUM;
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

    private String parseFinalScore() {
        int finalScore = this.getTotalScore();
        if (finalScore == BUSTED_VAL_RESET) {
            return BUSTED;
        }
        return Integer.toString(finalScore);
    }
}
