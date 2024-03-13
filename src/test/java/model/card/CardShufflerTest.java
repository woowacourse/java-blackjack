package model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardShufflerTest {

    @DisplayName("생성 시 사용하는 덱 개수가 1 이상이면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 10, 20})
    void testValidCreateCardShuffler(int dequeCount) {
        int dequeSize = 52;
        CardShuffler cardShuffler = CardShuffler.of(dequeCount);
        assertThat(cardShuffler.cardsSize()).isEqualTo(dequeCount * dequeSize);
    }

    @DisplayName("생성 시 사용하는 덱 개수가 1 미만이면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -5, -200})
    void testInvalidCreateCardShuffler(int dequeCount) {
        assertThatThrownBy(() -> CardShuffler.of(dequeCount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 1장을 뽑으면 셔플된 카드의 사이즈가 1 감소한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 10, 20})
    void testDrawCard(int dequeCount) {
        CardShuffler cardShuffler = CardShuffler.of(dequeCount);
        int previousSize = cardShuffler.cardsSize();
        cardShuffler.drawCard();
        assertThat(cardShuffler.cardsSize()).isEqualTo(previousSize - 1);
    }

    @DisplayName("뽑을 카드가 없으면 예외 발생")
    @Test
    void testDrawCardFromEmptyCards() {
        CardShuffler cardShuffler = CardShuffler.of(1);

        IntStream.range(0, cardShuffler.cardsSize())
            .forEach(count -> cardShuffler.drawCard());

        assertThatThrownBy(cardShuffler::drawCard)
            .isInstanceOf(NoSuchElementException.class);
    }
}
