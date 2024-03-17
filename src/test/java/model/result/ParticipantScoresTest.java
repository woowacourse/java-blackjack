package model.result;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import model.card.Card;
import model.participant.Dealer;
import model.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantScoresTest {

    @DisplayName("딜러와 참가자들의 점수 결과 생성")
    @Test
    void createParticipantScores() {
        Players players = Players.from(List.of("조조", "릴리"));
        Dealer dealer = new Dealer(List.of(new Card(ACE, HEART), new Card(JACK, CLOVER)));
        ParticipantScores participantScores = ParticipantScores.of(dealer, players);

        assertAll(
            () -> assertThat(participantScores.getDealerScore().getScore()).isEqualTo(21),
            () -> participantScores.getPlayerScores()
                .forEach(participantScore -> assertThat(participantScore.getScore()).isZero())
        );
    }
}
