package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.service.MockDeckGenerator;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("카드를 뽑을 때 첫 번째 카드를 뽑아서 반환한다.")
    void drawCard_success() {
        // given
        Deck deck = new MockDeckGenerator(List.of(
                new Card(Suit.DIAMOND, Rank.THREE),
                new Card(Suit.CLOVER, Rank.ACE)
        )).generate();

        // when
        Card card = deck.drawCard();

        // then
        assertThat(card)
                .isEqualTo(new Card(Suit.DIAMOND, Rank.THREE));
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
}
