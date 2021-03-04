package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Hand;
import blackjack.domain.user.HandStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @DisplayName("카드 추가 메소드 테스트")
    @Test
    public void addCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Suit.CLUBS));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);

        hand.addCard(new Card(Denomination.ACE, Suit.HEARTS));
        assertThat(hand.getScore()).isEqualTo(19);
        assertThat(hand.getStatus()).isEqualTo(HandStatus.HIT);
    }

    @DisplayName("Hand 에 들고 있는 카드 점수 계산 : ACE 없는 경우")
    @Test
    public void calculateHandScore() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Hand hand = new Hand(cards);

        assertThat(hand.calculateHandScore()).isEqualTo(13);
    }

    @DisplayName("Hand 에 들고 있는 카드 점수 계산 : ACE 있는 경우")
    @Test
    public void countAce() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Suit.CLUBS));
        cards.add(new Card(Denomination.ACE, Suit.DIAMONDS));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMONDS));
        cards.add(new Card(Denomination.TWO, Suit.HEARTS));
        Hand hand = new Hand(cards);

        assertThat(hand.calculateHandScore()).isEqualTo(21);
    }

    @DisplayName("카드 추가 메소드 테스트 : 추가 후 BUST")
    @Test
    public void addCard_Bust() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.EIGHT, Suit.CLUBS));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);

        hand.addCard(new Card(Denomination.QUEEN, Suit.HEARTS));
        assertThat(hand.getStatus()).isEqualTo(HandStatus.BUST);
    }
}