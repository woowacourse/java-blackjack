package domain.participant;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayersTest {

    @Test
    @DisplayName("플레이어 인원 수는 5명 이하여야 합니다.")
    void 플레이어인원수_5명이하_성공() {
        // given
        List<Player> players = List.of(new Player("pobi"), new Player("james"));

        // when - then
        Assertions.assertDoesNotThrow(() -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 인원 수는 6명 이상인 경우 오류가 발생해야 한다.")
    void 플레이어인원수_6명이상_오류() {
        // given
        List<Player> players = List.of(
                new Player("pobi")
                , new Player("james")
                , new Player("eunoh")
                , new Player("ruro")
                , new Player("rama")
                , new Player("dudu"));

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 이름이 중복되는 경우 오류가 발생해야 한다.")
    void 플레이어_이름_중복_금지() {
        // given
        List<Player> players = List.of(
                new Player("pobi")
                , new Player("james")
                , new Player("pobi"));

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어를 정확하게 찾는지 검증한다.")
    void findByTest() {
        // given
        Player targetPlayer = new Player("pobi");
        List<Player> playerNames = List.of(
                targetPlayer
                , new Player("james"));

        // when - then
        Players players = new Players(playerNames);

        assertEquals(players.findBy(targetPlayer), targetPlayer);
    }

    @Test
    @DisplayName("모든 플레이어의 승패 결과를 계산한다.")
    void calculateMatchResultTest() {
        // given
        Dealer dealer = new Dealer();

        Player player1 = new Player("pobi");
        Player player2 = new Player("jason");

        Players players = new Players(List.of(player1, player2));

        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));
        dealer.receive(new Card(Rank.SEVEN, Suit.HEART)); // 17

        player1.receive(new Card(Rank.KING, Suit.CLOVER));
        player1.receive(new Card(Rank.QUEEN, Suit.HEART)); // 20 -> WIN

        player2.receive(new Card(Rank.TEN, Suit.CLOVER));
        player2.receive(new Card(Rank.SEVEN, Suit.CLOVER)); // 17 -> DRAW

        // when
        Map<Player, MatchResult> result = players.calculateMatchResult(dealer);

        // then
        assertEquals(MatchResult.WIN, result.get(player1));
        assertEquals(MatchResult.DRAW, result.get(player2));
    }
}
