package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("카드 목록을 포함한 일급 컬렉션을 생성 한다.")
    public void Cards_Instance_create_with_CardList() {
        List<Card> cards = List.of(Card.EIGHT, Card.FOUR);

        assertThatCode(() -> new Cards(cards)).doesNotThrowAnyException();
    }
}
