package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.winning.WinningResult;

public interface State {
    /**
     * 힛(Hit): 처음 2장의 상태에서 카드를 더 뽑는 것 스테이(Stay): 카드를 더 뽑지 않고 차례를 마치는 것 블랙잭(Blackjack): 처음 두 장의 카드 합이 21인 경우, 베팅 금액의 1.5배
     * 버스트(Bust): 카드 총합이 21을 넘는 경우. 배당금을 잃는다.
     */

    State draw(Card card);

    State stay();

    double profit(double bettingMoney);

    Score calculateTotalScore();

    WinningResult decide(State state);

    boolean isFinished();

    Cards cards();
}
