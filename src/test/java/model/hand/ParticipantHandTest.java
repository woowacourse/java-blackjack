package model.hand;

import static org.junit.jupiter.api.Assertions.*;

import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantHandTest {
    private ParticipantHand participantHand;

    @BeforeEach
    void setUp() {
        participantHand = new HardHand();
    }

    @Test
    @DisplayName("받은 카드 합이 21 이하면 burst가 아니라고 반환한다")
    void 받은카드_isBurst_아닐때_계산() {
        //given
        participantHand.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBurst();

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
        boolean burst = participantHand.checkBurst();

        //then
        Assertions.assertTrue(burst);
    }

    @Test
    @DisplayName("받은 카드 중에 ace가 있으면, ace가 1일때 합이 21 이하일때 burst인지 확인한다.")
    void 받은카드가_ace포함일때_isBurst_계산() {
        //given
        participantHand = new SoftHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.HEART));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));

        //when
        boolean burst = participantHand.checkBurst();

        //then
        assertFalse(burst);
    }

    @Test
    @DisplayName("받은 카드 중에 ace가 있으면, ace가 1일때 합이 21 초과일때 burst인지 확인한다.")
    void 받은카드가_ace포함일때_계산_합_21_초과일때_isBurst_계산() {
        //given
        participantHand = new SoftHand();
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
    @DisplayName("특정 숫자 이하인 경우 true 반환")
    void 특정_숫자_이하_값인_경우_true_반환() {
        //given
        participantHand.add(new Card(CardRank.SIX, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.KING, CardSuit.DIAMOND));

        //when, then
        assertTrue(participantHand.checkScoreBelow(16));
    }

    @Test
    @DisplayName("카드를 2장 뽑았을 때 합이 21이면 블랙잭이다.")
    void 카드_두장_뽑고_합이_21이면_블랙잭() {
        //given
        participantHand = new SoftHand();
        participantHand.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        participantHand.add(new Card(CardRank.JACK, CardSuit.DIAMOND));

        //when, then
        assertTrue(participantHand.checkBlackJack());
    }
}
