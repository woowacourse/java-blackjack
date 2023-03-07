package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.strategy.RandomCardPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {

    @Test
    void initHit() {
        Players players = new Players("a,b,c");
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame();

        blackJackGame.initHit(players, dealer, new RandomCardPicker());
        players.getPlayers().stream().allMatch(player -> player.getCardDeck().getCardCount() == 2);
        Assertions.assertThat(dealer.getCardDeck().getCardCount()).isEqualTo(2);
    }
}