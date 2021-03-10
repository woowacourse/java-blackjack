package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    @DisplayName("Hand 에 들고 있는 카드 점수 계산 : ACE 없는 경우")
    @Test
    void calculateHandScore() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Hand hand = new Hand(cards, 21);

        assertThat(hand.getScore()).isEqualTo(13);
    }

    @DisplayName("Hand 에 들고 있는 카드 점수 계산 : ACE 있는 경우")
    @Test
    void countAce() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Suit.CLUBS));
        cards.add(new Card(Denomination.ACE, Suit.DIAMONDS));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMONDS));
        cards.add(new Card(Denomination.TWO, Suit.HEARTS));
        Hand hand = new Hand(cards, 21);

        assertThat(hand.getScore()).isEqualTo(21);
    }

    @DisplayName("카드 추가 메소드 테스트")
    @Test
    void addCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Suit.CLUBS));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards, 21);

        hand.addCard(new Card(Denomination.ACE, Suit.HEARTS));
        assertThat(hand.getScore()).isEqualTo(19);
        assertTrue(hand.isHit());
    }

    @DisplayName("카드 추가 메소드 테스트 : 추가 후 BUST")
    @Test
    void addCard_Bust() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.EIGHT, Suit.CLUBS));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards, 21);

        hand.addCard(new Card(Denomination.QUEEN, Suit.HEARTS));
        assertTrue(hand.isBust());
    }

    @DisplayName("플레이어가 Hit 인 경우 테스트 = 21점 이하면 hit")
    @Test
    void isHit_forPlayer() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.TEN, Suit.CLUBS));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards, 21);

        assertThat(hand.getScore()).isEqualTo(17);
        assertTrue(hand.isHit());
    }

    @DisplayName("딜러 Hit 인 경우 테스트 = 16점 이하면 hit")
    @Test
    public void isHit_forDealer() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.EIGHT, Suit.CLUBS));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards, 16);

        assertThat(hand.getScore()).isEqualTo(15);
        assertTrue(hand.isHit());
    }
}