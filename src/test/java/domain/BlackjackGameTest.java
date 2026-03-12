package domain;

import static domain.BlackjackGame.INIT_DRAW_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Hand;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("처음 카드를 뽑는 경우 2장(INIT_DRAW_COUNT)을 뽑는다.")
    public void 첫_드로우_성공() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(
                new Participants(List.of(new Participant(new Name("zzaekkii"), new Hand()))));

        // when
        blackjackGame.initDraw();

        // then
        final Participant zakie = blackjackGame.getParticipants().getPlayers().getFirst();
        final Participant dealer = blackjackGame.getParticipants().getDealer();
        assertThat(zakie.getHand()).hasSize(INIT_DRAW_COUNT);
        assertThat(dealer.getHand()).hasSize(INIT_DRAW_COUNT);
    }


}
