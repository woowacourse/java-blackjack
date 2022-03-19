package blackjack.domain.state;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldingCards;

public interface State {

    State drawCard(Deck deck);

    boolean isBust();

    HoldingCards cards();

    int cardSum();

    boolean isFinished();

    /**
     * 카드 뽑기 o
     * 버스트 체크 o
     * 점수 게산 o
     * 카드 리턴 o
     * 수익 계산
     */
}
