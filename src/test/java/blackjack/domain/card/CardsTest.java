package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    void 카드_추가_테스트() {
        // given
        List<Card> cardsValue = new ArrayList<>();
        Card card1 = Card.of(Denomination.FOUR, Shape.CLUBS);
        cardsValue.add(card1);
        Cards cards = Cards.of(cardsValue);

        // when
        Card insertCard = Card.of(Denomination.FOUR, Shape.CLUBS);
        cards.addCard(insertCard);

        // then
        assertThat(cards.calculateScore()).isEqualTo(8);
    }

    @Test
    void 카드들_점수_계산() {
        // given, when
        Card card1 = Card.of(Denomination.FOUR, Shape.CLUBS);
        Card card2 = Card.of(Denomination.TWO, Shape.CLUBS);
        Card card3 = Card.of(Denomination.KING, Shape.CLUBS);
        Card card4 = Card.of(Denomination.FIVE, Shape.CLUBS);

        Cards cards = Cards.of(Arrays.asList(card1, card2, card3, card4));

        // when
        assertThat(cards.calculateScore()).isEqualTo(21);
    }

    @DisplayName("에이스는 1 또는 11이다.")
    @Test
    void 에이스_점수_계산() {
        // given, when
        Card card1 = Card.of(Denomination.FOUR, Shape.CLUBS);
        Card card2 = Card.of(Denomination.ACE, Shape.CLUBS);
        Card card3 = Card.of(Denomination.KING, Shape.CLUBS);

        Cards cards = Cards.of(Arrays.asList(card1, card2, card3));

        // then
        assertThat(cards.calculateScore()).isEqualTo(15);
        assertThat(cards.calculateScore()).isNotEqualTo(25);
    }

    @DisplayName("에이스가 2개일 때 하나는 1, 하나는 11 테스트")
    @Test
    void 에이스_점수_계산2() {
        // given, when
        Card card1 = Card.of(Denomination.ACE, Shape.CLUBS);
        Card card2 = Card.of(Denomination.ACE, Shape.CLUBS);
        Card card3 = Card.of(Denomination.NINE, Shape.CLUBS);

        Cards cards = Cards.of(Arrays.asList(card1, card2, card3));

        // then
        assertThat(cards.calculateScore()).isEqualTo(21);
        assertThat(cards.calculateScore()).isNotEqualTo(11);
        assertThat(cards.calculateScore()).isNotEqualTo(31);

    }
}
