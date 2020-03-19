package second.domain;

import second.domain.card.HandCards;
import second.domain.score.Score;

import static second.domain.BlackJackGame.INITIAL_CARD_AMOUNT;

// TODO : 정적 클래스 ?, 구현 클래스 ?
// 상태를 가지는지 안가지는지 생각해보자.
public class BlackJackRule {
    public static boolean isBlackJack(HandCards handCards, Score score) {
        return handCards.isRightSize(INITIAL_CARD_AMOUNT) && score.isMaxScore();
    }
}
