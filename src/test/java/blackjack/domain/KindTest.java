package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static blackjack.domain.Kind.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class KindTest {

    @DisplayName("정수값을 바탕으로 순차적으로 Kind를 찾을 수 있다")
    @Test
    void should_findKind_When_ExistKindNumber() {
        assertAll(
                () -> assertThat(SPADE).isEqualTo(findKind(0)),
                () -> assertThat(DIAMOND).isEqualTo(findKind(1)),
                () -> assertThat(HEART).isEqualTo(findKind(2)),
                () -> assertThat(CLOVER).isEqualTo(findKind(3))
        );
    }

    @DisplayName("존재하지 않는 Kind를 조회할 수 없다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 5})
    void should_ThrowIllegalStateException_When_NonExistKindNumber(int nonExistKindNumber) {
        assertThatThrownBy(() -> findKind(nonExistKindNumber))
                .isInstanceOf(IllegalStateException.class);
    }
}
