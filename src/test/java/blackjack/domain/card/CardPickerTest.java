package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카드 피커")
public class CardPickerTest {
    @Test
    @DisplayName("게임 시작 시 인원 당 받는 덱의 수는 2장이다.")
    void gameCreateTest() {
        // given
        CardPicker cardPicker = new CardPicker(new Deck(Arrays.asList(Card.values())));
        int count = 2;
        int expectedCount = 2;

        // when
        List<Card> cards = cardPicker.pickCards(count);

        // then
        assertThat(cards.size()).isEqualTo(expectedCount);
    }
}
