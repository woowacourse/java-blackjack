package second.domain;

import second.domain.card.HandCards;
import second.domain.score.Score;

import static second.domain.BlackJackGame.INITIAL_CARD_AMOUNT;

public class BlackJackRule {
    public static boolean isBlackJack(HandCards handCards, Score score) {
        return handCards.hasSizeOf(INITIAL_CARD_AMOUNT) && score.isMaxScore();
    }
}
