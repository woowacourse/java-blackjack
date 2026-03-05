package team.blackjack.domain.rule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.rule.DefaultBlackjackRule;

class DefaultBlackjackRuleTest {
    private static final DefaultBlackjackRule defaultBlackjackRule = new DefaultBlackjackRule();

    @Test
    void 각_핸드의_점수는_21점을_초과하는_경우_버스트이다() {
        int handScore = 22;

        boolean isBust = defaultBlackjackRule.isBust(handScore);

        Assertions.assertEquals(true, isBust);
    }

    @Test
    void 각_핸드의_점수는_21점을_초과하지_않는_경우_버스트가_아니다() {
        int handScore = 21;

        boolean isBust = defaultBlackjackRule.isBust(handScore);

        Assertions.assertEquals(false, isBust);
    }

    @Test
    void 각_핸드의_카드_개수가_2개이고_점수가_21점인_경우_블랙잭이다() {
        int handScore = 21;
        int cardCount = 2;

        boolean isBlackjack = defaultBlackjackRule.isBlackjack(handScore, cardCount);

        Assertions.assertEquals(true, isBlackjack);
    }

    @Test
    void 각_핸드의_카드_개수가_3개이상이고_점수가_21점인_경우_블랙잭이_아니다() {
        int handScore = 21;
        int cardCount = 3;

        boolean isBlackjack = defaultBlackjackRule.isBlackjack(handScore, cardCount);

        Assertions.assertEquals(false, isBlackjack);
    }

    @Test
    void 각_핸드의_점수가_21점이_아닌_경우_블랙잭이_아니다() {
        int handScore = 20;
        int cardCount = 2;

        boolean isBlackjack = defaultBlackjackRule.isBlackjack(handScore, cardCount);

        Assertions.assertEquals(false, isBlackjack);
    }

    @Test
    void 딜러의_점수가_17점_미만인_경우_딜러는_카드를_더_받아야한다() {
        int dealerScore = 16;
        boolean isDealerMustDraw = defaultBlackjackRule.isDealerMustDraw(dealerScore);

        Assertions.assertEquals(true, isDealerMustDraw);
    }

    @Test
    void 딜러의_점수가_17점_이상인_경우_딜러는_카드를_더_받지_않아야한다() {
        int dealerScore = 17;
        boolean isDealerMustDraw = defaultBlackjackRule.isDealerMustDraw(dealerScore);

        Assertions.assertEquals(false, isDealerMustDraw);
    }
}
