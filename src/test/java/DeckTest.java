import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("초기 덱의 카드 수는 52장이어야한다.")
    void checkDeckSize() {

        Assertions.assertThat(Deck.cards.size()).isEqualTo(52);
    }

}
