package model.participant;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.KING;
import static model.card.CardNumber.NINE;
import static model.card.CardNumber.QUEEN;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.SIX;
import static model.card.CardNumber.THREE;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static model.game.GameResult.BLACKJACK_WIN;
import static model.game.GameResult.LOOSE;
import static model.game.GameResult.PUSH;
import static model.game.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @DisplayName("딜러의 카드 합계가 16점 이하면 true를 반환한다")
    @Test
    void testCanHit() {
        List<Card> cards = List.of(new Card(SIX, HEART), new Card(JACK, HEART));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleHit()).isTrue();
    }

    @DisplayName("딜러의 카드 합계가 17점 이상이면 false를 반환한다")
    @Test
    void testCanNotHit() {
        List<Card> cards = List.of(new Card(SEVEN, HEART), new Card(JACK, HEART));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleHit()).isFalse();
    }

    @DisplayName("카드 1장을 획득하면 딜러의 카드가 1개가 증가한다")
    @Test
    void testHitCard() {
        Dealer dealer = new Dealer();
        Card card = new Card(TWO, DIAMOND);
        dealer.hit(card);
        assertThat(dealer.cardSize()).isEqualTo(1);
    }

    @DisplayName("딜러가 처음 획득한 카드 1장을 반환한다")
    @Test
    void testGetFirstCard() {
        List<Card> cards = List.of(new Card(SIX, HEART), new Card(JACK, HEART));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.getFirstCard()).isEqualTo("6하트");
    }

    @DisplayName("플레이어가 블랙잭으로 이긴 경우 BLACKJACK_WIN 반환")
    @ParameterizedTest
    @MethodSource("provideDealerCardsWithNotBurstAndBurst")
    void testJudgeBlackjackWin(List<Card> dealerCards) {
        Dealer dealer = new Dealer(dealerCards);
        List<Card> playerCards = List.of(new Card(ACE, HEART), new Card(JACK, SPADE));
        Player player = new Player("jojo", playerCards);

        Assertions.assertThat(dealer.judge(player)).isEqualTo(BLACKJACK_WIN);
    }

    public static Stream<Arguments> provideDealerCardsWithNotBurstAndBurst() {
        return Stream.of(
            Arguments.of(List.of(new Card(NINE, HEART), new Card(JACK, SPADE))),
            Arguments.of(List.of(new Card(NINE, HEART), new Card(JACK, SPADE)), new Card(THREE, CLOVER))
        );
    }

    @DisplayName("플레이어가 블랙잭이 아닌 다른 패로 이긴 경우 WIN 반환")
    @ParameterizedTest
    @MethodSource("provideDealerCardsWithNotBurstAndBurst")
    void testJudgeWin(List<Card> dealerCards) {
        Dealer dealer = new Dealer(dealerCards);
        List<Card> playerCards = List.of(new Card(QUEEN, HEART), new Card(NINE, SPADE), new Card(TWO, SPADE));
        Player player = new Player("jojo", playerCards);

        Assertions.assertThat(dealer.judge(player)).isEqualTo(WIN);
    }

    @DisplayName("플레이어가 진 경우 LOOSE 반환")
    @ParameterizedTest
    @MethodSource("provideDealerCardsAndPlayerCardsWithLoose")
    void testJudgeLoose(List<Card> dealerCards, List<Card> playerCards) {
        Dealer dealer = new Dealer(dealerCards);
        Player player = new Player("jojo", playerCards);

        Assertions.assertThat(dealer.judge(player)).isEqualTo(LOOSE);
    }

    public static Stream<Arguments> provideDealerCardsAndPlayerCardsWithLoose() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(ACE, HEART), new Card(JACK, SPADE)),
                List.of(new Card(QUEEN, HEART), new Card(NINE, SPADE), new Card(TWO, SPADE))
            ),
            Arguments.of(
                List.of(new Card(NINE, HEART), new Card(JACK, SPADE), new Card(KING, HEART)),
                List.of(new Card(NINE, HEART), new Card(JACK, SPADE), new Card(SEVEN, HEART))
            ),
            Arguments.of(
                List.of(new Card(NINE, HEART), new Card(JACK, SPADE)),
                List.of(new Card(KING, HEART), new Card(FIVE, CLOVER))
            )
        );
    }

    @DisplayName("플레이어가 비긴 경우 PUSH 반환")
    @ParameterizedTest
    @MethodSource("provideDealerCardsAndPlayerCardsWithPush")
    void testJudgePush(List<Card> dealerCards, List<Card> playerCards) {
        Dealer dealer = new Dealer(dealerCards);
        Player player = new Player("jojo", playerCards);

        Assertions.assertThat(dealer.judge(player)).isEqualTo(PUSH);
    }

    public static Stream<Arguments> provideDealerCardsAndPlayerCardsWithPush() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(ACE, HEART), new Card(JACK, SPADE)),
                List.of(new Card(ACE, HEART), new Card(JACK, SPADE))
            ),
            Arguments.of(
                List.of(new Card(NINE, HEART), new Card(JACK, SPADE)),
                List.of(new Card(NINE, HEART), new Card(JACK, SPADE))
            )
        );
    }
}
