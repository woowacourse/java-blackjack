package blackjack.domain.rule;

import blackjack.domain.card.CardFactory;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HandInitializerTest {

    @Test
    void initializeHand() {
        Deck deck = new Deck(CardFactory.generate());
        Dealer dealer = new Dealer();
        Player pobi = new Player("pobi");
        Player jay = new Player("jay");
        Player elly = new Player("elly");
        Players players = new Players(Arrays.asList(pobi, jay, elly));

        HandInitializer.initialize(dealer, players, deck);

        assertThat(dealer.getHand()).size().isEqualTo(2);
        for (Player player : players) {
            assertThat(player.getHand()).size().isEqualTo(2);
        }
    }
}