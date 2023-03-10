package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.domain.MockDeckGenerator;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DeckTest {

    @Test
    @DisplayName("카드를 뽑을 때 첫 번째 카드를 뽑아서 반환한다.")
    void drawCard_success() {
        // given
        Deck deck = new MockDeckGenerator(List.of(
                Card.of(Suit.DIAMOND, Rank.THREE),
                Card.of(Suit.CLOVER, Rank.ACE)
        )).generate();

        // when
        Card card = deck.drawCard();

        // then
        assertThat(card)
                .isEqualTo(Card.of(Suit.DIAMOND, Rank.THREE));
    }

    @Test
    @DisplayName("카드를 뽑을 때 카드가 없으면 예외가 발생해야 한다.")
    void drawCard_empty() {
        // given
        Deck deck = new MockDeckGenerator(Collections.emptyList())
                .generate();

        // expect
        assertThatIllegalStateException()
                .isThrownBy(deck::drawCard)
                .withMessage("[ERROR] 남은 카드가 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("원하는 갯수의 카드를 뽑을 수 있어야 한다.")
    void drawCards_withCount(int count) {
        // given
        Deck deck = new MockDeckGenerator(Card.of(Suit.DIAMOND, Rank.KING), 52)
                .generate();

        // when
        List<Card> cards = deck.drawCards(count);

        // then
        assertThat(cards)
                .hasSize(count);
    }
    
    @Test
    @DisplayName("원하는 갯수의 카드를 뽑을 때 카드가 부족하면 예외가 발생한다.")
    void drawCards_insufficientCards() {
        // given
        Deck deck = new MockDeckGenerator(Card.of(Suit.DIAMOND, Rank.KING), 3)
                .generate();

        // expect
        assertThatIllegalStateException().isThrownBy(() -> {
            deck.drawCards(4);
        }).withMessage("[ERROR] 카드가 부족합니다.");
    }
}
