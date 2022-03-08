package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantTest {


    @Test
    @DisplayName("점수를 올바르게 계산하는지 확인한다")
    void testCalculateScore() {
        List<Card> cards = List.of(
                new Card(Symbol.CLOVER, Denomination.EIGHT),
                new Card(Symbol.DIAMOND, Denomination.KING)
        );

        Participant participant = new Participant(cards);
        participant.addCard(new Card(Symbol.HEART, Denomination.TWO));
        participant.endTurn();

        assertThat(participant.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("더 유리한 에이스 값 선택하여 결과를 계산하는지 확인한다.")
    void calculateScoreContainsAce() {
        List<Card> cards = List.of(
                new Card(Symbol.CLOVER, Denomination.EIGHT),
                new Card(Symbol.HEART, Denomination.TWO)
        );

        Participant participant = new Participant(cards);
        participant.addCard(new Card(Symbol.SPADE, Denomination.ACE));
        participant.endTurn();

        assertThat(participant.getScore()).isEqualTo(21);
    }

}
