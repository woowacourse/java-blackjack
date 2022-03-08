package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameTest {
    @Test
    @DisplayName("")
    void initDistribution() {
        Gamer dealer = new Gamer();
        List<Gamer> players = Arrays.asList(new Gamer(), new Gamer());
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players);

        blackJackGame.initDistribution();

        assertThat(dealer.getCards().size()).isEqualTo(2);
        assertThat(players)
                .map(gamer -> gamer.getCards().size())
                .containsExactly(2, 2);
    }
}
