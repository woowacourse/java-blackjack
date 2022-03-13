package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("카드 묶음 생성 테스트")
    void createCards() {
        // given
        Cards cards = new Cards();
        cards.addCard(Card.of(Suit.CLUBS, Denomination.FIVE));
        cards.addCard(Card.of(Suit.HEARTS, Denomination.SEVEN));

        // when
        List<Card> rawCards = cards.getCards();

        // then
        assertThat(rawCards).contains(Card.of(Suit.CLUBS, Denomination.FIVE));
    }

    @Test
    @DisplayName("카드 묶음 컬렉션 수정 시도시 UOE 발생")
    void cardsShouldBeUnmodifiable() {
        // given
        Cards cards = new Cards();

        // when
        List<Card> unmodifiableCards = cards.getCards();

        // then
        assertThatThrownBy(
                () -> unmodifiableCards.add(Card.of(Suit.DIAMONDS, Denomination.JACK)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
