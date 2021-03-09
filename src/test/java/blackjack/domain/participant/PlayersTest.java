package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.game.WinnerFlag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersTest {
    private Players players;

    @BeforeEach
    void setUp() {
        List<String> input = Arrays.asList("pobi", "jason");
        players = new Players(input, new Dealer());
    }

    @Test
    @DisplayName("카드 한장씩 분배 확인")
    void playerReceiveCards() {
        players.giveCards(new Deck());
        assertEquals(2, (int) players.toList().stream()
                .filter(player -> player.getCards().size() == 1)
                .count());
    }

    @Test
    @DisplayName("우승자 계산 확인")
    void calculateResult() {
        List<String> input = Arrays.asList("pobi", "jason", "cu");
        Dealer dealer = new Dealer();
        Players players = new Players(input, dealer);
        for(Player player : players.toList()) {
            player.calculateResult(dealer);
        }
        assertEquals(3, players.calculateTotalWinnings(dealer).get(WinnerFlag.DRAW));
    }
}
