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
        return getTotalScore() > 16;
    }

    public void reverseAllExceptOne() {
        this.getCards().forEach(Card::reverseCard);
        this.getCards().get(0).openCard();
    }

    public void openAllCard() {
        this.getCards().forEach(Card::openCard);
    }

    public WinTieLose compareScoreWith(int playerScore){
        if (playerScore==this.getTotalScore()) {
            return WinTieLose.TIE;
        }
        if (playerScore>this.getTotalScore()) {
            return WinTieLose.WIN;
        }
        if (playerScore<this.getTotalScore()) {
            return WinTieLose.LOSE;
        }
        throw new NullPointerException(NON_CASE);
    }
}
