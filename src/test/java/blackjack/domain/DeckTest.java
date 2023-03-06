package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class DeckTest {

    @Test
    @DisplayName("카드를 뽑을 때 첫 번째 카드를 뽑아서 반환한다.")
    void drawCard_success() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.DIAMOND, Rank.THREE));
        cards.add(new Card(Suit.HEART, Rank.FOUR));
        cards.add(new Card(Suit.SPADE, Rank.SIX));

        // when
        Deck deck = new Deck(cards);
        Card card = deck.drawCard();

        // then
        assertThat(card)
                .isEqualTo(new Card(Suit.DIAMOND, Rank.THREE));
    }

    @Test
    @DisplayName("카드를 뽑을 때 카드가 없으면 예외가 발생해야 한다.")
    void drawCard_empty() {
        // given
        Deck deck = new Deck(new ArrayList<>());

        // expect
        assertThatIllegalStateException()
                .isThrownBy(deck::drawCard)
                .withMessage("[ERROR] 남은 카드가 없습니다.");
    }
}