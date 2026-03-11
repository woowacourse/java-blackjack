package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    Players players;

    @BeforeEach
    void beforeEach() {
        players = new Players(List.of("pobi", "jason"));
    }

    @DisplayName("입력에 따른 Player 객체 생성")
    @Test
    void 이름이_정상적으로_들어왔을때() {
        assertThat(players.getSize()).isEqualTo(3); // 딜러 포함
    }

    @DisplayName("이름이 중복이면 예외가 발생한다")
    @Test
    void 이름이_중복이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Players(List.of("아나키", "아나키", "모아")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어 점수가 딜러보다 높으면 승리한다")
    @Test
    void 플레이어_점수가_딜러보다_높으면_승리한다() {
        players.getDealer().addCard(new Card(Rank.TEN, Suit.SPADE));       // 10
        players.getGamePlayers().get(0).addCard(new Card(Rank.KING, Suit.HEART)); // 10
        players.getGamePlayers().get(0).addCard(new Card(Rank.ACE, Suit.SPADE));  // 21

        Map<Player, Result> result = players.judge();
        assertThat(result.get(players.getGamePlayers().get(0))).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 점수가 딜러보다 낮으면 패배한다")
    @Test
    void 플레이어_점수가_딜러보다_낮으면_패배한다() {
        players.getDealer().addCard(new Card(Rank.KING, Suit.SPADE));      // 10
        players.getDealer().addCard(new Card(Rank.NINE, Suit.SPADE));      // 19
        players.getGamePlayers().get(0).addCard(new Card(Rank.SEVEN, Suit.HEART)); // 7

        Map<Player, Result> result = players.judge();
        assertThat(result.get(players.getGamePlayers().get(0))).isEqualTo(Result.LOSE);
    }

    @DisplayName("동점이면 딜러가 이긴다")
    @Test
    void 동점이면_딜러가_이긴다() {
        players.getDealer().addCard(new Card(Rank.KING, Suit.SPADE));      // 10
        players.getGamePlayers().get(0).addCard(new Card(Rank.TEN, Suit.HEART));  // 10

        Map<Player, Result> result = players.judge();
        assertThat(result.get(players.getGamePlayers().get(0))).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 버스트면 패배한다")
    @Test
    void 플레이어가_버스트면_패배한다() {
        players.getDealer().addCard(new Card(Rank.FIVE, Suit.SPADE));      // 5
        players.getGamePlayers().get(0).addCard(new Card(Rank.KING, Suit.HEART)); // 10
        players.getGamePlayers().get(0).addCard(new Card(Rank.QUEEN, Suit.HEART)); // 10
        players.getGamePlayers().get(0).addCard(new Card(Rank.FIVE, Suit.DIAMOND)); // 25

        Map<Player, Result> result = players.judge();
        assertThat(result.get(players.getGamePlayers().get(0))).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 버스트면 플레이어가 승리한다")
    @Test
    void 딜러가_버스트면_플레이어가_승리한다() {
        players.getDealer().addCard(new Card(Rank.KING, Suit.SPADE));      // 10
        players.getDealer().addCard(new Card(Rank.QUEEN, Suit.SPADE));     // 10
        players.getDealer().addCard(new Card(Rank.FIVE, Suit.SPADE));      // 25
        players.getGamePlayers().get(0).addCard(new Card(Rank.SEVEN, Suit.HEART)); // 7

        Map<Player, Result> result = players.judge();
        assertThat(result.get(players.getGamePlayers().get(0))).isEqualTo(Result.WIN);
    }
}
