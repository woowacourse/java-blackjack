package model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardShufflerTest {

    @DisplayName("덱의 개수만큼 카드를 생성하여 셔플한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 10, 20})
    void testCreateCardShuffler(int dequeCount) {
        int dequeSize = 52;
        CardShuffler cardShuffler = CardShuffler.of(dequeCount);
        assertThat(cardShuffler.cardsSize()).isEqualTo(dequeCount * dequeSize);
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
}
