package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("카드 숫자 합으로 승패를 결정한다.")
    void name() {
        // TODO: 리팩토링 시급
        Cards cards1 = new Cards(new Card(CardNumber.TEN, CardShape.HEART),
                new Card(CardNumber.QUEEN, CardShape.HEART));
        Cards cards2 = new Cards(new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.ACE, CardShape.CLOVER));
        Cards cards3 = new Cards(new Card(CardNumber.TEN, CardShape.SPADE), new Card(CardNumber.NINE, CardShape.SPADE));
        Cards cards4 = new Cards(new Card(CardNumber.TEN, CardShape.DIA), new Card(CardNumber.KING, CardShape.DIA));

        Name kirby = new Name("kirby");
        Name baekho = new Name("baekho");
        Name pobi = new Name("pobi");

        Dealer dealer = new Dealer(cards1);
        Player player1 = new Player(kirby, cards2);
        Player player2 = new Player(baekho, cards3);
        Player player3 = new Player(pobi, cards4);

        Players players = new Players(List.of(player1, player2, player3));
        FinalResult finalResult = players.determineWinStatus(dealer);

        // TODO: 순서대로 검증도 되게
        assertThat(finalResult.getPlayersWinstatus()).containsExactlyInAnyOrderEntriesOf(Map.of(
                kirby, WinStatus.WIN,
                baekho, WinStatus.LOSE,
                pobi, WinStatus.DRAW));
    }
}
