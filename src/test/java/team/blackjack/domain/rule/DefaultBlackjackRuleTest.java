package team.blackjack.domain.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import team.blackjack.domain.Card;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Participant;
import team.blackjack.domain.Player;
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
        boolean shouldHit = DefaultBlackjackRule.shouldDealerHit(dealerScore);
        assertEquals(expected, shouldHit);
    }

    @Test
    void 플레이어가_버스트면_패() {
        Participant player = playerWithCards(Card.KING_OF_HEARTS, Card.SEVEN_OF_HEARTS, Card.FIVE_OF_HEARTS); // 22
        Participant target = dealerWithCards(Card.KING_OF_CLUBS, Card.TEN_OF_CLUBS); // 20

        assertEquals(Result.LOSE, DefaultBlackjackRule.judge(player, target));
    }

    @Test
    void 딜러가_버스트고_플레이어는_20점일경우_승() {
        Participant player = playerWithCards(Card.KING_OF_HEARTS, Card.TEN_OF_HEARTS); // 20
        Participant dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.SEVEN_OF_CLUBS, Card.SIX_OF_CLUBS); // 23

        assertEquals(Result.WIN, DefaultBlackjackRule.judge(player, dealer));
    }

    @Test
    void 버스트가_아닌_플레이어_점수가_높으면_승() {
        Participant player = playerWithCards(Card.KING_OF_HEARTS, Card.QUEEN_OF_HEARTS); // 20
        Participant dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.EIGHT_OF_CLUBS); // 18

        assertEquals(Result.WIN, DefaultBlackjackRule.judge(player, dealer));
    }

    @Test
    void 플레이어_점수가_낮으면_패() {
        Participant player = playerWithCards(Card.KING_OF_HEARTS, Card.EIGHT_OF_HEARTS); // 18
        Participant dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.QUEEN_OF_CLUBS); // 20

        assertEquals(Result.LOSE, DefaultBlackjackRule.judge(player, dealer));
    }

    @Test
    void 플레이어와_딜러_둘다_버스트가_아니고_동점이면_무() {
        Participant player = playerWithCards(Card.KING_OF_HEARTS, Card.SEVEN_OF_HEARTS); // 17
        Participant dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.SEVEN_OF_CLUBS); // 17

        assertEquals(Result.DRAW, DefaultBlackjackRule.judge(player, dealer));
    }

    @Test
    void 플레이어가_블랙잭_상대는_아니면_블랙잭_승() {
        Participant player = playerWithCards(Card.ACE_OF_HEARTS, Card.KING_OF_HEARTS); // blackjack
        Participant dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.NINE_OF_CLUBS); // 19

        assertEquals(Result.BLACKJACK, DefaultBlackjackRule.judge(player, dealer));
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

    private static Player playerWithCards(Card... cards) {
        Player player = new Player("test");
        for (Card card : cards) {
            player.hit(card);
        }
        return player;
    }

    private static Dealer dealerWithCards(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.hit(card);
        }
        return dealer;
    }
}
