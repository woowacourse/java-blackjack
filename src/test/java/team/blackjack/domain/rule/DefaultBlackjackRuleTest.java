package team.blackjack.domain.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import team.blackjack.domain.Card;
import team.blackjack.domain.Result;

class DefaultBlackjackRuleTest {


    @ParameterizedTest
    @CsvSource({
            "22, true",
            "21, false"
    })
    void 버스트_판정(int handScore, boolean expected) {
        boolean isBust = DefaultBlackjackRule.isBust(handScore);
        assertEquals(expected, isBust);
    }

    @ParameterizedTest
    @CsvSource({
            "21, 2, true",
            "21, 3, false",
            "20, 2, false"
    })
    void 블랙잭_판정(int handScore, int cardCount, boolean expected) {
        boolean isBlackjack = DefaultBlackjackRule.isBlackjack(handScore, cardCount);
        assertEquals(expected, isBlackjack);
    }

    @ParameterizedTest
    @CsvSource({
            "16, true",
            "17, false"
    })
    void 딜러_카드_추가_여부(int dealerScore, boolean expected) {
        boolean isDealerMustDraw = DefaultBlackjackRule.isDealerMustDraw(dealerScore);
        assertEquals(expected, isDealerMustDraw);
    }

    @ParameterizedTest
    @CsvSource({
            "20,18,WIN",
            "18,20,LOSE",
            "17,17,DRAW",
            "22,20,LOSE"
    })
    void 승패_판정(int myScore, int opponentScore, Result expected) {
        assertEquals(expected, DefaultBlackjackRule.judgeResult(myScore, opponentScore));
    }

    @ParameterizedTest
    @CsvSource({
            "10, true",
            "11, false"
    })
    void Ace를_11로_사용_가능한지(int score, boolean expected) {
        assertEquals(expected, DefaultBlackjackRule.canUseAceAsEleven(score));
    }


    @Test
    void 숫자10과_6이후에_ACE가_2개_오는_경우_각각_1로_정상_해석되는지_테스트() {
        List<Card> cards = List.of(Card.KING_OF_CLUBS, Card.SIX_OF_HEARTS, Card.ACE_OF_SPADES, Card.ACE_OF_HEARTS);

        int score = DefaultBlackjackRule.calculateBestScore(cards);

        assertEquals(18, score);
    }

    @Test
    void Ace가_1장있는_경우_최적의_합_정상_계산_테스트() {
        List<Card> cards = List.of(Card.FIVE_OF_CLUBS, Card.FIVE_OF_DIAMONDS, Card.ACE_OF_SPADES);
        int score = DefaultBlackjackRule.calculateBestScore(cards);

        assertEquals(21, score);
    }
}
