package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {
    @Test
    @DisplayName("결과 출력 테스트")
    void scoreTest() {
        Players players = new Players("pobi,jason", new Dealer());
        players.giveCards(new Deck());
        GameResult.getPlayersCardsAndResult(players);
    }
}
