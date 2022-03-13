package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.MockDeck;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayersTest {

    @Nested
    @DisplayName("생성자는")
    class Constructor {

        @Test
        @DisplayName("8명을 초과할 때 예외를 발생시킨다.")
        void throwExceptionOverEight() {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                players.add(new Player(Integer.toString(i)));
            }
            Assertions.assertThatThrownBy(() -> new Players(players))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("인원수는 8명을 넘을 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("compete는")
    class Compete {

        @Test
        @DisplayName("딜러와 승부를 겨루고 결과를 맵으로 반환한다.")
        void returnScoreMap() {
            Player roma = new Player("roma");
            Player tonic = new Player("tonic");
            Player pobi = new Player("pobi");

            Players players = new Players(List.of(roma, tonic, pobi));
            MockDeck mockDeck = new MockDeck(List.of(
                    Card.of(CardPattern.DIAMOND, CardNumber.NINE),
                    Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                    Card.of(CardPattern.DIAMOND, CardNumber.ACE),
                    Card.of(CardPattern.SPADE, CardNumber.TEN)));
            players.drawAll(mockDeck);
            Dealer dealer = new Dealer();
            dealer.drawCard(mockDeck);
            ScoreResult result = players.compete(dealer);

            assertAll(
                    () -> assertThat(result.getPlayerScore(roma)).isEqualTo(Score.LOSE),
                    () -> assertThat(result.getPlayerScore(tonic)).isEqualTo(Score.DRAW),
                    () -> assertThat(result.getPlayerScore(pobi)).isEqualTo(Score.WIN),
                    () -> assertThat(result.getDealerScoreCount(Score.LOSE)).isEqualTo(1),
                    () -> assertThat(result.getDealerScoreCount(Score.DRAW)).isEqualTo(1),
                    () -> assertThat(result.getDealerScoreCount(Score.WIN)).isEqualTo(1)
            );
        }
    }

    @Nested
    @DisplayName("getCurrentTurnPlayer는")
    class GetCurrentTurnPlayer {

        @Test
        @DisplayName("현재 차례의 플레이어를 반환한다.")
        void getCurrentTurnPlayer() {
            Player roma = new Player("roma");
            Player tonic = new Player("tonic");
            Players players = new Players(List.of(roma, tonic));

            assertThat(players.getCurrentTurnPlayer()).isEqualTo(roma);
        }
    }

    @Nested
    @DisplayName("ProceedTurn은")
    class ProceedTurn {

        @Test
        @DisplayName("다음 차례로 턴을 넘긴다.")
        void proceedTurn() {
            Player roma = new Player("roma");
            Player tonic = new Player("tonic");
            Players players = new Players(List.of(roma, tonic));
            players.proceedTurn();

            assertThat(players.getCurrentTurnPlayer()).isEqualTo(tonic);
        }

        @Test
        @DisplayName("다음 턴을 진행할 플레이어가 없을 때 사용하면 예외를 발생시킨다.")
        void throwExceptionByOverAllTurn() {
            Player roma = new Player("roma");
            Player tonic = new Player("tonic");
            Players players = new Players(List.of(roma, tonic));
            players.proceedTurn();
            Player nowTurnPlayer = players.getCurrentTurnPlayer();
            System.out.println(nowTurnPlayer.getName());

            assertThatThrownBy(players::proceedTurn)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("proceed 할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("isBustNowTurnPlayer는")
    class isBustNowTurnPlayer {

        @ParameterizedTest
        @CsvSource(value = {"ACE|false", "QUEEN|true"}, delimiter = '|')
        @DisplayName("현재 차례의 플레이어가 버스트되었는지 확인한다.")
        void checkBustCurrentTurnPlayer(CardNumber cardNumber, boolean expected) {
            Player roma = new Player("roma");
            Player tonic = new Player("tonic");
            Players players = new Players(List.of(roma, tonic));

            MockDeck mockDeck = new MockDeck(List.of(
                    Card.of(CardPattern.CLOVER, CardNumber.QUEEN),
                    Card.of(CardPattern.CLOVER, CardNumber.QUEEN),
                    Card.of(CardPattern.DIAMOND, cardNumber)
            ));
            for (int i = 0; i < 3; i++) {
                players.drawPlayerCard(mockDeck);
            }

            assertThat(players.isBustCurrentTurnPlayer()).isEqualTo(expected);
        }
    }
}
