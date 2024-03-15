package blackjack.domain.participants;


import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public class Player {
    public static final int BLACKJACK_SCORE = 21;
    public static final int BLACKJACK_CARD_SIZE = 2;

    private final Name name;
    private final PlayerStatus playerStatus;

    public Player(Name name) {
        this.name = name;
        this.playerStatus = new PlayerStatus();
    }

    public void receiveCard(Card card) {
        playerStatus.addCard(card);
    }

    public int calculateScore() {
        return playerStatus.calculateScore();
    }

    public Result checkResult(int dealerScore, boolean isDealerBlackjack) {
        return checkResultWhenBust(dealerScore, isDealerBlackjack);
    }

    private Result checkResultWhenBust(int dealerScore, boolean isDealerBlackjack) {
        if (calculateScore() > BLACKJACK_SCORE) {
            return Result.LOSE;
        }
        if (dealerScore > BLACKJACK_SCORE) {
            return Result.WIN;
        }
        return checkResultWhenBlackjack(dealerScore, isDealerBlackjack);
    }

    private Result checkResultWhenBlackjack(int dealerScore, boolean isDealerBlackjack) {
        if (isBlackjack() && isDealerBlackjack) {
            return Result.TIE;
        }
        if (isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        return checkResultWhenNormal(dealerScore);
    }

    private Result checkResultWhenNormal(int dealerScore) {
        if (calculateScore() < dealerScore) {
            return Result.LOSE;
        }
        if (calculateScore() > dealerScore) {
            return Result.WIN;
        }
        return Result.TIE;
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_SCORE && checkHandSize() == BLACKJACK_CARD_SIZE;
    }

    public boolean isNotOver(int boundaryScore) {
        return playerStatus.calculateScore() < boundaryScore;
    }

    public void betMoney(GamblingMoney gamblingMoney) {
        playerStatus.addMoney(gamblingMoney);
    }

    public void loseMoney(GamblingMoney gamblingMoney) {
        playerStatus.subtractMoney(gamblingMoney);
    }

    public void checkBettingMoney(float benefit) {
        playerStatus.calculateBettingMoney(benefit);
    }

    private int checkHandSize() {
        return playerStatus.checkHandSize();
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return playerStatus.getHand();
    }

    public GamblingMoney getGamblingMoney() {
        return playerStatus.getGamblingMoney();
    }
}
