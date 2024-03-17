package model.result;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import model.card.Card;
import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantScoreTest {

    @DisplayName("참가자 점수 결과 생성")
    @Test
    void createParticipantScore() {
        List<Card> cards = List.of(new Card(ACE, HEART), new Card(JACK, SPADE));
        Player player = Player.of("jojo", cards);
        ParticipantScore playerScore = ParticipantScore.from(player);

        assertAll(
            () -> assertThat(playerScore.getCard().getName()).isEqualTo("jojo"),
            () -> assertThat(playerScore.cards()).hasToString("[A하트, J스페이드]"),
            () -> assertThat(playerScore.getScore()).isEqualTo(21)
        );
    }
}
