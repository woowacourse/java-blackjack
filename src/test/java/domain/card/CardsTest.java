package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardsTest {

    @Test
    @DisplayName("Card를 입력받으면 저장한다.")
    void givenCard_thenAdd() {
        //given
        final Cards cards = Cards.create();
        final Card card = Card.of(Suit.SPADE, Rank.JACK);

        //when
        cards.takeCard(card);

        //then
        assertThat(cards.getCards()).isEqualTo(List.of(card));
    }

    @Nested
    @DisplayName("뽑은 카드 중에 ACE가")
    class HasAceTest {

        @Test
        @DisplayName("없으면 false를 반환한다.")
        void givenNoAce_thenReturnFalse() {
            //given
            final Cards cards = Cards.create();
            final List<Rank> ranks = List.of(Rank.FIVE, Rank.TEN, Rank.EIGHT);
            final Deck deck = Deck.from(TestCardGenerator.from(ranks));

            //when
            ranks.forEach(i->cards.takeCard(deck.dealCard()));

            //then
            assertThat(cards.hasAce()).isFalse();
        }

        @Test
        @DisplayName("있으면 true를 반환한다.")
        void givenAce_thenReturnTrue() {
            //given
            final Cards cards = Cards.create();
            final List<Rank> ranks = List.of(Rank.FIVE, Rank.ACE, Rank.EIGHT);
            final Deck deck = Deck.from(TestCardGenerator.from(ranks));

            //when
            ranks.forEach(i -> cards.takeCard(deck.dealCard()));

            //then
            assertThat(cards.hasAce()).isTrue();
        }
    }
}
