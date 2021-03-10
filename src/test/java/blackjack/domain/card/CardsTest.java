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
        assertThat(cards.getCards().contains(Card.of(Suit.SPADE, Denomination.ACE)));
    }

    @Test
    @DisplayName("점수 총합 테스트")
    void testTotalScore() {
        cards.add(Card.of(Suit.SPADE, Denomination.FIVE));
        cards.add(Card.of(Suit.SPADE, Denomination.TEN));
        assertThat(cards.totalScore()).isEqualTo(Score.of(15));
    }

    @Test
    @DisplayName("카드가 두장뿐이고, 점수가 21일 때 블랙잭 테스트")
    void testBlackJack() {
        cards.add(Card.of(Suit.SPADE, Denomination.ACE));
        cards.add(Card.of(Suit.SPADE, Denomination.JACK));
        assertThat(cards.isOnlyTwoCard()).isTrue();
        assertThat(cards.totalScore()).isEqualTo(Score.of(21));
        assertThat(cards.isBlackJack()).isTrue();
    }
}
