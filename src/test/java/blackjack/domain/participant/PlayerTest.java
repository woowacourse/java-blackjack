package blackjack.domain.participant;

import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.*;

import blackjack.domain.state.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("seung");
    }

    @Test
    @DisplayName("플레이어가 정상적으로 생성되는지 확인")
    void create() {
        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받는지 확인")
    void receiveCard() {
        player.hit(aceCard);

        assertThat(player.getCards()).containsExactly(aceCard);
    }

    @Test
    @DisplayName("카드를 받을 수 있는 상태인지 확인")
    void isFinished() {
        player.hit(kingCard);
        player.hit(tenCard);
        player.hit(jackCard);

        assertThat(player.isFinished()).isTrue();
    }

    @Test
    @DisplayName("카드를 받을 수 없는 상태인지 확인")
    void doesNotFinished() {
        player.hit(aceCard);
        player.hit(twoCard);

        assertThat(player.isFinished()).isFalse();
    }

    @Test
    @DisplayName("스테이 상태인지 확인")
    void stay() {
        player.hit(aceCard);
        player.hit(fiveCard);
        player.stay();
        Participant participant = player;

        assertThat(participant.getState()).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("준비 상태인지 확인")
    void isReady() {
        assertThat(player.isReady()).isTrue();
    }

    @Test
    @DisplayName("준비 상태가 아닌지 확인")
    void doesNotReady() {
        player.hit(kingCard);
        player.hit(tenCard);

        assertThat(player.isReady()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 finished 상태이면 hit해도 카드를 더이상 받을 수 없다.")
    void doesNotHit() {
        player.hit(kingCard);
        player.hit(tenCard);
        player.hit(threeCard);

        assertThatThrownBy(() -> player.hit(fourCard))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR] 카드뽑는걸 지원하지 않습니다.");
    }
}
