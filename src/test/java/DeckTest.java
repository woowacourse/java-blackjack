import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("생성되는 시점에 카드의 개수는 52장이어야 한다.")
    @Test
    void test6() {
        // given

        // when & then

    }

    @DisplayName("생성되는 시점에 카드의 개수가 52장이 아니면 예외를 던진다.")
    @Test
    void test1() {
        // given

        // when & then
//        assertThatThrownBy(() -> new Deck(cards))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage(ErrorMessage.INVALID_DECK_SIZE.getMessage());
    }

    @DisplayName("중복되는 카드가 존재하면 예외를 던진다.")
    @Test
    void test2() {

    }

    @DisplayName("카드는 섞일 수 있다.")
    @Test
    void test3() {

    }

    @DisplayName("원하는 개수만큼 카드를 뽑을 수 있다.")
    @Test
    void test4() {

    }

    @DisplayName("카드가 소진되면 예외를 던진다.")
    @Test
    void test5() {

    }
}
