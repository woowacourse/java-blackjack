package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TypeTest {
    @Test
    @DisplayName("타입 of")
    void of() {
        assertThat(Type.of("스페이드") == Type.SPADE).isTrue();
        assertThat(Type.of("하트") == Type.HEART).isTrue();
        assertThat(Type.of("클로버") == Type.CLOVER).isTrue();
        assertThat(Type.of("다이아몬드") == Type.DIAMOND).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 타입")
    void of_not_exist_type() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Type.of("세모"));
    }

    @Test
    @DisplayName("심볼 이름 확인")
    void getName() {
        assertThat(Type.of("스페이드").getName()).isEqualTo("스페이드");
        assertThat(Type.of("하트").getName()).isEqualTo("하트");
        assertThat(Type.of("클로버").getName()).isEqualTo("클로버");
        assertThat(Type.of("다이아몬드").getName()).isEqualTo("다이아몬드");
    }
}
