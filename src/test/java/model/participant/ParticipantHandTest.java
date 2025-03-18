package model.participant;

import static org.junit.jupiter.api.Assertions.*;

import model.card.Card;
import model.card.CardRank;
import model.card.CardSuit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantHandTest {
    private ParticipantHand participantHand;

    @BeforeEach
    void setUp() {
        participantHand = new ParticipantHand();
    }
    @Test
    @DisplayName("받은 카드 합이 21 이하면 burst가 아니라고 반환한다")
    void 받은카드_burst_아닐때_계산() {
        //given
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBust();

        //then
        assertFalse(burst);
    }

    @Test
    @DisplayName("받은 카드 합이 21을 초과하면 burst라고 반환한다")
    void 받은카드_burst일때_계산() {
        //given
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBust();

        //then
        Assertions.assertTrue(burst);
    }

    @Test
    @DisplayName("받은 카드 중에 ace가 있으면, ace가 1일때 합이 21 이하일때 burst인지 확인한다.")
    void 받은카드가_ace포함일때_burst_계산() {
        //given
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.HEART));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBust();

        //then
        assertFalse(burst);
    }

    @Test
    @DisplayName("받은 카드 중에 ace가 있으면, ace가 1일때 합이 21 초과일때 burst인지 확인한다.")
    void 받은카드가_ace포함일때_계산_합_21_초과일때_burst_계산() {
        //given
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.HEART));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBust();

        //then
        Assertions.assertTrue(burst);
    }

    @Test
    @DisplayName("특정 숫자 이하인 경우 true 반환")
    void 특정_숫자_이하_값인_경우_true_반환() {
        //given
        participantHand.add(new Card(CardRank.SIX, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));

        //when, then
        assertTrue(participantHand.checkScoreBelow(16));
    }

    @Test
    @DisplayName("ACE를 뺀 나머지의 합이 10 이하일 경우 ACE 한 장을 11로 계산한다")
    void ACE_를_뺸_나머지의_합이_10이하일_경우_ACE_한_장을_11로_계산() {
        //given
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
    @DisplayName("ACE가 없는 경우 최종결과 계산")
    void ACE가_없는_경우_최종결과_계산() {
        //given
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        //when
        int expect = 10;
        int result = participantHand.calculateFinalScore();

        //then
        assertEquals(expect, result);
    }
}