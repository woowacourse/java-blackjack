package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.CardConstant.DIAMOND_JACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @Test
    @DisplayName("52장의 트럼프 카드를 모두 생성하는지 테스트")
    void createAllCardTest() {
        final Deck deck = TestDeck.create();

        Assertions.assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("중복된 카드를 주입하면 에러를 반환한다")
    void throwExceptionWhenDuplicateCard() {
        final List<Card> cards = List.of(DIAMOND_JACK, DIAMOND_JACK);

        assertThatThrownBy(() -> TestDeck.from(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드가 존재합니다.");
    }

    @Test
    @DisplayName("카드를 뽑는다")
    void drawCardTest() {
        //given
        final Deck deck = TestDeck.from(List.of(DIAMOND_JACK));
        final Card expected = Card.of(Shape.DIAMOND, Letter.JACK);

        //when
        final Card actual = deck.draw();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("덱에 카드가 존재하지 않은 상태에서 카드를 뽑을 때, 에러가 발생해야 한다.")
    void throwExceptionWhenNotExistCardInDeck() {
        final Deck deck = TestDeck.from(List.of());

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 카드가 존재하지 않아 드로우를 할 수 없습니다.");
    }
}

class TestDeck extends Deck {

    private TestDeck(final List<Card> cards) {
        super(cards);
    }

    public static TestDeck from(final List<Card> cards) {
        return new TestDeck(cards);
    }
}
