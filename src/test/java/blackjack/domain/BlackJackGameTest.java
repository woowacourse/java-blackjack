package blackjack.domain;

import blackjack.domain.card.Pattern;
import blackjack.domain.card.StandardCard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.strategy.RandomCardPicker;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    void isValidScore() {
        assertThat(blackJackGame.isValidScore(20)).isTrue();
    }

    @Test
    void isContinueToHit() {
        assertThat(blackJackGame.isContinueToHit(16)).isTrue();
    }

    @Test
    void getPlayersResult() {
        Players players = new Players("a,b");
        Dealer dealer = new Dealer();
        dealer.hit(new StandardCard(Pattern.CLUB, "9"));
        players.getPlayers().forEach(player -> player.hit(new StandardCard(Pattern.SPADE, "10")));

        List<Result> result = blackJackGame.getPlayersResult(dealer, players);

        assertThat(result).isEqualTo(List.of(Result.WIN, Result.WIN));
    }

    @Test
    void getDealerResult() {
        List<Result> results = List.of(Result.WIN, Result.WIN, Result.LOSE, Result.DRAW);

        Map<String, Long> dealerResult = blackJackGame.getDealerResult(results);

        assertThat(dealerResult.get(Result.WIN.getResult())).isEqualTo(2);
        assertThat(dealerResult.get(Result.LOSE.getResult())).isEqualTo(1);
        assertThat(dealerResult.get(Result.DRAW.getResult())).isEqualTo(1);
    }
}