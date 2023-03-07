package blackjack.domain;

import blackjack.domain.card.Pattern;
import blackjack.domain.card.StandardCard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.strategy.RandomCardPicker;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameTest {

    private final BlackJackGame blackJackGame = new BlackJackGame();

    @Test
    void initHit() {
        Players players = new Players("a,b,c");
        Dealer dealer = new Dealer();

        blackJackGame.initHit(players, dealer, new RandomCardPicker());
        players.getPlayers().stream().allMatch(player -> player.getCardDeck().getCardCount() == 2);
        assertThat(dealer.getCardDeck().getCardCount()).isEqualTo(2);
    }

    @Test
    void hit() {
        Player player = new Player("a");

        blackJackGame.hit(player, new RandomCardPicker());

        assertThat(player.getCardDeck().getCardCount()).isEqualTo(1);
    }

    @Test
    void calculateScore() {
        Player player = new Player("a");
        assertThat(blackJackGame.calculateScore(player)).isEqualTo(0);
        player.hit(new StandardCard(Pattern.CLUB, "10"));

        int score = blackJackGame.calculateScore(player);

        assertThat(score).isEqualTo(10);
    }

    @Test
    void isBurst() {
        assertThat(blackJackGame.isBurst(22)).isTrue();
    }
}