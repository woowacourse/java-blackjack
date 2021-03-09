package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PlayerTest {
    private static final int BETTING_MONEY = 1000;
    private static final List<Card> CARDS_SCORE_19 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.EIGHT, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_20 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.NINE, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_21 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.TEN, Shape.HEART)
    );
    private static final List<Card> CARDS_BUST = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.TEN, Shape.HEART),
            new Card(Symbol.TWO, Shape.HEART)
    );
    private static final List<Card> CARDS_BLACKJACK = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.ACE, Shape.DIAMOND)
    );

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(CARDS_SCORE_20, true),
                Arguments.of(CARDS_SCORE_21, false)
        );
    }

    @DisplayName("더 받을 수 있는가 : ACE를 1로 했을 때 카드 합이 21 미만일 경우 true, 그 이상인 경우 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("generateData")
    void isAbleToReceiveCard(List<Card> inputCards, boolean result) {
        Cards cards = new Cards(inputCards);
        Player player = new Player("jason", 100);

        player.receiveCards(cards);
        boolean isAbleToReceiveCard = player.isAbleToReceiveCard();

        assertThat(isAbleToReceiveCard).isEqualTo(result);
    }

    @DisplayName("플레이어의 배팅 금액은 양의 정수여야한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void isValidBettingMoney(int bettingMoney) {
        assertThatCode(() -> new Player("jason", bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양의 정수여야합니다.");
    }

    @DisplayName("calculateProfitMoney 메서드는")
    @Nested
    class Describe_CalculateProfitMoney {

        private int calculateProfitMoney(List<Card> playerCards, List<Card> dealerCards) {
            Player player = new Player("json", BETTING_MONEY);
            player.receiveCards(new Cards(playerCards));
            Dealer dealer = new Dealer();
            dealer.receiveCards(new Cards(dealerCards));
            return player.calculateProfitMoney(dealer);
        }

        @DisplayName("딜러가 블랙잭일 때")
        @Nested
        class Context_With_Dealer_BlackJack {

            @DisplayName("플레이어가 블랙잭이면 배팅 금액 원금을 얻는다")
            @Test
            void earnProfitMoney_PlayerBlackJack() {
                int profitMoney = calculateProfitMoney(CARDS_BLACKJACK, CARDS_BLACKJACK);

                assertThat(profitMoney).isEqualTo(BETTING_MONEY);
            }

            @DisplayName("플레이어가 21점이더라도 배팅 금액을 무조건 잃는다")
            @Test
            void loseBettingMoney_PlayerHighestScore() {
                int profitMoney = calculateProfitMoney(CARDS_SCORE_21, CARDS_BLACKJACK);

                assertThat(profitMoney).isEqualTo(-1 * BETTING_MONEY);
            }
        }

        @DisplayName("딜러가 버스트일 때")
        @Nested
        class Context_With_Dealer_Bust {

            @DisplayName("플레이어가 버스트면 배팅 금액을 잃는다")
            @Test
            void loseBettingMoney_PlayerBust() {
                int profitMoney = calculateProfitMoney(CARDS_BUST, CARDS_BUST);

                assertThat(profitMoney).isEqualTo(-1 * BETTING_MONEY);
            }

            @DisplayName("플레이어가 블랙잭이라면 배팅 금액의 1.5배를 얻는다")
            @Test
            void earnProfitMoney_PlayerBlackJack() {
                int profitMoney = calculateProfitMoney(CARDS_BLACKJACK, CARDS_BUST);

                assertThat(profitMoney).isEqualTo((int) (BETTING_MONEY * 1.5));
            }

            @DisplayName("플레이어가 1~21 일반 점수라면 배팅 원금을 얻는다")
            @Test
            void earnProfitMoney_PlayerNormalScore() {
                int profitMoney = calculateProfitMoney(CARDS_SCORE_19, CARDS_BUST);

                assertThat(profitMoney).isEqualTo(BETTING_MONEY);
            }
        }

        @DisplayName("딜러가 일반 점수(1~21)일 때")
        @Nested
        class Context_With_Dealer_Normal {

            @DisplayName("플레이어가 버스트면 배팅 금액을 잃는다")
            @Test
            void loseBettingMoney_PlayerBust() {
                int profitMoney = calculateProfitMoney(CARDS_BUST, CARDS_SCORE_19);

                assertThat(profitMoney).isEqualTo(-1 * BETTING_MONEY);
            }

            @DisplayName("플레이어가 딜러보다 점수가 낮다면 배팅 금액을 잃는다")
            @Test
            void loseBettingMoney_PlayerLessScore() {
                int profitMoney = calculateProfitMoney(CARDS_SCORE_19, CARDS_SCORE_20);

                assertThat(profitMoney).isEqualTo(-1 * BETTING_MONEY);
            }

            @DisplayName("플레이어가 블랙잭이면 배팅 금액의 1.5배를 얻는다")
            @Test
            void earnBettingMoney_PlayerBlackJack() {
                int profitMoney = calculateProfitMoney(CARDS_BLACKJACK, CARDS_SCORE_19);

                assertThat(profitMoney).isEqualTo((int) (1.5 * BETTING_MONEY));
            }

            @DisplayName("플레이어가 딜러보다 점수가 높으면 배팅 금액 원금만큼 얻는다")
            @Test
            void earnBettingMoney_PlayerMoreScore() {
                int profitMoney = calculateProfitMoney(CARDS_SCORE_21, CARDS_SCORE_19);

                assertThat(profitMoney).isEqualTo(BETTING_MONEY);
            }
        }
    }
}
