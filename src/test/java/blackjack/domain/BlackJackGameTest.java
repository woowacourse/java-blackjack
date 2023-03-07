package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.strategy.RandomCardPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {

    private final BlackJackGame blackJackGame = new BlackJackGame();

    @Test
    void initHit() {
        Players players = new Players("a,b,c");
        Dealer dealer = new Dealer();

        blackJackGame.initHit(players, dealer, new RandomCardPicker());
        players.getPlayers().stream().allMatch(player -> player.getCardDeck().getCardCount() == 2);
        Assertions.assertThat(dealer.getCardDeck().getCardCount()).isEqualTo(2);
    }

    @Test
    void hit() {
        Player player = new Player("a");

        blackJackGame.hit(player, new RandomCardPicker());

        Assertions.assertThat(player.getCardDeck().getCardCount()).isEqualTo(1);
    }
}