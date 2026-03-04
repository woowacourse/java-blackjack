package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Emblem;
import domain.card.Grade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HandTest {

    @Test
    void 카드를_받으면_손패의_카드_수가_늘어난다() {
        // given
        Deck deck = new Deck();
        Hand hand = new Hand();
        Card card = deck.drawCard();
        // when
        hand.receiveCard(card);
        // then
        assertThat(hand.getCards().size()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"FIVE, TWO, 7", "ACE, FOUR, 15"})
    void 손패에_있는_카드의_합을_계산한다(Grade card1Value, Grade card2Value, int result) {
        // given
        Hand hand = new Hand();

        hand.receiveCard(new Card(Emblem.CLOVER, card1Value));
        hand.receiveCard(new Card(Emblem.CLOVER, card2Value));
        // when
        int score = hand.calculate();
        // then
        assertThat(score).isEqualTo(result);
    }

    // TODO : 테스트 코드 리팩터링 생각하기.
    @Test
    void ACE가_여러_장_일때_합을_계산한다1() {
        // given
        Hand hand = new Hand();

        hand.receiveCard(new Card(Emblem.CLOVER, Grade.ACE));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.ACE));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.NINE));
        // when
        int score = hand.calculate();
        // then
        assertThat(score).isEqualTo(21);
    }

    @Test
    void ACE가_여러_장_일때_합을_계산한다2() {
        // given
        Hand hand = new Hand();

        hand.receiveCard(new Card(Emblem.CLOVER, Grade.JACK));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.FIVE));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.ACE));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.FOUR));
        // when
        int score = hand.calculate();
        // then
        assertThat(score).isEqualTo(20);
    }
}
