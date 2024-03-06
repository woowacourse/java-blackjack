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
        Cards cards2 = new Cards(new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.ACE, CardShape.CLOVER));
        Cards cards3 = new Cards(new Card(CardNumber.TEN, CardShape.SPADE), new Card(CardNumber.NINE, CardShape.SPADE));
        Cards cards4 = new Cards(new Card(CardNumber.TEN, CardShape.DIA), new Card(CardNumber.KING, CardShape.DIA));

        Name kirby = new Name("kirby");
        Name baekho = new Name("baekho");
        Name pobi = new Name("pobi");

        Score dealerScore = new Score(20);
        Player player1 = new Player(kirby, cards2);
        Player player2 = new Player(baekho, cards3);
        Player player3 = new Player(pobi, cards4);

        Players players = new Players(List.of(player1, player2, player3));
        Map<Name, WinStatus> finalResult = players.determineWinStatus(dealerScore);

        // TODO: 순서대로 검증도 되게
        assertThat(finalResult).containsExactlyInAnyOrderEntriesOf(Map.of(
                kirby, WinStatus.WIN,
                baekho, WinStatus.LOSE,
                pobi, WinStatus.DRAW));
    }

    @Test
    void validateDuplicate() {
        // given
        Name name = new Name("kirby");
        Cards cards = new Cards(new Card(CardNumber.TEN, CardShape.SPADE), new Card(CardNumber.NINE, CardShape.SPADE));
        Player player = new Player(name, cards);
        List<Player> players = List.of(player, player);

        // when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름의 참여자는 참여할 수 없습니다.");
    }
}
