package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResultTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        player = new Player(new Name("name"));
        dealer = new Dealer();
    }

    @Test
    @DisplayName("무승부: 플레이어 점수 == 딜러 점수")
    void tie_PlayerScoreIsEqualToDealerScore() {
        player.receive(new Card(Rank.EIGHT, Symbol.DIAMOND));
        dealer.receive(new Card(Rank.EIGHT, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        assertThat(result.playerWinLose(player)).isEqualTo(WinLose.TIE);
    }

    @Test
    @DisplayName("플레이어 패배: 플레이어 점수 < 딜러 점수")
    void playerLose_PlayerScoreIsLessThanDealerScore() {
        player.receive(new Card(Rank.EIGHT, Symbol.DIAMOND));
        dealer.receive(new Card(Rank.NINE, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        assertThat(result.playerWinLose(player)).isEqualTo(WinLose.LOSE);
    }

    @Test
    @DisplayName("플레이어 승리: 플레이어 점수 > 딜러 점수")
    void playerWin_PlayerScoreIsGreaterThanDealerScore() {
        player.receive(new Card(Rank.NINE, Symbol.DIAMOND));
        dealer.receive(new Card(Rank.EIGHT, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        assertThat(result.playerWinLose(player)).isEqualTo(WinLose.WIN);
    }

    @Test
    @DisplayName("플레이어 패배: 플레이어 버스트(경계값 22점)")
    void playerLose_PlayerIsBusted() {
        player.receive(new Card(Rank.KING, Symbol.DIAMOND));
        player.receive(new Card(Rank.QUEEN, Symbol.CLUB));
        player.receive(new Card(Rank.TWO, Symbol.HEART));
        dealer.receive(new Card(Rank.KING, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        assertThat(result.playerWinLose(player)).isEqualTo(WinLose.LOSE);
    }

    @Test
    @DisplayName("플레이어 승리: 딜러 버스트(경계값 22점), 플레이어 생존")
    void playerWin_DealerIsBustedAndPlayerIsNotBusted() {
        dealer.receive(new Card(Rank.KING, Symbol.DIAMOND));
        dealer.receive(new Card(Rank.SIX, Symbol.CLUB));
        dealer.receive(new Card(Rank.SIX, Symbol.HEART));
        player.receive(new Card(Rank.KING, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        assertThat(result.playerWinLose(player)).isEqualTo(WinLose.WIN);
    }

    @Test
    @DisplayName("플레이어 패배: 딜러 버스트(22점), 플레이어 버스트(22점)")
    void playerLose_DealerIsBustedAndPlayerIsBusted() {
        dealer.receive(new Card(Rank.KING, Symbol.DIAMOND));
        dealer.receive(new Card(Rank.SIX, Symbol.CLUB));
        dealer.receive(new Card(Rank.SIX, Symbol.HEART));

        player.receive(new Card(Rank.KING, Symbol.HEART));
        player.receive(new Card(Rank.KING, Symbol.CLUB));
        player.receive(new Card(Rank.TWO, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        assertThat(result.playerWinLose(player)).isEqualTo(WinLose.LOSE);
    }

    @Nested
    class countTest {

        private List<Player> players;
        private Dealer dealer;

        // winPlayer: 21점, losePlayer: 5점, tiePlayer: 17점, dealer: 17점)
        @BeforeEach
        void beforeEach() {
            players = new ArrayList<>(List.of(
                    new Player(new Name("A")),
                    new Player(new Name("B")),
                    new Player(new Name("C"))));
            dealer = new Dealer();
            dealer.receive(List.of(new Card(Rank.TEN, Symbol.CLUB), new Card(Rank.SEVEN, Symbol.CLUB)));
        }

        @Test
        @DisplayName("성공: 딜러 승리 수 == 플레이어 패배 수")
        void countDealerWins() {
            distributeLoseCards(players);
            Result result = Result.of(players, dealer);

            assertThat(result.countDealerWins()).isEqualTo(3);
        }

        @Test
        @DisplayName("성공: 딜러 패배 수 == 플레이어 승리 수")
        void countDealerLoses() {
            distributeWinCards(players);
            Result result = Result.of(players, dealer);

            assertThat(result.countDealerLoses()).isEqualTo(3);
        }

        @Test
        @DisplayName("성공: 딜러 무승부 수 == 플레이어 무승부 수")
        void countDealerTies() {
            distributeTieCards(players);
            Result result = Result.of(players, dealer);

            assertThat(result.countDealerTies()).isEqualTo(3);
        }

        private void distributeWinCards(List<Player> players) {
            for (Player player : players) {
                player.receive(List.of(new Card(Rank.KING, Symbol.CLUB), new Card(Rank.ACE, Symbol.CLUB)));
            }
        }

        private void distributeLoseCards(List<Player> players) {
            for (Player player : players) {
                player.receive(List.of(new Card(Rank.TWO, Symbol.CLUB), new Card(Rank.THREE, Symbol.CLUB)));
            }
        }

        private void distributeTieCards(List<Player> players) {
            for (Player player : players) {
                player.receive(List.of(new Card(Rank.TEN, Symbol.CLUB), new Card(Rank.SEVEN, Symbol.CLUB)));
            }
        }
    }
}
