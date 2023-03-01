package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("정적 팩토리 메서드에 shuffle전략을 이용해 deck생성 시 정상적으로 동작한다.")
    void DeckFromTest() {
        Assertions.assertThatCode(() -> Deck.from(new RandomShuffleStrategy()))
            .doesNotThrowAnyException();
    }
}
