package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("문양과 번호를 가지고 카드를 생성할 수 있다.")
    void createCard() {
        final CardPattern pattern = CardPattern.SPADE;
        final CardNumber number = CardNumber.A;
        final Card card = new Card(pattern, number);
        assertThat(card).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 52장을 반환할 수 있다.")
    void cards() {
        final List<Card> cards = Card.cards();
        final int distinctCount = (int) cards.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(52);
    }
}
