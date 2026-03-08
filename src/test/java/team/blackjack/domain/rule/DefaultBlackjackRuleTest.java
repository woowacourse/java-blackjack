package team.blackjack.domain.rule;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.Card;
import team.blackjack.domain.Rank;
import team.blackjack.domain.Result;
import team.blackjack.domain.Suit;

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


    @Test
    void 숫자10과_6이후에_ACE가_2개_오는_경우_각각_1로_정상_해석되는지_테스트() {
        List<Card> cards = List.of(
                new Card(Suit.CLUBS, Rank.KING),
                new Card(Suit.HEARTS, Rank.SIX),
                new Card(Suit.SPADES, Rank.ACE),
                new Card(Suit.HEARTS, Rank.ACE)
        );

        int score = DefaultBlackjackRule.calculateBestScore(cards);

        Assertions.assertEquals(18, score);
    }

    @Test
    void Ace가_1장있는_경우_최적의_합_정상_계산_테스트() {
        List<Card> cards = List.of(
                new Card(Suit.CLUBS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.ACE)
        );

        int score = DefaultBlackjackRule.calculateBestScore(cards);

        Assertions.assertEquals(21, score);
    }

    @Test
    void 딜러의_승패를_계산할때_딜러가_플레이어보다_더_높으면_WIN() {
        int dealerScore = 20;
        int playerScore = 18;

        Assertions.assertEquals(Result.WIN, DefaultBlackjackRule.judgeResult(dealerScore, playerScore));
    }

    @Test
    void 플레이어의_승패를_계산할때_플레이어가_딜러보다_더_높으면_WIN() {
        int dealerScore = 18;
        int playerScore = 20;

        Assertions.assertEquals(Result.WIN, DefaultBlackjackRule.judgeResult(playerScore, dealerScore));
    }

    @Test
    void 딜러의_승패를_계산할때_딜러가_플레이어보다_더_낮으면_LOSE() {
        int dealerScore = 18;
        int playerScore = 20;

        Assertions.assertEquals(Result.LOSE, DefaultBlackjackRule.judgeResult(dealerScore, playerScore));
    }

    @Test
    void 플레이어의_승패를_계산할때_플레이어가_딜러보다_더_낮으면_LOSE() {
        int dealerScore = 20;
        int playerScore = 18;

        Assertions.assertEquals(Result.LOSE, DefaultBlackjackRule.judgeResult(playerScore, dealerScore));
    }

    @Test
    void 딜러의_승패를_계산할때_플레이어가_딜러_점수와_같을때_DRAW() {
        int dealerScore = 20;
        int playerScore = 20;

        Assertions.assertEquals(Result.DRAW, DefaultBlackjackRule.judgeResult(dealerScore, playerScore));
    }

    @Test
    void 플레이어의_승패를_계산할때_플레이어가_딜러_점수와_같을때_DRAW() {
        int dealerScore = 20;
        int playerScore = 20;

        Assertions.assertEquals(Result.DRAW, DefaultBlackjackRule.judgeResult(playerScore, dealerScore));
    }

    @Test
    void 딜러의_승패를_계산할때_딜러의_점수가_21초과로_버스트일때_LOSE() {
        int dealerScore = 22;
        int playerScore = 20;

        Assertions.assertEquals(Result.LOSE, DefaultBlackjackRule.judgeResult(dealerScore, playerScore));
    }

    @Test
    void 플레이어의_승패를_계산할때_플레이어의_점수가_21초과로_버스트일때_LOSE() {
        int dealerScore = 20;
        int playerScore = 22;

        Assertions.assertEquals(Result.LOSE, DefaultBlackjackRule.judgeResult(playerScore, dealerScore));
    }

    @Test
    void 플레이어의_승패를_계산할때_플레이어의_점수가_21초과로_버스트이면서_딜러도_버스트일때_플레이어는_LOSE() {
        int dealerScore = 22;
        int playerScore = 22;

        Assertions.assertEquals(Result.LOSE, DefaultBlackjackRule.judgeResult(playerScore, dealerScore));
    }

    @Test
    void 딜러의_점수가_21을_넘을때_Bust(){
        int dealerScore = 22;

        Assertions.assertEquals(true, DefaultBlackjackRule.isBust(dealerScore));
    }

    @Test
    void 플레이어의_점수가_21을_넘을때_Bust(){
        int playerScore = 22;

        Assertions.assertEquals(true, DefaultBlackjackRule.isBust(playerScore));
    }

    @Test
    void 합이_10이하면_Ace는_11() {
        Assertions.assertTrue(DefaultBlackjackRule.canUseAceAsEleven(10));
    }

    @Test
    void 합이_10초과면_Ace는_11() {
        Assertions.assertFalse(DefaultBlackjackRule.canUseAceAsEleven(11));
    }
}
