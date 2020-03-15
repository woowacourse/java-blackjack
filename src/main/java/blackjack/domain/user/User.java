package blackjack.domain.user;

import blackjack.domain.card.Card;

public abstract class User {
    private static final int MAX_VALID_SUM = 21;
    public static final String CARD = " 카드: ";
    private static final String DELIMITER = ", ";
    private static final String RESULT = " - 결과 : ";
    private static final String BUSTED = "버스트";
    private static final int BUSTED_VAL_RESET = 0;
    private static final String NO_CARD = "카드가 없습니다";
    protected final String name;
    protected UserCards cards;


    public User(String name) {
        validateUserName(name);
        this.name = name;
    }

    private void validateUserName(String name) {
        if (name == null) {
            throw new NullPointerException("이름이 입력되지 않았습니다");
        }
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
        return cards.calculateTotalScore();
    }

    public boolean isBusted() {
        return cards.calculateTotalScore() == BUSTED_VAL_RESET;
    }

    public boolean isBlackJack() {
        return cards.getCardName().size() == 2
                && cards.calculateTotalScore() == MAX_VALID_SUM;
    }

    public String getName() {
        return this.name;
    }

    public String showCardNames() {
        return name + CARD + String.join(DELIMITER, cards.getCardName());
    }

    public abstract String showInitialCardNames();

    public String showFinalCardNameScore() {
        return showCardNames() + RESULT + parseTotalScore();
    }

    private String parseTotalScore() {
        int finalScore = this.getTotalScore();
        if (finalScore == BUSTED_VAL_RESET) {
            return BUSTED;
        }
        return Integer.toString(finalScore);
    }
}
