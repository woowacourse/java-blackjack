package blackjack.domain.state;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldingCards;

public interface State {

    State drawCard(Deck deck);

    boolean isBust();

    HoldingCards holdingCards();

    int cardSum();

    State stand(); // stay상태로 이동하는 메서드 (더이상 입력 받지 않는다고 한다면 stay로 이동하기 위해!!!)

    boolean isFinished();

    /**
     * 카드 뽑기 o
     * 버스트 체크 o
     * 점수 게산 o
     * 카드 리턴 o
     * 수익 계산
     */
}
