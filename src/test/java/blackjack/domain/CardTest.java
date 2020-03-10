package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("Deck 생성자 테스트")
    @Test
    void deckConstructorTest() {

        List<Card> cards = Card.createCards();
        // given
        Assertions.assertThat(cards.size()).isEqualTo(52);
        // when
        // then
    }
}
