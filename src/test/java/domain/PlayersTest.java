package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 플레이어_리스트로부터_모델을_생성한다() {
        // given
        List<Player> players = List.of(Player.of("pobi1"),
                Player.of("pobi2"),
                Player.of("pobi3"),
                Player.of("pobi4"),
                Player.of("pobi5"));

        // when & then
        assertThatCode(() -> Players.of(players))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이어가_5명_초과일_때_예외가_발생한다() {
        // given
        List<Player> players = List.of(Player.of("pobi1"),
                Player.of("pobi2"),
                Player.of("pobi3"),
                Player.of("pobi4"),
                Player.of("pobi5"),
                Player.of("pobi6"));

        // when & then
        assertThatThrownBy(() -> Players.of(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여 가능한 플레이어는 최대 5명 입니다.");
    }

    @Test
    void 플레이어들이_딜러에게_카드를_한_장씩_받는다() {
        // given
        List<Player> participants = List.of(Player.of("pobi1"),
                Player.of("pobi2"),
                Player.of("pobi3"),
                Player.of("pobi4"),
                Player.of("pobi5"));
        Players players = Players.of(participants);
        Dealer dealer = Dealer.of(CardDeck.of());

        // when
        players.receiveCardFrom(dealer);

        // then
        assertThatCode(() -> players.receiveCardFrom(dealer))
                .doesNotThrowAnyException();
    }
}