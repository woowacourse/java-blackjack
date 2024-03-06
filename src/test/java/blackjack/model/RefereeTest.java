package blackjack.model;

import static blackjack.model.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RefereeTest {
    @Nested
    @DisplayName("딜러의 합이 21일 미만이면")
    class under21 {

        @Test
        @DisplayName("플레이어의 합이 딜러보다 크면 플레이어가 승리한다.")
        void playerWinWhenBiggerThanDealer() {
            Players players = Players.from(
                    List.of("몰리"),
                    List.of(new Cards(List.of(new Card(Shape.CLOVER, Score.TEN), new Card(Shape.SPADE, Score.TEN)))));
            Dealer dealer = new Dealer(new Cards(List.of(new Card(Shape.SPADE, Score.SEVEN), new Card(Shape.DIA, Score.TEN))));

            Referee referee = new Referee(dealer, players);
            assertThat(referee.judgePlayerWinner()).containsExactlyEntriesOf(Map.of("몰리", WIN));
        }

        @Test
        @DisplayName("플레이어 결과가 딜러의 결과와 동일하지만 카드 수는 적은 경우 플레이어가 승리한다.")
        void playerWinWhenHasLittleCardsThanDealer() {
            Players players = Players.from(
                    List.of("몰리"),
                    List.of(new Cards(List.of(new Card(Shape.CLOVER, Score.TEN), new Card(Shape.SPADE, Score.TEN)))));
            Dealer dealer = new Dealer(new Cards(List.of(new Card(Shape.SPADE, Score.SEVEN), new Card(Shape.DIA, Score.TEN), new Card(Shape.DIA, Score.THREE))));

            Referee referee = new Referee(dealer, players);
            assertThat(referee.judgePlayerWinner()).containsExactlyEntriesOf(Map.of("몰리", WIN));
        }
    }
}
