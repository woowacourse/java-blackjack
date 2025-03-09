package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("플레이어 이름 중 중복이 존재하면 예외가 발생한다.")
    void 플레이어_이름_중복_존재_예외() {
        //given
        List<String> names = List.of("pobi", "daro", "pobi");

        //when, then
        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("모든 플레이어에게 시작 카드를 분배한다.")
    void 모든_플레이어에게_시작_카드들을_분배() {
        //given
        Deck deck = Deck.of();
        Players players = new Players(List.of(
                new Player("moda"),
                new Player("daro"),
                new Player("pobi")));
        players.dealInitialCards(deck);

        //when, then
        for (Player player : players.getPlayers()) {
            assertThat(player.getHandCards().size()).isEqualTo(2);
        }
    }
}
