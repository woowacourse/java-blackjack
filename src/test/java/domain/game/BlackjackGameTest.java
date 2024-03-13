package domain.game;

import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomCardGenerator;

class BlackjackGameTest {

    @Test
    @DisplayName("성공: 모든 참가자에게 시작 카드를 분배")
    void distributeStartingCards() {
        Participants participants = Participants.from(List.of("name1"));
        BlackjackGame game = new BlackjackGame(participants, new RandomCardGenerator());
        Player player = game.getPlayers()
            .get(0);
        Dealer dealer = game.getDealer();

        game.distributeStartingCards();

        Assertions.assertThat(player.getCards()).hasSize(2);
        Assertions.assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("성공: 참가자에게 카드 한장 주기")
    void giveOneCard() {
        Participants participants = Participants.from(List.of("name"));
        BlackjackGame game = new BlackjackGame(participants, new RandomCardGenerator());
        Player player = game.getPlayers().get(0);
        game.giveOneCard(player);

        Assertions.assertThat(player.getCards()).hasSize(1);
    }
}
