package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static blackjack.domain.CardConstant.*;

class PlayerTest {

    Player player;

    @BeforeEach
    void setting() {
        player = Player.of(Name.from("test"), new ArrayList<>());
        player.drawCard(DIAMOND_TEN);
        player.drawCard(DIAMOND_EIGHT);
    }

    @Test
    @DisplayName("플레이어의 점수가 20 이하일 때, 히트인지 테스트")
    void isHitTest() {
        // when
        player.drawCard(DIAMOND_TWO);

        // then
        Assertions.assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 점수가 21 이상일 때, 히트가 아닌지 테스트")
    void isNotHitTest() {
        // when
        player.drawCard(DIAMOND_THREE);

        // then
        Assertions.assertThat(player.canHit()).isFalse();
    }
}
