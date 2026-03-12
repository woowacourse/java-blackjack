package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.constant.Result;
import domain.dto.GameResultDto;
import domain.game.GameResultJudge;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultJudgeTest {

    @Test
    void 플레이어가_bust면_BUST와_음수_수익을_가진다() {
        Dealer dealer = new Dealer();
        Players players = new Players();
        Player player = new Player("pobi", 1000);

        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEART)); // 17

        player.receiveCard(new Card(Rank.KING, Suit.CLUB));
        player.receiveCard(new Card(Rank.QUEEN, Suit.DIAMOND));
        player.receiveCard(new Card(Rank.TWO, Suit.HEART)); // 22 bust

        players.add(player);

        List<GameResultDto> results = GameResultJudge.judge(dealer, players);

        GameResultDto dealerResult = results.get(0);
        GameResultDto playerResult = results.get(1);

        assertThat(playerResult.getPlayerName()).isEqualTo("pobi");
        assertThat(playerResult.getResult()).isEqualTo(Result.BUST);
        assertThat(playerResult.getProceeds()).isEqualTo(-1000);

        assertThat(dealerResult.getPlayerName()).isEqualTo("딜러");
        assertThat(dealerResult.getProceeds()).isEqualTo(1000);
    }

    @Test
    void 플레이어_점수가_더_높으면_WIN이다() {
        Dealer dealer = new Dealer();
        Players players = new Players();
        Player player = new Player("pobi", 1000);

        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEART)); // 17

        player.receiveCard(new Card(Rank.TEN, Suit.CLUB));
        player.receiveCard(new Card(Rank.EIGHT, Suit.DIAMOND)); // 18

        players.add(player);

        List<GameResultDto> results = GameResultJudge.judge(dealer, players);
        GameResultDto playerResult = results.get(1);

        assertThat(playerResult.getResult()).isEqualTo(Result.WIN);
        assertThat(playerResult.getProceeds()).isEqualTo(1000);
    }

    @Test
    void 플레이어_점수가_더_낮으면_LOSE다() {
        Dealer dealer = new Dealer();
        Players players = new Players();
        Player player = new Player("pobi", 1000);

        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.NINE, Suit.HEART)); // 19

        player.receiveCard(new Card(Rank.TEN, Suit.CLUB));
        player.receiveCard(new Card(Rank.EIGHT, Suit.DIAMOND)); // 18

        players.add(player);

        List<GameResultDto> results = GameResultJudge.judge(dealer, players);
        GameResultDto playerResult = results.get(1);

        assertThat(playerResult.getResult()).isEqualTo(Result.LOSE);
        assertThat(playerResult.getProceeds()).isEqualTo(-1000);
    }

    @Test
    void 플레이어와_딜러의_점수가_같으면_DRAW다() {
        Dealer dealer = new Dealer();
        Players players = new Players();
        Player player = new Player("pobi", 1000);

        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.EIGHT, Suit.HEART)); // 18

        player.receiveCard(new Card(Rank.NINE, Suit.CLUB));
        player.receiveCard(new Card(Rank.NINE, Suit.DIAMOND)); // 18

        players.add(player);

        List<GameResultDto> results = GameResultJudge.judge(dealer, players);
        GameResultDto playerResult = results.get(1);

        assertThat(playerResult.getResult()).isEqualTo(Result.DRAW);
        assertThat(playerResult.getProceeds()).isEqualTo(0);
    }

    @Test
    void naturalBlackJack인_플레이어는_BLACKJACK과_블랙잭_배당을_가진다() {
        Dealer dealer = new Dealer();
        Players players = new Players();
        Player player = new Player("pobi", 10000);

        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.NINE, Suit.HEART)); // 19

        player.receiveCard(new Card(Rank.ACE, Suit.CLUB));
        player.receiveCard(new Card(Rank.KING, Suit.DIAMOND)); // natural blackjack
        player.markNaturalBlackJack();

        players.add(player);

        List<GameResultDto> results = GameResultJudge.judge(dealer, players);
        GameResultDto playerResult = results.get(1);

        assertThat(playerResult.getResult()).isEqualTo(Result.BLACKJACK);
        assertThat(playerResult.getProceeds()).isEqualTo(15000);
    }

    @Test
    void 딜러의_총수익은_플레이어_수익의_합에_음수를_취한_값이다() {
        Dealer dealer = new Dealer();
        Players players = new Players();

        Player winPlayer = new Player("pobi", 1000);
        Player losePlayer = new Player("jason", 2000);

        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.EIGHT, Suit.HEART)); // 18

        winPlayer.receiveCard(new Card(Rank.TEN, Suit.CLUB));
        winPlayer.receiveCard(new Card(Rank.NINE, Suit.DIAMOND)); // 19 -> WIN

        losePlayer.receiveCard(new Card(Rank.TEN, Suit.HEART));
        losePlayer.receiveCard(new Card(Rank.SEVEN, Suit.CLUB)); // 17 -> LOSE

        players.add(winPlayer);
        players.add(losePlayer);

        List<GameResultDto> results = GameResultJudge.judge(dealer, players);

        GameResultDto dealerResult = results.get(0);
        GameResultDto firstPlayerResult = results.get(1);
        GameResultDto secondPlayerResult = results.get(2);

        assertThat(firstPlayerResult.getProceeds()).isEqualTo(1000);
        assertThat(secondPlayerResult.getProceeds()).isEqualTo(-2000);
        assertThat(dealerResult.getProceeds()).isEqualTo(1000);
    }
}