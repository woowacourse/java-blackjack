import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalDeckTest {

    @Test
    @DisplayName("전체 덱에서 카드를 가져온다.")
    void getNewCardTest() {
        //given
        Card card = new Card();
        TotalDeck totalDeck = new TotalDeck(List.of(card));
        //when
        Card actualCard = totalDeck.getNewCard();
        //then
        assertThat(actualCard).isEqualTo(card);
    }
}
