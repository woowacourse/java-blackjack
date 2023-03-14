package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardsTest {

    @Test
    void calculateScore_메서드_테스트() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Suit.HEART, Denomination.FIVE);
        Card card2 = new Card(Suit.HEART, Denomination.QUEEN);

        //when
        cards.addCard(card1);
        cards.addCard(card2);

        //then
        int expectedScore = 15;
        assertThat(cards.calculateScore().getValue()).isEqualTo(expectedScore);
    }

    @Test
    void A가_11점으로_계산되는_케이스() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Suit.HEART, Denomination.ACE);
        Card card2 = new Card(Suit.HEART, Denomination.FIVE);

        //when
        cards.addCard(card1);
        cards.addCard(card2);

        //then
        int expectedScore = 16;
        assertThat(cards.calculateScore().getValue()).isEqualTo(expectedScore);
    }

    @Test
    void A가_1점으로_계산되는_케이스() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Suit.HEART, Denomination.ACE);
        Card card2 = new Card(Suit.HEART, Denomination.QUEEN);
        Card card3 = new Card(Suit.HEART, Denomination.FIVE);

        //when
        cards.addCard(card1);
        cards.addCard(card2);
        cards.addCard(card3);

        //then
        int expectedScore = 16;
        assertThat(cards.calculateScore().getValue()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 두장으로 21점일때 blackjack이다")
    void blackJackTest() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Suit.HEART, Denomination.ACE);
        Card card2 = new Card(Suit.HEART, Denomination.JACK);
        //when
        cards.addCard(card1);
        cards.addCard(card2);
        //then
        assertThat(cards.isBlackJack()).isTrue();
    }
}
