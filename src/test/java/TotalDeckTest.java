import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalDeckTest {

    @Test
    @DisplayName("전체 덱에서 카드를 가져온다.")
    void getNewCardTest() {
        //given
        TotalDeck totalDeck = new TotalDeck();
        //when
        Card expectedCard = totalDeck.list.get(0);
        Card actualCard = totalDeck.getNewCard();
        //then
        assertThat(actualCard).isEqualTo(expectedCard);
    }
}
