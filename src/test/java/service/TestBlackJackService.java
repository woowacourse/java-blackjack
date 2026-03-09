package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import model.BlackJackDeck;
import model.Dealer;
import model.MatchStatus;
import model.Player;
import dto.PlayerName;
import model.Players;
import dto.ParticipantWinning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestBlackJackService {

    @Test
    public void 카드_뽑기_정상_작동() {
        BlackJackDeck cards = new BlackJackDeck();
        BlackJackService blackJackService = new BlackJackService(cards);

        Player player = new Player(new PlayerName("player1"));

        blackJackService.draw(player);

        assertThat(player.getResult().score()).isGreaterThan(0);
        assertThat(player.getResult().hand().size()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({"21, false", "22, true"})
    public void 버스트_판정_정상_작동(Integer score, boolean result) {
        BlackJackService blackJackService = new BlackJackService(new BlackJackDeck());

        Player player = new Player(new PlayerName("player"));
        player.addScore(score);

        assertThat(blackJackService.isBust(player)).isEqualTo(result);
    }


    @ParameterizedTest
    @CsvSource({"22,24,무","10,10,무","22,1,승","10,11,승","1,22,패","21,10,패"})
    public void 블랙잭_참가자_딜러_승_패_판정_정상_작동(Integer dealerScore, Integer playerScore, String result) {
        BlackJackDeck cards = new BlackJackDeck();
        BlackJackService blackJackService = new BlackJackService(cards);

        Player player = new Player(new PlayerName("player1"));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer();

        dealer.addScore(dealerScore);
        player.addScore(playerScore);

        ParticipantWinning gameResult = blackJackService.getGameResult(players, dealer);
        Map<MatchStatus, Integer> dealerWinning = gameResult.dealerWinning().getDealerWinning();

        assertThat(gameResult.playersWinning().getPlayersWinnings().getFirst().matchStatus().getStatus()).isEqualTo(result);

        if(result.equals("승")) {
            assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(0);
            assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(1);
            assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(0);
            return;
        }

        if(result.equals("패")) {
            assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(1);
            assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(0);
            assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(0);
            return;
        }

        assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(0);
        assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(0);
        assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(1);
    }

}
