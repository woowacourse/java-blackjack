package blackjack.domain.profit;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitWeightTest {
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

    @DisplayName("findProfitWeight 메서드는")
    @Nested
    class Describe_findProfitWeight {

        private ProfitWeight findProfitWeight(List<Card> playerCards, List<Card> dealerCards) {
            Player player = new Player("json", BETTING_MONEY);
            player.receiveCards(new Cards(playerCards));
            Dealer dealer = new Dealer();
            dealer.receiveCards(new Cards(dealerCards));
            return ProfitWeight.findProfitWeight(dealer, player);
        }

        @DisplayName("딜러가 버스트일 때")
        @Nested
        class Context_With_Dealer_Bust {

            @DisplayName("플레이어가 버스트라면 손실 가중치를 반환한다")
            @Test
            void returnLossProfit_PlayerBust() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_BUST, CARDS_BUST);

                assertThat(profitWeight).isEqualTo(ProfitWeight.LOSS_WEIGHT);
            }

            @DisplayName("플레이어가 블랙잭이면 블랙잭 이익 가중치를 반환한다")
            @Test
            void earnProfitWeight_PlayerBlackJack() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_BLACKJACK, CARDS_BUST);

                assertThat(profitWeight).isEqualTo(ProfitWeight.BLACKJACK_PROFIT_WEIGHT);
            }

            @DisplayName("플레이어가 1~21 일반 점수면 일반 이익 가중치를 얻는다")
            @Test
            void earnProfitWeight_PlayerNormalScore() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_SCORE_19, CARDS_BUST);

                assertThat(profitWeight).isEqualTo(ProfitWeight.NORMAL_PROFIT_WEIGHT);
            }
        }

        @DisplayName("딜러가 블랙잭일 때")
        @Nested
        class Context_With_Dealer_BlackJack {

            @DisplayName("플레이어가 블랙잭이면 일반 이익 가중치를 반환한다.")
            @Test
            void returnLossProfit_PlayerBlackJack() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_BLACKJACK, CARDS_BLACKJACK);

                assertThat(profitWeight).isEqualTo(ProfitWeight.NORMAL_PROFIT_WEIGHT);
            }

            @DisplayName("플레이어가 21점이더라도 무조건 손실 가중치를 반환한다")
            @Test
            void lossProfitWeight_PlayerNormalOrBust() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_SCORE_21, CARDS_BLACKJACK);

                assertThat(profitWeight).isEqualTo(ProfitWeight.LOSS_WEIGHT);
            }
        }

        @DisplayName("딜러가 일반 점수(1~21)일 때")
        @Nested
        class Context_With_Dealer_Normal {

            @DisplayName("플레이어가 버스트면 무조건 손실 가중치를 반환한다")
            @Test
            void lossProfitWeight_PlayerBust() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_BUST, CARDS_SCORE_19);

                assertThat(profitWeight).isEqualTo(ProfitWeight.LOSS_WEIGHT);
            }

            @DisplayName("플레이어가 딜러보다 점수가 낮다면 손실 가중치를 반환한다")
            @Test
            void lossProfitWeight_PlayerLessScore() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_SCORE_19, CARDS_SCORE_20);

                assertThat(profitWeight).isEqualTo(ProfitWeight.LOSS_WEIGHT);
            }

            @DisplayName("플레이어가 블랙잭이면 블랙잭 이익 가중치를 얻는다")
            @Test
            void earnProfitWeight_PlayerBlackJack() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_BLACKJACK, CARDS_SCORE_19);

                assertThat(profitWeight).isEqualTo(ProfitWeight.BLACKJACK_PROFIT_WEIGHT);
            }

            @DisplayName("플레이어가 딜러보다 점수가 높으면 일반 이익 가중치를 얻는다.")
            @Test
            void earnProfitWeight_PlayerMoreScore() {
                ProfitWeight profitWeight = findProfitWeight(CARDS_SCORE_21, CARDS_SCORE_19);

                assertThat(profitWeight).isEqualTo(ProfitWeight.NORMAL_PROFIT_WEIGHT);
            }
        }
    }
}
