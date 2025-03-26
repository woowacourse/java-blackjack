package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackJackWinningStatus;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerResultsTest {

    @Test
    @DisplayName("딜러가 블랙잭으로 한명을 이겼을 때의 계산되는 승패 결과의 수가 올바른지 확인한다")
    void should_return_dealer_winning_status_count_blackjack_win_count_1() {
        // given
        int blackjackWinCount = 1;
        List<DealerWinningStatus> dealerWinningStatuses = new ArrayList<>();
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.BLACK_JACK_WIN));
        DealerResults dealerResults = new DealerResults(dealerWinningStatuses);

        // when
        int result = dealerResults.getStatusCount(BlackJackWinningStatus.BLACK_JACK_WIN);

        // then
        assertThat(result).isEqualTo(blackjackWinCount);
    }

    @Test
    @DisplayName("딜러가 두명을 이겼을 때의 계산되는 승패 결과의 수가 올바른지 확인한다")
    void should_return_dealer_winning_status_count_win_count_2() {
        // given
        int winCount = 2;
        List<DealerWinningStatus> dealerWinningStatuses = new ArrayList<>();
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.WIN));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.WIN));
        DealerResults dealerResults = new DealerResults(dealerWinningStatuses);

        // when
        int result = dealerResults.getStatusCount(BlackJackWinningStatus.WIN);

        // then
        assertThat(result).isEqualTo(winCount);
    }

    @Test
    @DisplayName("딜러가 세명에게 비겼을 때의 계산되는 승패 결과의 수가 올바른지 확인한다")
    void should_return_dealer_winning_status_count_draw_count_3() {
        // given
        int drawCount = 3;
        List<DealerWinningStatus> dealerWinningStatuses = new ArrayList<>();
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.DRAW));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.DRAW));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.DRAW));
        DealerResults dealerResults = new DealerResults(dealerWinningStatuses);

        // when
        int result = dealerResults.getStatusCount(BlackJackWinningStatus.DRAW);

        // then
        assertThat(result).isEqualTo(drawCount);
    }


    @Test
    @DisplayName("딜러가 네명에게 블랙잭으로 졌을 때의 계산되는 승패 결과의 수가 올바른지 확인한다")
    void should_return_dealer_winning_status_blackjack_lose_count_4() {
        // given
        int blackjackLoseCount = 4;
        List<DealerWinningStatus> dealerWinningStatuses = new ArrayList<>();
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.BLACK_JACK_LOSE));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.BLACK_JACK_LOSE));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.BLACK_JACK_LOSE));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.BLACK_JACK_LOSE));
        DealerResults dealerResults = new DealerResults(dealerWinningStatuses);

        // when
        int result = dealerResults.getStatusCount(BlackJackWinningStatus.BLACK_JACK_LOSE);

        // then
        assertThat(result).isEqualTo(blackjackLoseCount);
    }


    @Test
    @DisplayName("딜러가 다섯명에게 졌을 때의 계산되는 승패 결과의 수가 올바른지 확인한다")
    void should_return_dealer_winning_status_lose_count_5() {
        // given
        int loseCount = 5;
        List<DealerWinningStatus> dealerWinningStatuses = new ArrayList<>();
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.LOSE));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.LOSE));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.LOSE));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.LOSE));
        dealerWinningStatuses.add(new DealerWinningStatus("opponentName", BlackJackWinningStatus.LOSE));
        DealerResults dealerResults = new DealerResults(dealerWinningStatuses);

        // when
        int result = dealerResults.getStatusCount(BlackJackWinningStatus.LOSE);

        // then
        assertThat(result).isEqualTo(loseCount);
    }
}
