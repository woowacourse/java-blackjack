package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardsTest {

    Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
        cards.add(Card.of(Symbol.CLOVER, Denomination.EIGHT));
    }

    @Test
    void 중복된_카드를_받는_경우_예외가_발생한다() {
        assertThatThrownBy(() -> cards.add(Card.of(Symbol.CLOVER, Denomination.EIGHT)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 받을 수 없습니다.");
    }

    @Test
    void ACE가_없는_경우_점수를_계산한다() {
        cards.add(Card.of(Symbol.CLOVER, Denomination.JACK));

        assertThat(cards.calculateScore()).isEqualTo(new Score(18));
    }

    @Test
    void ACE가_1로_계산되는_경우_점수를_계산한다() {
        cards.add(Card.of(Symbol.CLOVER, Denomination.ACE));
        cards.add(Card.of(Symbol.CLOVER, Denomination.JACK));

        assertThat(cards.calculateScore()).isEqualTo(new Score(19));
    }

    @Test
    void ACE가_11로_계산되는_경우_점수를_계산한다() {
        cards.add(Card.of(Symbol.CLOVER, Denomination.ACE));

        assertThat(cards.calculateScore()).isEqualTo(new Score(19));
    }

    @Test
    void ACE가_여러장인_경우_점수를_계산한다() {
        cards.add(Card.of(Symbol.CLOVER, Denomination.ACE));
        cards.add(Card.of(Symbol.HEART, Denomination.ACE));
        cards.add(Card.of(Symbol.DIAMOND, Denomination.ACE));

        assertThat(cards.calculateScore()).isEqualTo(new Score(21));
    }
}