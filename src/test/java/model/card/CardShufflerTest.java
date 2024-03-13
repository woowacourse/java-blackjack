package model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardShufflerTest {

    @DisplayName("덱의 개수만큼 랜덤으로 카드를 정렬하여 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 4, 5, 10, 20})
    void testCreateCardShuffler(int dequeCount) {
        int dequeSize = 52;
        CardShuffler cardShuffler = CardShuffler.of(dequeCount);
        assertThat(cardShuffler.cardsSize()).isEqualTo(dequeCount * dequeSize);
    }
}
