package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에서 카드를 정상적으로 뽑아 반환한다.")
    @Test
    void drawCard() {
        Deck deck = new Deck(List.of(new Card(0), new Card(1)));
        Card drawnCard = deck.draw();
        assertThat(drawnCard).isNotNull();
    }

    @DisplayName("덱에 남은 카드가 없을 때 카드를 뽑으려 하면 예외가 발생한다.")
    @Test
    void throwExceptionWhenDeckIsEmpty() {
        Deck deck = new Deck(List.of());
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("남은 카드가 없습니다.");
    }
}
