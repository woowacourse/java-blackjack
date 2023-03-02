package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("참가자에게 카드를 지급한다.")
    void dealTest() {
        Dealer dealer = new Dealer("echo");
        Game game = new Game(new Participants(List.of(dealer)));
        game.deal(dealer);
        List<Card> readyCards = dealer.getReadyCards();
        Assertions.assertThat(readyCards).containsExactly(new Card(CardNumber.ACE, CardShape.SPADE));
    }
}
