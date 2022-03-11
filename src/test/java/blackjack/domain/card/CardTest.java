package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("스페이드 에이스를 생성한다.")
    void createSpadeAce() {
        Card card = Card.valueOf(Suit.SPADE, Number.ACE);

        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("같은 모양과 숫자의 카드는 동일하다.")
    void equalsCardsHashcode() {
        Card card = Card.valueOf(Suit.SPADE, Number.ACE);

        assertThat(card.hashCode() == Card.valueOf(Suit.SPADE, Number.ACE).hashCode()).isTrue();
    }

    @Test
    @DisplayName("같은 모양과 숫자의 카드는 동등하다.")
    void equalsCard() {
        Card card = Card.valueOf(Suit.SPADE, Number.ACE);

        assertThat(card.equals(Card.valueOf(Suit.SPADE, Number.ACE))).isTrue();
    }

    @Test
    @DisplayName("52장의 서로 다른 카드를 생성한다.")
    void createCards() {
        List<Card> cards = Card.createDeck();

        assertThat(Set.copyOf(cards)).hasSize(52);
    }
}
