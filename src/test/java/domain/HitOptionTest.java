package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitOptionTest {

    @DisplayName("HIT 이면 카드를 더 받는다.")
    @Test
    void meanHitAccordingToLetter() {
        assertThat(HitOption.HIT.isHit()).isTrue();
    }

    @DisplayName("NOT_HIT 이면 카드를 더 받지 않는다.")
    @Test
    void meanNoHitAccordingToLetter() {
        assertThat(HitOption.NOT_HIT.isHit()).isFalse();
    }
}
