package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }

    @Test
    @DisplayName("add 테스트")
    void testAdd() {
        cards.add(Card.of(Suit.SPADE, Denomination.ACE));
        assertThat(cards.cards().contains(Card.of(Suit.SPADE, Denomination.ACE)));
    }

    @Test
    @DisplayName("점수 확인 테스트")
    void testGetScore() {
        cards.add(Card.of(Suit.SPADE, Denomination.FIVE));
        cards.add(Card.of(Suit.SPADE, Denomination.TEN));
        assertThat(cards.getScore()).isEqualTo(new Score(15));
    }
}
