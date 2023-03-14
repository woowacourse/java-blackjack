package service;

import domain.participant.Player;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackjackServiceTest {

    @Test
    void n를_입력하면_플레이어는_스탠드가_된다() {
        //given
        final BlackjackService service = BlackjackService.of(List.of("연어"), Collections::shuffle);
        final Player player = service.getPlayers().get(0);
        final boolean previous = player.isStand();

        //when
        service.playNextTurnPlayer(player, "n");

        //then
        assertThat(previous).isFalse();
        assertThat(player.isStand()).isTrue();
    }

    @Test
    void y를_입력하면_플레이어는_카드를_받고_스탠드_상태가_아니다() {
        //given
        final BlackjackService service = BlackjackService.of(List.of("연어"), Collections::shuffle);
        final Player player = service.getPlayers().get(0);

        //when
        service.playNextTurnPlayer(player, "y");

        //then
        assertThat(player.getCards()).hasSize(3);
        assertThat(player.isStand()).isFalse();
    }

    @Test
    void y또는_n을_입력하지_않으면_예외가_발생한다() {
        //given
        final BlackjackService service = BlackjackService.of(List.of("연어"), Collections::shuffle);
        final Player player = service.getPlayers().get(0);

        //when

        //then
        assertThatThrownBy(() -> service.playNextTurnPlayer(player, "h"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] y 또는 n만 입력할 수 있습니다");
    }
}
