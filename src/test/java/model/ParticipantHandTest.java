package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
    @DisplayName("ACE를 뺸 나머지의 합이 10 이하일경우 true")
    void ACE를_제외한_나머지의_합이_10_이하이다(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        boolean result = participantHand.checkScoreExceptAceBelowTen();

        assertTrue(result);
    }

    @Test
    @DisplayName("ACE를 뺸 나머지의 합이 11 이샹일경우 false")
    void ACE를_제외한_나머지의_합이_11_이상이다(){
        ParticipantHand participantHand = new ParticipantHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.NINE, CardSuit.HEART));
        participantHand.add(new Card(CardRank.TWO, CardSuit.DIAMOND));

        boolean result = participantHand.checkScoreExceptAceBelowTen();

        assertFalse(result);
    }


}