package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    static Strategy dealerDeck = new TempDeck(Arrays.asList(
            new Card(Symbol.EIGHT, Type.DIAMOND),
            new Card(Symbol.TWO, Type.DIAMOND),
            new Card(Symbol.FIVE, Type.DIAMOND)
    ));

    Strategy playerDeck = new TempDeck(Arrays.asList(
            new Card(Symbol.SIX, Type.DIAMOND),
            new Card(Symbol.TWO, Type.DIAMOND),
            new Card(Symbol.FIVE, Type.DIAMOND)
    ));

    @Test
    void compareTest() {
        Dealer dealer = new Dealer(dealerDeck);
        Player player = new Player(playerDeck);

        dealer.compareWithPlayer(player);
        assertThat(dealer.getWin() ==1).isTrue();
        assertThat(player.getResult() == Result.LOSE).isTrue();
    }
}