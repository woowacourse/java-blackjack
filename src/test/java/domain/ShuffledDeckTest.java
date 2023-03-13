package domain;

import static org.assertj.core.api.Assertions.*;

import domain.card.Deck;
import domain.card.ShuffledDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class ShuffledDeckTest {

    @DisplayName("6덱 생성 후 312장 반환을 확인한다.")
    @Test
    void 카드에서_생성_후_52장_반환_확인() {
        Deck shuffledDeck = ShuffledDeck.createByCount(6);
        for (int i = 0; i < 6 * 52; i++) {
            shuffledDeck.draw();
        }
        assertThatThrownBy(shuffledDeck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱이 비었습니다.");
    }

    @DisplayName("잘못된 덱 개수로 생성하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 3, 9})
    void 잘못된_덱_개수_예외_발생(int inputDeckCount) {
        assertThatThrownBy(() -> ShuffledDeck.createByCount(inputDeckCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[1, 2, 4, 6, 8]개의 덱만 사용 가능합니다.");
    }
}
