package blackjack.domain.state;

import blackjack.domain.card.Deck;

public interface State {

    State drawCard(Deck deck);

    boolean isFinished();

    /**
     * 카드 뽑기
     * 버스트 체크
     * 점수 게산
     * 카드 리턴
     * 수익 계산
     */
}
