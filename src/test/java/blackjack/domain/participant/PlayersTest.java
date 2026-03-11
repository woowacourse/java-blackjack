package blackjack.domain.participant;

import blackjack.domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    void 플레이어가_0명이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 1명 이상이어야 합니다.");
    }

    @Test
    void 중복된_이름의_플레이어가_있으면_예외가_발생한다() {
        List<Player> duplicated = List.of(new Player("pobi"), new Player("pobi"));

        assertThatThrownBy(() -> new Players(duplicated))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름의 플레이어가 있습니다.");
    }

    @Test
    void 플레이어가_1명이면_생성에_성공한다() {
        List<Player> players = List.of(new Player("pobi"));

        Players result = new Players(players);

        assertThat(result.players()).hasSize(1);
    }

    @Test
    void 플레이어들은_베팅_금액을_입력하여_베팅을_하고_베팅_결과를_Map으로_반환하여_검증할_수_있다() {
        Player 포비 = new Player("pobi");
        Player 티뉴 = new Player("tinuu");
        Money wager = new Money(10000);
        Players players = new Players(List.of(포비, 티뉴));

        Map<Player, Money> wagers = players.placeWagers(player -> wager);

        Assertions.assertAll(
                () -> assertThat(wagers.get(포비)).isEqualTo(wager),
                () -> assertThat(wagers.get(티뉴)).isEqualTo(wager)
        );
    }
}
