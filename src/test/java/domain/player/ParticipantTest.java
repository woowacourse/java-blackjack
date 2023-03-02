package domain.player;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant("test");
    }

    @Test
    @DisplayName("참가자가 딜러를 이긴 후, 승리 결과를 가진다.")
    void givenParticipantWinningFromDealer_thenHavingWinning() {
        participant.addCard(new Card(Shape.HEART, Number.ACE));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Shape.DIAMOND, Number.KING));

        participant.battle(dealer);

        assertThat(participant.getGameResult())
                .containsExactly(1, 0, 0);
    }

    @Test
    @DisplayName("참가자가 딜러에게 진 후, 패배 결과를 가진다.")
    void givenParticipantLosingFromDealer_thenHavingLosing() {
        participant.addCard(new Card(Shape.HEART, Number.KING));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Shape.DIAMOND, Number.ACE));

        participant.battle(dealer);

        assertThat(participant.getGameResult())
                .containsExactly(0, 1, 0);
    }

    @Test
    @DisplayName("참가자가 딜러과 비긴 후, 비긴 결과를 가진다.")
    void givenParticipantDrawingWithDealer_thenHavingDrawing() {
        participant.addCard(new Card(Shape.HEART, Number.ACE));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Shape.DIAMOND, Number.ACE));

        participant.battle(dealer);

        assertThat(participant.getGameResult())
                .containsExactly(0, 0, 1);
    }
}