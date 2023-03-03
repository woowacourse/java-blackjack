package domain;

import domain.user.Participant;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("참가자에게 카드를 지급한다.")
    void dealTest() {
        Dealer dealer = new Dealer();
        Game game = new Game(new Participants(List.of(dealer)));
        game.deal(dealer);
        List<Card> readyCards = dealer.getReadyCards();
        Assertions.assertThat(readyCards).containsExactly(new Card(CardNumber.ACE, CardShape.SPADE));
    }

    @Test
    @DisplayName("게임이 준비완료된 상태를 반환한다.")
    void readyResultTest() {
        Participant player = new Participant("echo");
        Game game = new Game(new Participants(List.of(player)));
        game.ready();
        List<Participant> readyResults = game.getReadyResults();
        Participant dealer = readyResults.get(1);
        Assertions.assertThat(readyResults).containsExactly(player, new Dealer());
        Assertions.assertThat(dealer.getReadyCards()).hasSize(1);
        Assertions.assertThat(player.getReadyCards()).hasSize(2);
    }
}
