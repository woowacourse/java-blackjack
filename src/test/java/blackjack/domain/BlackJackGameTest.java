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

        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.initDistribution(dealer, players);

        assertThat(dealer.getCards().size()).isEqualTo(2);
        assertThat(players)
                .map(gamer -> gamer.getCards().size())
                .containsExactly(2, 2);
    }

    @Test
    @DisplayName("")
    void distributeCard() {
        Gamer dealer = new Gamer();
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.distributeCard(dealer);

        assertThat(dealer.getCards().size()).isEqualTo(1);
    }
}
