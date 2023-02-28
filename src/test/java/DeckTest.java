import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("초기 카드 더미의 크기는 52장이다.")
    @Test
    void createDeckSizeSuccessTest(){
        Deck deck = new Deck();

        Assertions.assertThat(deck.size())
                .isEqualTo(52);
    }

}
