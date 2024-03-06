package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("카드 숫자 합으로 승패를 결정한다.")
    void determineWinStatus() {
        // TODO: 리팩토링 시급
        PlayerName kirby = new PlayerName("kirby");
        PlayerName baekho = new PlayerName("baekho");
        PlayerName pobi = new PlayerName("pobi");

        Score dealerScore = new Score(20);
        Player player1 = Player.from("kirby");
        List<Card> cards1 = List.of(new Card(CardNumber.TEN, CardShape.CLOVER), new Card(CardNumber.ACE, CardShape.CLOVER));
        cards1.forEach(player1::addCard);

        Player player2 = Player.from("baekho");
        List<Card> card2 = List.of(new Card(CardNumber.TEN, CardShape.SPADE), new Card(CardNumber.NINE, CardShape.SPADE));
        card2.forEach(player2::addCard);

        Player player3 = Player.from("pobi");
        List<Card> card3 = List.of(new Card(CardNumber.TEN, CardShape.DIA), new Card(CardNumber.KING, CardShape.DIA));
        card3.forEach(player3::addCard);

        Players players = new Players(List.of(player1, player2, player3));
        Map<PlayerName, WinStatus> finalResult = players.determineWinStatus(dealerScore);

        // TODO: 순서대로 검증도 되게
        assertThat(finalResult).containsExactlyInAnyOrderEntriesOf(Map.of(
                kirby, WinStatus.WIN,
                baekho, WinStatus.LOSE,
                pobi, WinStatus.DRAW));
    }

    @Test
    @DisplayName("중복된 플레이어를 생성할 수 없다.")
    void validateDuplicate() {
        // given
        PlayerName playerName = new PlayerName("kirby");
        Hands hands = new Hands(new Card(CardNumber.TEN, CardShape.SPADE), new Card(CardNumber.NINE, CardShape.SPADE));
        Player player = Player.from("kirby");
        List<Player> players = List.of(player, player);

        // when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름의 참여자는 참여할 수 없습니다.");
    }
}
