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

    @DisplayName("카드가 남아있지 않으면 뽑을 수 없다.")
    @Test
    void drawCardFailTestWhenDeckIsEmpty() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        Assertions.assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class);

    }

}
