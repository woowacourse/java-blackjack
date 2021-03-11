package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.result.MatchResult;

public class Player extends Participant {

    private BettingMoney bettingMoney;

    public Player(String name, Hand cardHand) {
        super(name, cardHand);
    }

    public static Player from(String name) {
        return new Player(name, Hand.createEmptyHand());
    }

    public void setBettingMoney(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public MatchResult getMatchResult(int dealerCardSum) {
        if (isBust() || getHandTotal() < dealerCardSum) {
            return MatchResult.LOSE;
        }
        if (dealerCardSum == getHandTotal()) {
            return MatchResult.TIE;
        }
        if (dealerCardSum < getHandTotal()) {
            return MatchResult.WIN;
        }

        throw new IllegalArgumentException("입력값이 잘못되었습니다. 승무패를 계산할 수 없습니다.");
    }

    @Override
    public int getHandTotal() {
        return cardHand.getPlayerTotal();
    }
}
