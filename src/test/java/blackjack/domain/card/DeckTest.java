package blackjack.domain.card;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Suit.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.generator.RandomCardsGenerator;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("카드 컬렉션을 가지는 덱을 생성한다.")
    @Test
    void 카드덱_생성() {
        assertDoesNotThrow(() -> new Deck(new RandomCardsGenerator()));
    }

    @DisplayName("카드덱이 비어있을 경우 예외를 던진다.")
    @Test
    void 카드_뽑기_예외() {
        Deck deck = new Deck(() -> Collections.emptyList());

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드가 모두 소진되었습니다.");
    }

    @DisplayName("카드덱에서 카드를 한장 뽑는다.")
    @Test
    void 카드_뽑기_정상() {
        Card card = new Card(ACE, DIAMOND);
        Deck deck = new Deck(() -> List.of(card));

        Card drawCard = deck.draw();

        assertThat(card).isEqualTo(drawCard);
    }
}
