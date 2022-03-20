package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("카드덱 생성 테스트")
    void createValidDeck() {
        assertThat(Deck.create()).isNotNull();
    }

    @Test
    @DisplayName("카드덱에 52장의 카드가 있는지 테스트")
    void checkAllCardsDeck() {
        final Deck deck = new Deck();

        assertThat(deck.getDeck().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드를 정상적으로 나눠주는지 테스트")
    void distributeCard() {
        final Deck deck = new Deck();

        assertThat(deck.distributeCard()).isEqualTo(Card.from(Suit.SPADE, Denomination.ACE));
    }
}
