package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("카드 리스트를 활용하여 Cards 를 생성한다.")
    @Test
    void 카드들_생성() {
        List<Card> cardValues = List.of(Card.of(Denomination.JACK, Suit.CLOVER),
                Card.of(Denomination.ACE, Suit.DIAMOND));

        assertDoesNotThrow(() -> new Cards(cardValues));
    }

    @DisplayName("카드의 총점을 계산한다.")
    @Test
    void 카드_총점_계산() {
        List<Card> cardValues = List.of(Card.of(Denomination.JACK, Suit.CLOVER),
                Card.of(Denomination.ACE, Suit.DIAMOND));

        Cards cards = new Cards(cardValues);

        assertThat(cards.calculateTotalScore()).isEqualTo(11);
    }

    @DisplayName("카드 List에 카드를 추가한다.")
    @Test
    void 카드_추가() {
        List<Card> cardValues = List.of(Card.of(Denomination.JACK, Suit.CLOVER),
                Card.of(Denomination.ACE, Suit.DIAMOND));

        Cards cards = new Cards(cardValues);

        cards.combine(Card.of(Denomination.KING, Suit.DIAMOND));

        assertThat(cards.getValue().size()).isEqualTo(3);
    }
}
