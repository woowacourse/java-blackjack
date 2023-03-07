package domain;

import domain.user.Dealer;
import domain.user.Participant;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    
    @Test
    @DisplayName("참가자에게 카드를 지급한다.")
    void dealTest() {
        Game game = new Game("echo");
        Participant currentParticipant = game.getCurrentParticipant();
        List<Card> readyCards = currentParticipant.getReadyCards();
        Assertions.assertThat(readyCards).containsExactly(new Card(CardNumber.ACE, CardShape.SPADE));
    }
    
    @Test
    @DisplayName("게임이 준비완료된 상태를 반환한다.")
    void readyResultTest() {
        Game game = new Game("echo");
        game.ready();
        List<Participant> readyResults = game.getAllParticipant();
        Participant dealer = readyResults.get(0);
        Participant echo = readyResults.get(1);
        Assertions.assertThat(readyResults).containsExactly(new Dealer(), new Participant("echo"));
        Assertions.assertThat(dealer.getReadyCards()).hasSize(1);
        Assertions.assertThat(echo.getReadyCards()).hasSize(2);
    }
}
