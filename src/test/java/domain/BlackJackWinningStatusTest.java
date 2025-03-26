package domain;

import static domain.BlackJackWinningStatus.BLACK_JACK_LOSE;
import static domain.BlackJackWinningStatus.BLACK_JACK_WIN;
import static domain.BlackJackWinningStatus.DRAW;
import static domain.BlackJackWinningStatus.LOSE;
import static domain.BlackJackWinningStatus.WIN;
import static domain.BlackJackWinningStatus.calculateDealerResult;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackJackWinningStatusTest {

    @Nested
    @DisplayName("블랙잭이 나온 결과 계산")
    class BlackJackWinningCase {
        @ParameterizedTest
        @DisplayName("블랙잭으로 딜러가 이기는 경우")
        @CsvSource(value = {"true, 21, false, 21",
                "true, 21, false, 20"})
        void should_return_BLACK_JACK_WIN_when_dealer_blackjack(boolean isDealerBlackJack, int dealerValue,
                                                                boolean isPlayerBlackJack, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(isDealerBlackJack, dealerValue,
                    isPlayerBlackJack, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(BLACK_JACK_WIN);
        }

        @ParameterizedTest
        @DisplayName("블랙잭으로 딜러가 지는 경우")
        @CsvSource(value = {"false, 21, true, 21",
                "false, 20, true, 21"})
        void should_return_BLACK_JACK_LOSE_when_player_blackjack(boolean isDealerBlackJack, int dealerValue,
                                                                 boolean isPlayerBlackJack, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(isDealerBlackJack, dealerValue,
                    isPlayerBlackJack, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(BLACK_JACK_LOSE);
        }

        @ParameterizedTest
        @DisplayName("딜러, 플레이어가 블랙잭으로 비기는 경우")
        @CsvSource(value = {"true, 21, true, 21"})
        void should_return_DRAW_when_each_blackjack(boolean isDealerBlackJack, int dealerValue,
                                                    boolean isPlayerBlackJack, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(isDealerBlackJack, dealerValue,
                    isPlayerBlackJack, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(DRAW);
        }
    }


    @Nested
    @DisplayName("버스트가 나온 결과 계산")
    class BustWinningCase {
        @ParameterizedTest
        @DisplayName("딜러와 플레이어가 버스트로 딜러가 이기는 경우")
        @CsvSource(value = {"22, 22", "23, 22", "22, 23"})
        void should_return_DRAW_when_each_burst(int dealerValue, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(false, dealerValue, false, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(WIN);
        }

        @ParameterizedTest
        @DisplayName("플레이어가 버스트로 딜러가 승리하는 경우")
        @CsvSource(value = {"10, 22", "21, 22"})
        void should_return_WIN_when_player_burst(int dealerValue, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(false, dealerValue, false, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(WIN);
        }

        @ParameterizedTest
        @DisplayName("딜러가 버스트로 딜러가 패배하는 경우")
        @CsvSource(value = {"22, 10", "22, 21"})
        void should_return_LOSE_when_dealer_burst(int dealerValue, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(false, dealerValue, false, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(LOSE);
        }
    }


    @Nested
    @DisplayName("점수로 결과 계산")
    class ValueWinningCase {
        @ParameterizedTest
        @DisplayName("딜러와 플레이어의 점수가 동일하여 비기는 경우")
        @CsvSource(value = {"11, 11", "15, 15", "21, 21"})
        void should_return_DRAW_when_same_value(int dealerValue, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(false, dealerValue, false, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(DRAW);
        }

        @ParameterizedTest
        @DisplayName("딜러의 점수가 더 높아 딜러가 이기는 경우")
        @CsvSource(value = {"21, 20", "10, 9"})
        void should_return_WIN_when_dealer_value_high(int dealerValue, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(false, dealerValue, false, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(WIN);
        }

        @ParameterizedTest
        @DisplayName("플레이어의 점수가 더 높아 딜러가 지는 경우")
        @CsvSource(value = {"20, 21", "9, 10"})
        void should_return_LOSE_when_player_value_high(int dealerValue, int playerValue) {
            // when
            BlackJackWinningStatus dealerResult = calculateDealerResult(false, dealerValue, false, playerValue);

            // then
            assertThat(dealerResult).isEqualTo(LOSE);
        }
    }

    @ParameterizedTest
    @DisplayName("반대의 결과를 계산한다")
    @CsvSource(value = {"BLACK_JACK_WIN, BLACK_JACK_LOSE", "BLACK_JACK_LOSE, BLACK_JACK_WIN", "WIN, LOSE", "DRAW, DRAW",
            "LOSE, WIN"})
    void should_return_reverse_result(BlackJackWinningStatus given, BlackJackWinningStatus expected) {
        // when
        BlackJackWinningStatus result = given.reverse();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("손패의 합계가 bust 조건이라면 true를 반환한다.")
    @CsvSource(value = {"21, false", "22,true"})
    void should_return_true_when_burst(int value, boolean expected) {
        // when
        boolean result = BlackJackWinningStatus.isBurstBy(value);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
