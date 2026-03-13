package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SuitTest {

    @Test
    void Suit에_해당하는_이름을_제공한다() {
        assertThat(Suit.values())
                .extracting(Suit::getName)
                .containsExactly(
                        "스페이드", "하트", "다이아몬드", "클로버"
                );
    }

}