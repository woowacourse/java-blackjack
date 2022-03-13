package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("참가자의 이름이 중복인 경우 확인")
    void duplicateTest() {
        assertThatThrownBy(() -> Players.fromNames(List.of("a", "a"), (p) -> HitFlag.Y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("초기 카드를 주어진 인자에 맞게 받는지 확인")
    void initHitCountTest() {
        Deck deck = new Deck();
        Players players = Players.fromNames(List.of("a", "b"), (p) -> HitFlag.Y);
        players.initHit(deck, 2);
        for (Player player : players.getPlayers()) {
            assertThat(player.getCards().getCards().size())
                    .isEqualTo(2);
        }
    }
}
