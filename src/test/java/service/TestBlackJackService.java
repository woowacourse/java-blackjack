package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import model.BlackJackDeck;
import model.CardNumber;
import model.Dealer;
import constant.MatchStatus;
import model.Player;
import model.PlayerName;
import model.Players;
import model.Shape;
import dto.Card;
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
    @CsvSource({
            "10,10,무",
            "10,11,승",
            "15,20,승",
            "21,10,패",
            "20,15,패",
            "22,1,승",
            "1,22,패",
            "22,24,패"
    })
    public void 블랙잭_참가자_딜러_승_패_판정_정상_작동(Integer dealerScore, Integer playerScore, String result) {
        BlackJackDeck cards = new BlackJackDeck();
        BlackJackService blackJackService = new BlackJackService(cards);

        Player player = new Player(new PlayerName("player1"));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer();

        dealer.addScore(dealerScore);
        player.addScore(playerScore);

        ParticipantWinning gameResult = blackJackService.getGameResult(players, dealer);
        Map<MatchStatus, Integer> dealerWinning = gameResult.dealerWinning();

        assertThat(gameResult.playersWinning().getFirst().matchStatus().getStatus()).isEqualTo(result);

        if (result.equals("승")) {
            assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(0);
            assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(1);
            assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(0);
            return;
        }

        if (result.equals("패")) {
            assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(1);
            assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(0);
            assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(0);
            return;
        }

        assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(0);
        assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(0);
        assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(1);
    }

    @Test
    public void 카드_2장_합계_21_블랙잭() {
        BlackJackService service = new BlackJackService(new BlackJackDeck());

        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.CLOVER, CardNumber.ACE));
        player.draw(new Card(Shape.CLOVER, CardNumber.KING));
        service.updateAceScore(player);

        assertThat(service.isBlackJack(player)).isTrue();
    }

    @Test
    public void 카드_3장_합계_21_블랙잭_아님() {
        BlackJackService service = new BlackJackService(new BlackJackDeck());

        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.CLOVER, CardNumber.SEVEN));
        player.draw(new Card(Shape.HEART, CardNumber.SEVEN));
        player.draw(new Card(Shape.SPADE, CardNumber.SEVEN));

        assertThat(service.isBlackJack(player)).isFalse();
    }

    @Test
    public void 카드_2장_합계_21_미만_블랙잭_아님() {
        BlackJackService service = new BlackJackService(new BlackJackDeck());

        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.CLOVER, CardNumber.NINE));
        player.draw(new Card(Shape.CLOVER, CardNumber.KING));

        assertThat(service.isBlackJack(player)).isFalse();
    }

    @Test
    public void 플레이어_블랙잭_딜러_블랙잭_무() {
        BlackJackService service = new BlackJackService(new BlackJackDeck());

        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.HEART, CardNumber.ACE));
        player.draw(new Card(Shape.HEART, CardNumber.KING));
        service.updateAceScore(player);

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        service.updateAceScore(dealer);

        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);

        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(MatchStatus.DRAW);
        assertThat(result.dealerWinning().get(MatchStatus.DRAW)).isEqualTo(1);
    }

    @Test
    public void 플레이어_블랙잭_딜러_일반_승() {
        BlackJackService service = new BlackJackService(new BlackJackDeck());

        Player player = new Player(new PlayerName("player1"));
        player.draw(new Card(Shape.HEART, CardNumber.ACE));
        player.draw(new Card(Shape.HEART, CardNumber.KING));
        service.updateAceScore(player);

        Dealer dealer = new Dealer();
        dealer.addScore(20);

        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);

        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(MatchStatus.WIN);
        assertThat(result.dealerWinning().get(MatchStatus.LOSE)).isEqualTo(1);
    }

    @Test
    public void 딜러_블랙잭_플레이어_일반_패() {
        BlackJackService service = new BlackJackService(new BlackJackDeck());

        Player player = new Player(new PlayerName("player1"));
        player.addScore(20); // 일반 20점

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        service.updateAceScore(dealer);

        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);

        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(MatchStatus.LOSE);
        assertThat(result.dealerWinning().get(MatchStatus.WIN)).isEqualTo(1);
    }

    @Test
    public void 딜러_블랙잭_플레이어_버스트_패() {
        BlackJackService service = new BlackJackService(new BlackJackDeck());

        Player player = new Player(new PlayerName("player1"));
        player.addScore(22);

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Shape.SPADE, CardNumber.ACE));
        dealer.draw(new Card(Shape.SPADE, CardNumber.KING));
        service.updateAceScore(dealer);

        ParticipantWinning result = service.getGameResult(new Players(List.of(player)), dealer);

        assertThat(result.playersWinning().getFirst().matchStatus()).isEqualTo(MatchStatus.LOSE);
        assertThat(result.dealerWinning().get(MatchStatus.WIN)).isEqualTo(1);
    }


}
