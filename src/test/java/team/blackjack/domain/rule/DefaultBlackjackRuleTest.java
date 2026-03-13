package team.blackjack.domain.rule;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.Card;
import team.blackjack.domain.Hand;
import team.blackjack.domain.Rank;
import team.blackjack.domain.Result;
import team.blackjack.domain.Suit;

class DefaultBlackjackRuleTest {
    private static final BlackjackRule blackjackRule = new DefaultBlackjackRule();

    /**
     * 딜러 카드 재발급 테스트
     */
    @Test
    void 딜러의_점수가_17점_미만인_경우_딜러는_카드를_더_받아야한다() {
        int dealerScore = 16;
        boolean isDealerMustDraw = blackjackRule.isDealerMustDraw(dealerScore);

        Assertions.assertEquals(true, isDealerMustDraw);
    }

    @Test
    void 딜러의_점수가_17점_이상인_경우_딜러는_카드를_더_받지_않아야한다() {
        int dealerScore = 17;
        boolean isDealerMustDraw = blackjackRule.isDealerMustDraw(dealerScore);

        Assertions.assertEquals(false, isDealerMustDraw);
    }

    /**
     * 플레이어 승패 결과 계산 테스트
     */
    /**
     * 1. 플레이어 혹은 딜러 Bust시 결과 테스트
     */
    @Test
    void 딜러의_승패를_계산할때_딜러의_점수가_21초과로_버스트일때_LOSE가_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> playerCard =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.JACK));

        List<Card> dealerCard =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.JACK),
                        Card.of(Suit.HEARTS, Rank.JACK));

        for (Card card : playerCard) {
            playerHand.addCard(card);
        }

        for (Card card : dealerCard) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.WIN, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }

    @Test
    void 플레이어의_승패를_계산할때_플레이어의_점수가_21초과로_버스트일때_LOSE가_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> playerCard =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.JACK),
                        Card.of(Suit.HEARTS, Rank.JACK));

        List<Card> dealerCard =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.JACK));

        for (Card card : playerCard) {
            playerHand.addCard(card);
        }

        for (Card card : dealerCard) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.LOSE, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }

    @Test
    void 플레이어의_승패를_계산할때_플레이어의_점수가_버스트이면서_딜러도_버스트일때_플레이어는_LOSE가_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> bustCards =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.JACK),
                        Card.of(Suit.HEARTS, Rank.JACK));

        for (Card card : bustCards) {
            playerHand.addCard(card);
        }

        for (Card card : bustCards) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.LOSE, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }

    /**
     * 2. 플레이어 혹은 딜러가 블랙잭일 경우 결과 테스트
     */
    @Test
    void 플레이어가_블랙잭이면서_딜러는_블랙잭이_아닐때_플레이어는_BLACKJACK로_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> playerCards =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.ACE));

        List<Card> dealerCard =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.JACK));

        for (Card card : playerCards) {
            playerHand.addCard(card);
        }

        for (Card card : dealerCard) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.BLACKJACK, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }

    @Test
    void 플레이어가_블랙잭이_아니면서_딜러는_블랙잭일때_플레이어는_LOSE가_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> playerCards =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.JACK));

        List<Card> dealerCard =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.ACE));

        for (Card card : playerCards) {
            playerHand.addCard(card);
        }

        for (Card card : dealerCard) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.LOSE, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }

    @Test
    void 플레이어와_딜러_둘다_블랙잭일때_플레이어는_DRAW가_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> blackjackCards =
                List.of(Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.ACE));

        for (Card card : blackjackCards) {
            playerHand.addCard(card);
        }

        for (Card card : blackjackCards) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.DRAW, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }

    /**
     * 3. 점수 비교를 통한 결과 테스트
     */
    @Test
    void 딜러의_승패를_계산할때_딜러가_플레이어보다_더_높으면_LOSE가_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> playerCards =
                List.of(Card.of(Suit.DIAMONDS, Rank.TEN),
                        Card.of(Suit.SPADES, Rank.FIVE));

        List<Card> dealerCard =
                List.of(Card.of(Suit.DIAMONDS, Rank.TEN),
                        Card.of(Suit.SPADES, Rank.SIX));

        for (Card card : playerCards) {
            playerHand.addCard(card);
        }

        for (Card card : dealerCard) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.LOSE, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }

    @Test
    void 플레이어의_승패를_계산할때_플레이어가_딜러보다_더_높으면_WIN이_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> playerCards =
                List.of(Card.of(Suit.DIAMONDS, Rank.TEN),
                        Card.of(Suit.SPADES, Rank.SIX));

        List<Card> dealerCard =
                List.of(Card.of(Suit.DIAMONDS, Rank.TEN),
                        Card.of(Suit.SPADES, Rank.FIVE));

        for (Card card : playerCards) {
            playerHand.addCard(card);
        }

        for (Card card : dealerCard) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.WIN, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }

    @Test
    void 딜러의_승패를_계산할때_플레이어가_딜러_점수와_같을때_DRAW가_반환된다() {
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        List<Card> cards =
                List.of(Card.of(Suit.DIAMONDS, Rank.TEN),
                        Card.of(Suit.SPADES, Rank.SIX));

        for (Card card : cards) {
            playerHand.addCard(card);
        }

        for (Card card : cards) {
            dealerHand.addCard(card);
        }

        Assertions.assertEquals(Result.DRAW, blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }
}
