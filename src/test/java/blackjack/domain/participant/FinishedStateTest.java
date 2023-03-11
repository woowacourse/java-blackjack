package blackjack.domain.participant;

import static blackjack.Fixtures.BET_AMOUNT_10000;
import static blackjack.Fixtures.BLACKJACK_CARDS;
import static blackjack.Fixtures.CARDS_OF_BUST;
import static blackjack.Fixtures.CARDS_SUM_15;
import static blackjack.Fixtures.PLAYER_WITH_10000;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.player.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FinishedStateTest {

    @DisplayName("수익률 계산 기능")
    @Nested
    class EarningRate {
        @DisplayName("플레이어가 블랙잭일 때")
        @Nested
        class WhenPlayerIsBlackjack {
            private FinishedState finishedState = FinishedState.BLACK_JACK;
            private Player player;
            private Dealer dealer;

            @BeforeEach
            void setUp() {
                player = PLAYER_WITH_10000;
                player.hit(BLACKJACK_CARDS);
                dealer = new Dealer();
            }

            @DisplayName("딜러가 블랙잭이면")
            @Test
            void andDealerIsBlackjack() {
                //given
                dealer.hit(BLACKJACK_CARDS);
                //when
                double earningRate = finishedState.getEarningRate(player, dealer);
                //then
                Assertions.assertThat(earningRate).isEqualTo(1);
            }

            @DisplayName("딜러가 블랙잭이 아니면")
            @Test
            void andDealerIsNotBlackjack() {
                //given
                dealer.hit(CARDS_SUM_15);
                //when
                double earningRate = finishedState.getEarningRate(player, dealer);
                //then
                Assertions.assertThat(earningRate).isEqualTo(1.5);
            }
        }


        @DisplayName("플레이어가 버스트일 때")
        @Nested
        class WhenPlayerIsBust {
            private FinishedState finishedState = FinishedState.BUST;
            private Player player;
            private Dealer dealer;

            @BeforeEach
            void setUp() {
                player = PLAYER_WITH_10000;
                player.hit(CARDS_OF_BUST);
                dealer = new Dealer();
            }

            @DisplayName("딜러가 버스트가 아니면 -1을 반환한다.")
            @Test
            void returnZeroWhenDealerIsNotBust() {
                //given
                dealer.hit(CARDS_SUM_15);
                //when
                double earningRate = finishedState.getEarningRate(player, dealer);
                //then
                Assertions.assertThat(earningRate).isEqualTo(-1.0);
            }
        }

        @DisplayName("플레이어가 스테이일 때")
        @Nested
        class WhenPlayerIsStay {
            private FinishedState finishedState = FinishedState.STAY;
            private Player player;
            private Dealer dealer;

            @BeforeEach
            void setUp() {
                player = new Player(new Name("플레이어"), BET_AMOUNT_10000);
                player.hit(CARDS_SUM_15);
                dealer = new Dealer();
            }

            @DisplayName("딜러가 버스트이면 1을 반환한다.")
            @Test
            void returnZeroWhenDealerIsBust() {
                //given
                dealer.hit(CARDS_OF_BUST);
                //when
                double earningRate = finishedState.getEarningRate(player, dealer);
                //then
                Assertions.assertThat(earningRate).isEqualTo(1.0);
            }

            @DisplayName("딜러가 버스트가 아니고 점수가 더 높으면 -1을 반환한다.")
            @Test
            void returnZeroWhenDealerIsNotBustAndHasHigherScore() {
                //given
                dealer.hit(CARDS_SUM_15);
                dealer.hit(new Card(CardNumber.TWO, Pattern.HEART));
                //when
                double earningRate = finishedState.getEarningRate(player, dealer);
                //then
                Assertions.assertThat(earningRate).isEqualTo(-1.0);
            }

            @DisplayName("딜러가 버스트가 아니고 점수가 더 낮으면 1을 반환한다.")
            @Test
            void returnZeroWhenDealerIsNotBustAndHasLowerScore() {
                //given
                dealer.hit(
                        List.of(new Card(CardNumber.TWO, Pattern.HEART), new Card(CardNumber.EIGHT, Pattern.DIAMOND))
                );
                //when
                double earningRate = finishedState.getEarningRate(player, dealer);
                //then
                Assertions.assertThat(earningRate).isEqualTo(1.0);
            }
        }
    }

}