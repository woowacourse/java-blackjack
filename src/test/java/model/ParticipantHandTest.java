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

//    @Test
//    @DisplayName("ACE 카드를 받았을 경우에 burst인지 확인한다.")
//    void ACE카드를_받은_경우_burst_계산(){
//
//    }

}