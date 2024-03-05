import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalDeckTest {

    @Test
    @DisplayName("전체 덱에서 카드를 가져온다.")
    void getNewCardTest() {
        Card card = new Card(Shape.CLOVER,Number.ACE);
        TotalDeck totalDeck = new TotalDeck(List.of(card));

        Card actualCard = totalDeck.getNewCard();

        assertThat(actualCard).isEqualTo(card);
    }
}
