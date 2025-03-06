package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantHandTest {
    @Test
    @DisplayName("받은 카드 합이 21 이하면 burst가 아니라고 반환한다")
    void 받은카드_burst_아닐때_계산() {
        //given
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBurst();

        //then
        Assertions.assertFalse(burst);
    }

    @Test
    @DisplayName("받은 카드 합이 21을 초과하면 burst라고 반환한다")
    void 받은카드_burst일때_계산() {
        //given
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBurst();

        //then
        Assertions.assertTrue(burst);
    }

    @Test
    @DisplayName("받은 카드 중에 ace가 있으면, ace가 1일때 합이 21 이하일때 burst인지 확인한다.")
    void 받은카드가_ace포함일때_burst_계산() {
        //given
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.HEART));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));
        int a = participantHand.calculateFinalScore();
        int expect = 21;
        assertEquals(expect, a);
        //when
        boolean burst = participantHand.checkBurst();

        //then
        Assertions.assertFalse(burst);
    }

    @Test
    @DisplayName("받은 카드 중에 ace가 있으면, ace가 1일때 합이 21 초과일때 burst인지 확인한다.")
    void 받은카드가_ace포함일때_계산_합_21_초과일때_burst_계산() {
        //given
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.HEART));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBurst();

        //then
        Assertions.assertTrue(burst);
    }

    @Test
    @DisplayName("ACE가 한장일때 ACE를 뺸 나머지의 합이 10 이하일경우 true")
    void ACE를_제외한_나머지의_합이_10_이하이다(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        boolean result = participantHand.isAceElevenPossible();

        assertTrue(result);
    }

    @Test
    @DisplayName("ACE가 한장일 때 ACE를 뺸 나머지의 합이 11 이샹일경우 false")
    void ACE를_제외한_나머지의_합이_11_이상이다(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.NINE, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        boolean result = participantHand.isAceElevenPossible();

        assertFalse(result);
    }

    @Test
    @DisplayName("ACE를 뺀 나머지의 합이 10 이하일 경우 ACE 한 장을 11로 계산한다")
    void ACE_를_뺸_나머지의_합이_10이하일_경우_ACE_한_장을_11로_계산(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        int expect = 21;
        int result = participantHand.calculateFinalScore();

        assertEquals(expect, result);
    }
    @Test
    @DisplayName("ACE_한_장을_ACE의_최대점수인_11점으로 바뀔 수 있는지 확인한다")
    void ACE_한_장을_ACE의_최대점수인_11점으로_바뀔_수_있는지_확인한다(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.SIX, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        boolean result = participantHand.isAceElevenPossible();

        assertTrue(result);
    }

    @Test
    @DisplayName("ACE_한_장을_ACE의_최대점수인_11점으로 바뀔 수 없는 경우 확인")
    void ACE_한_장을_ACE의_최대점수인_11점으로_바뀔_수_없는_경우_확인(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.SEVEN, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        boolean result = participantHand.isAceElevenPossible();

        assertFalse(result);
    }
    /**
     * 나머지의 합이 10 이하다
     *  = a의 값이 11 이상일 수 있다.
     */
    @Test
    @DisplayName("ACE가 2장일때, 뺀 나머지의 합이 10 이하일 경우 ACE 한 장을 11로 계산한다")
    void ACE가_두장일때_ACE를_뺸_나머지의_합이_10이하일_경우_ACE_한_장을_11로_계산(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.SEVEN, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        int expect = 21;
        int result = participantHand.calculateFinalScore();

        assertEquals(expect, result);
    }

    @Test
    @DisplayName("ACE가 없는 경우 최종결과 계산")
    void ACE가_없는_경우_최종결과_계산(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        int expect = 10;
        int result = participantHand.calculateFinalScore();

        assertEquals(expect, result);
    }

}