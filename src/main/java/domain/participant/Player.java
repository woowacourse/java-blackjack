package domain.participant;

import domain.card.Deck;

public class Player extends Participant {
    private static final String ERROR_NAME_LENGTH = "[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.";
    private static final String INVALID_BETTING_AMOUNT = "[ERROR] 베팅 금액은 1000 ~ 10억원 까지만 가능합니다";
    private static final String INVALID_BETTING_UNIT = "[ERROR] 베팅 금액은 1000원 단위만 가능합니다";
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_BETTING_AMOUNT = 10000000;
    private static final int MIN_BETTING_AMOUNT = 1000;
    private static final int UNIT_OF_BETTING_MONEY = 1000;
    private static final double BLACKJACK_OUTCOME_RATE = 1.5;
    private static final int LOSE_RATE = -1;
    private static final int BLACKJACK = 21;

    private int bettingAmount;
    private boolean isStand = false;

    private Player(String name, Deck deck) {
        super(name, deck);
    }

    public static Player from(String name, Deck deck) {
        name = name.trim();
        validateName(name);

        return new Player(name, deck);
    }

    private static void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < name.length()) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
    }

    public void bet(int bettingAmount) {
        validateBettingAmount(bettingAmount);

        this.bettingAmount = bettingAmount;
    }

    private void validateBettingAmount(int bettingAmount) {
        if (bettingAmount < MIN_BETTING_AMOUNT || MAX_BETTING_AMOUNT < bettingAmount) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT);
        }

        if (bettingAmount % UNIT_OF_BETTING_MONEY != 0) {
            throw new IllegalArgumentException(INVALID_BETTING_UNIT);
        }
    }

    public int getResultBettingAmount(int dealerScore) {
        final int playerScore = calculateScore();

        if (isBust()) {
            return bettingAmount * LOSE_RATE;
        }
        if (playerScore == dealerScore) {
            return 0;
        }
        if (isBlackjack()) {
            return (int) (bettingAmount * BLACKJACK_OUTCOME_RATE);
        }
        if (dealerScore > BLACKJACK || playerScore > dealerScore) {
            return bettingAmount;
        }
        return bettingAmount * LOSE_RATE;
    }

    @Override
    public boolean isStand() {
        return isStand;
    }

    public void stand() {
        this.isStand = true;
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
