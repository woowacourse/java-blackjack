package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.CardShape;
import blackjack.domain.deck.CardValue;
import blackjack.domain.deck.Cards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }

    @Test
    void 카드_추가_후_이름_목록_반환() {
        // given
        cards.add(new Card(CardValue.ACE, CardShape.DIAMOND));
        cards.add(new Card(CardValue.KING, CardShape.HEART));

        // when & then
        assertThat(cards.getNames())
                .containsExactly("A다이아몬드", "K하트");
    }

    @Test
    void 첫_번째_카드_이름_반환() {
        // given
        cards.add(new Card(CardValue.ACE, CardShape.DIAMOND));
        cards.add(new Card(CardValue.KING, CardShape.HEART));

        // when & then
        assertThat(cards.getFirstName()).isEqualTo("A다이아몬드");
    }

    @Test
    void 카드_합계_계산_일반() {
        // given
        cards.add(new Card(CardValue.FOUR, CardShape.DIAMOND));
        cards.add(new Card(CardValue.SIX, CardShape.HEART));

        // when & then
        assertThat(cards.calculateValue()).isEqualTo(10);
    }

    @Test
    void ACE가_11로_계산되는_경우() {
        // given
        cards.add(new Card(CardValue.ACE, CardShape.DIAMOND));
        cards.add(new Card(CardValue.TEN, CardShape.HEART));

        // when & then
        assertThat(cards.calculateValue()).isEqualTo(21);
    }

    @Test
    void ACE가_1로_계산되는_경우_버스트_방지() {
        // given
        cards.add(new Card(CardValue.ACE, CardShape.DIAMOND));
        cards.add(new Card(CardValue.TEN, CardShape.HEART));
        cards.add(new Card(CardValue.THREE, CardShape.CLOVER));

        // when & then
        assertThat(cards.calculateValue()).isEqualTo(14);
    }

    @Test
    void ACE가_없을_때_합계_그대로_반환() {
        // given
        cards.add(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        cards.add(new Card(CardValue.EIGHT, CardShape.HEART));

        // when & then
        assertThat(cards.calculateValue()).isEqualTo(15);
    }
}
