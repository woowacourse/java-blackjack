package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("카드는 null일 수 없다.")
    void notNull() {
        // then
        assertThatThrownBy(() -> new Cards(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 카드는 null일 수 없습니다.");
    }

    @Test
    @DisplayName("시작 시 카드를 두 장 가지고 있어야 한다.")
    void validCardsSize() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        // when
        Cards actual = new Cards(cards);

        // then
        assertThat(actual.getCards()).containsOnly(card1, card2);
    }

    @Test
    @DisplayName("시작 시 카드가 두 장이 아니면 예외가 발생한다.")
    void invalidCardsSize() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Card card3 = new Card(Pattern.HEART, Denomination.THREE);
        List<Card> cards = List.of(card1, card2, card3);

        // then
        assertThatThrownBy(() -> new Cards(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
    }

    @Test
    @DisplayName("카드가 중복되면 예외가 발생한다.")
    void duplicatedCards() {
        // given
        Card card = new Card(Pattern.DIAMOND, Denomination.THREE);
        List<Card> cards = List.of(card, card);

        // then
        assertThatThrownBy(() -> new Cards(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드는 중복될 수 없습니다.");
    }
}
