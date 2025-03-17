package model.hand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SoftHandTest {

    @Test
    @DisplayName("ACE를 뺀 나머지의 합이 10 이하일 경우 ACE 한 장을 11로 계산한다")
    void ACE_를_뺸_나머지의_합이_10이하일_경우_ACE_한_장을_11로_계산() {
        //given
        SoftHand participantHand = new SoftHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        //when
        int expect = 21;
        int result = participantHand.calculateFinalScore();

        //then
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("ACE가 2장일때, 뺀 나머지의 합이 10 이하일 경우 ACE 한 장을 11로 계산한다")
    void ACE가_두장일때_ACE를_뺸_나머지의_합이_10이하일_경우_ACE_한_장을_11로_계산() {
        //given
        SoftHand participantHand = new SoftHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.SEVEN, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        //when
        int expect = 21;
        int result = participantHand.calculateFinalScore();

        //then
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("최종 점수는 버스트되지 않는 한 소프트밸류를 최대값으로 만든다")
    void 최종점수는_소프트밸류를_고려한_합() {
        //given
        SoftHand softHand = new SoftHand();
        softHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        softHand.add(new Card(CardRank.THREE, CardSuit.DIAMOND));

        //when
        int score = softHand.calculateFinalScore();

        //then
        assertThat(score).isEqualTo(14);
    }

    @Test
    @DisplayName("최종 점수는 소프트밸류의 값을 고려하여 구한다.")
    void 최종점수는_소프트밸류를_고려한_합_2() {
        //given
        SoftHand softHand = new SoftHand();
        softHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        softHand.add(new Card(CardRank.JACK, CardSuit.DIAMOND));

        //when
        int score = softHand.calculateFinalScore();

        //then
        assertThat(score).isEqualTo(21);
    }

    @Test
    @DisplayName("최종 점수는 소프트밸류의 값을 고려하여 구한다.")
    void 최종점수는_소프트밸류를_고려한_합_3() {
        //given
        SoftHand softHand = new SoftHand();
        softHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        softHand.add(new Card(CardRank.JACK, CardSuit.DIAMOND));
        softHand.add(new Card(CardRank.JACK, CardSuit.DIAMOND));

        //when
        int score = softHand.calculateFinalScore();

        //then
        assertThat(score).isEqualTo(21);
    }
}
