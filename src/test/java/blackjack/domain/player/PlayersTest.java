package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("참가자의 이름이 중복인 경우 확인")
    void duplicateTest() {
        assertThatThrownBy(() -> Players.fromNamesAndGeustHitStrategy(List.of("a", "a"), (p) -> HitFlag.Y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("초기 카드를 주어진 인자에 맞게 받는지 확인")
    void initHitCountTest() {
        Deck deck = new Deck();
        Players players = Players.fromNamesAndGeustHitStrategy(List.of("a", "b"), (p) -> HitFlag.Y);
        players.initHit(deck, 2);
        for (Player player : players.getPlayers()) {
            assertThat(player.getCards().getCardValues().size())
                    .isEqualTo(2);
        }
    }

    @Test
    @DisplayName("Players를 초기화하면 딜러를 잘 가지고 있는지 확인")
    void findDealerTest() {
        Players players = Players.fromNamesAndGeustHitStrategy(List.of("a", "b"), (p) -> HitFlag.Y);
        Player dealer = players.findDealer();
        assertThat(dealer instanceof Dealer)
                .isTrue();
    }

    @Test
    @DisplayName("Players가 딜러를 제외하고 게스트만 반환할 수 있는지 확인")
    void getGuestsTest() {
        Players players = Players.fromNamesAndGeustHitStrategy(List.of("a", "b"), (p) -> HitFlag.Y);
        List<Player> guests = players.getGuests();
        for (Player player : guests) {
            assertThat(player instanceof Guest)
                    .isTrue();
        }
    }

    @Test
    @DisplayName("참가자 이름을 8명 이상 입력하면 에러를 던지는지 확인")
    void maxSizeOverErrorTest() {
        assertThatThrownBy(() -> Players.fromNamesAndGeustHitStrategy(List.of("a", "b", "c", "d", "e", "f", "g", "h"), (p) -> HitFlag.Y))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("참가자는 딜러 포함 8명 까지만 가능합니다.");
    }
}
