package blackjack.domain.card;

import static blackjack.domain.Denomination.*;
import static blackjack.domain.Suit.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("카드 컬렉션을 가지는 덱을 생성한다.")
    @Test
    void 카드덱_생성() {
        assertDoesNotThrow(() -> new Deck(Card.VALUES));
    }

    @DisplayName("카드덱이 비어있을 경우 예외를 던진다.")
    @Test
    void 카드_뽑기_예외() {
        Deck deck = new Deck(new HashSet<>());

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드가 모두 소진되었습니다.");
    }

    @DisplayName("카드덱에서 카드를 한장 뽑는다.")
    @Test
    void 카드_뽑기_정상() {
        Card card = Card.of(ACE, DIAMOND);
        Deck deck = new Deck(Set.of(card));

        Card drawCard = deck.draw();

        assertThat(card).isEqualTo(drawCard);
    }

    @DisplayName("최초에 카드 두장을 뽑는다.")
    @Test
    void 카드_두장_뽑기() {
        Deck deck = new Deck(Card.VALUES);

        List<Card> cards = deck.getInitCards();

        assertThat(cards.size()).isEqualTo(2);
    }
}
