package domain.player;

import domain.CardDeck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBlackJackPlayersTest {
    @Test
    void 첫_페이즈_드로우_테스트() {
        List<BlackJackPlayer> blackJackPlayers = new ArrayList<>();

        blackJackPlayers.add(new User("pobi"));
        blackJackPlayers.add(new User("jason"));
        blackJackPlayers.add(new Dealer());

        AllBlackJackPlayers allBlackJackPlayers = new AllBlackJackPlayers(blackJackPlayers);
        allBlackJackPlayers.drawFirstPhase(new CardDeck());

        for (BlackJackPlayer blackJackPlayer : allBlackJackPlayers.getBlackJackPlayers()) {
            int cardAmount = blackJackPlayer.getCardsOnHand().getCardAmount();
            assertThat(cardAmount).isEqualTo(2);
        }
    }
}
