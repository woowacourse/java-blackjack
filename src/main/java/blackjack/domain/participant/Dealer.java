package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.WinTieLose;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final String NON_CASE = "해당하는 경우가 없습니다.";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public boolean canNotHit() {
        return getTotalScore() > 16 || getTotalScore() < 0;
    }

    public void reverseAllExceptOne() {
        this.getCards().forEach(Card::closeCard);
        this.getCards().get(0).openCard();
    }

    public void openAllCard() {
        this.getCards().forEach(Card::openCard);
    }

    public WinTieLose compareScoreWith(Player player) {
        if(player.isBlackjack()){
            return checkBlackjackCase(player);
        }
        if (isTie(player.getTotalScore())) {
            return WinTieLose.TIE;
        }
        if (isWin(player.getTotalScore())) {
            return WinTieLose.WIN;
        }
        if (isLose(player.getTotalScore())) {
            return WinTieLose.LOSE;
        }
        throw new NullPointerException(NON_CASE);
    }

    private WinTieLose checkBlackjackCase(Player player) {
        if(this.isBlackjack()){
            return WinTieLose.TIE;
        }
        return WinTieLose.BLACKJACK;
    }

    private boolean isLose(int playerScore) {
        return playerScore < this.getTotalScore();
    }

    private boolean isWin(int playerScore) {
        return playerScore > this.getTotalScore();
    }

    private boolean isTie(int playerScore) {
        return playerScore == this.getTotalScore();
    }
}
