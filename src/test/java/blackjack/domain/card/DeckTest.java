package blackjack.domain.card;

import static blackjack.fixture.CardFixture.DIAMOND_EIGHT;
import static blackjack.fixture.CardFixture.DIAMOND_ONE;
import static blackjack.fixture.CardFixture.DIAMOND_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드를_카드_셔플러로_섞을_수_있다() {
        //given
        Stack<Card> cards = new Stack<>();
        cards.addAll(List.of(
                DIAMOND_EIGHT,
                DIAMOND_ONE,
                DIAMOND_TEN
        ));
        Deck deck = new Deck(cards);

        //when
        deck.shuffleCards(new FixCardsShuffler());

        //then
        assertThat(deck.getCards()).isEqualTo(cards);
    }

    @Test
    void 남아있는_카드가_없을_때_더_뽑을_수_없다() {
        //given
        Stack<Card> cards = new Stack<>();
        Deck deck = new Deck(cards);

        //then
        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("남아있는 카드가 없습니다.");
    }
}
