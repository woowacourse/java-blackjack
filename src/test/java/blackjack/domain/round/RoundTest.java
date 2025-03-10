package blackjack.domain.round;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoundTest {

    @Test
    @DisplayName("플레이어에게 카드를 지급한다")
    void drawPlayerCardTest() {
        // given
        Round round = new Round(new Dealer(), List.of(new Player("Pobi")));

        // when
        round.initialize();
        round.drawPlayerCard("Pobi");
        List<Player> players = round.getPlayers();

        // then
        assertThat(players.getFirst().getCardCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 카드 지급 시 플레이어 목록에 없는 이름을 전달할 경우 예외가 발생한다")
    void drawPlayerCardExceptionTest() {
        // given
        Dealer dealer = new Dealer();
        List<Player> players = List.of(new Player("Pobi"));

        // when
        Round round = new Round(dealer, players);

        // then
        assertThatThrownBy(() -> round.drawPlayerCard("Neo")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("딜러에게 카드를 지급한다")
    void drawDealerCardTest() {
        // given
        Round round = new Round(new Dealer(), List.of(new Player("Pobi")));

        // when
        round.initialize();
        round.drawDealerCard();

        // then
        assertThat(round.getDealer().getCardCount()).isEqualTo(3);
    }
}
