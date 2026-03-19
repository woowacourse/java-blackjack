package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.constant.Result;
import dto.DealerResultDto;
import dto.GameResultDto;
import dto.PlayerResultDto;
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
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));

        dealer.addCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.addCard(new Card(Rank.SEVEN, Suit.HEART)); // 17

        player.addCard(new Card(Rank.KING, Suit.CLUB));
        player.addCard(new Card(Rank.QUEEN, Suit.DIAMOND));
        player.addCard(new Card(Rank.TWO, Suit.HEART)); // 22 bust

        Players players = new Players(List.of(player));

        GameResultDto result = GameResultJudge.judge(dealer, players);

        DealerResultDto dealerResult = result.getDealerResult();
        PlayerResultDto playerResult = result.getPlayerResults().getFirst();

        assertThat(playerResult.getPlayerName()).isEqualTo("pobi");
        assertThat(playerResult.getResult()).isEqualTo(Result.BUST);
        assertThat(playerResult.getProceeds()).isEqualTo(-1000);

        assertThat(dealerResult.getDealerName()).isEqualTo("딜러");
        assertThat(dealerResult.getProceeds()).isEqualTo(1000);
    }

    @Test
    void 플레이어_점수가_더_높으면_WIN이다() {
        Dealer dealer = new Dealer();
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));

        dealer.addCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.addCard(new Card(Rank.SEVEN, Suit.HEART)); // 17

        player.addCard(new Card(Rank.TEN, Suit.CLUB));
        player.addCard(new Card(Rank.EIGHT, Suit.DIAMOND)); // 18

        Players players = new Players(List.of(player));

        GameResultDto result = GameResultJudge.judge(dealer, players);
        PlayerResultDto playerResult = result.getPlayerResults().getFirst();

        assertThat(playerResult.getResult()).isEqualTo(Result.WIN);
        assertThat(playerResult.getProceeds()).isEqualTo(1000);
    }

    @Test
    void 플레이어_점수가_더_낮으면_LOSE다() {
        Dealer dealer = new Dealer();
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));

        dealer.addCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.addCard(new Card(Rank.NINE, Suit.HEART)); // 19

        player.addCard(new Card(Rank.TEN, Suit.CLUB));
        player.addCard(new Card(Rank.EIGHT, Suit.DIAMOND)); // 18

        Players players = new Players(List.of(player));

        GameResultDto result = GameResultJudge.judge(dealer, players);
        PlayerResultDto playerResult = result.getPlayerResults().getFirst();

        assertThat(playerResult.getResult()).isEqualTo(Result.LOSE);
        assertThat(playerResult.getProceeds()).isEqualTo(-1000);
    }

    @Test
    void 플레이어와_딜러의_점수가_같으면_DRAW다() {
        Dealer dealer = new Dealer();
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));

        dealer.addCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.addCard(new Card(Rank.EIGHT, Suit.HEART)); // 18

        player.addCard(new Card(Rank.NINE, Suit.CLUB));
        player.addCard(new Card(Rank.NINE, Suit.DIAMOND)); // 18

        Players players = new Players(List.of(player));

        GameResultDto result = GameResultJudge.judge(dealer, players);
        PlayerResultDto playerResult = result.getPlayerResults().getFirst();

        assertThat(playerResult.getResult()).isEqualTo(Result.DRAW);
        assertThat(playerResult.getProceeds()).isEqualTo(0);
    }

    @Test
    void naturalBlackJack인_플레이어는_BLACKJACK과_블랙잭_배당을_가진다() {
        Dealer dealer = new Dealer();
        Player player = new Player(new Name("pobi"), new BettingMoney(10000L));

        dealer.addCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.addCard(new Card(Rank.NINE, Suit.HEART)); // 19

        player.addCard(new Card(Rank.ACE, Suit.CLUB));
        player.addCard(new Card(Rank.KING, Suit.DIAMOND)); // natural blackjack

        Players players = new Players(List.of(player));

        GameResultDto result = GameResultJudge.judge(dealer, players);
        PlayerResultDto playerResult = result.getPlayerResults().getFirst();

        assertThat(playerResult.getResult()).isEqualTo(Result.BLACKJACK);
        assertThat(playerResult.getProceeds()).isEqualTo(15000);
    }

    @Test
    void 딜러의_총수익은_플레이어_수익의_합에_음수를_취한_값이다() {
        Dealer dealer = new Dealer();

        Player winPlayer = new Player(new Name("pobi"), new BettingMoney(1000L));
        Player losePlayer = new Player(new Name("jason"), new BettingMoney(2000L));

        dealer.addCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.addCard(new Card(Rank.EIGHT, Suit.HEART)); // 18

        winPlayer.addCard(new Card(Rank.TEN, Suit.CLUB));
        winPlayer.addCard(new Card(Rank.NINE, Suit.DIAMOND)); // 19 -> WIN

        losePlayer.addCard(new Card(Rank.TEN, Suit.HEART));
        losePlayer.addCard(new Card(Rank.SEVEN, Suit.CLUB)); // 17 -> LOSE

        Players players = new Players(List.of(winPlayer, losePlayer));

        GameResultDto result = GameResultJudge.judge(dealer, players);

        DealerResultDto dealerResult = result.getDealerResult();
        PlayerResultDto firstPlayerResult = result.getPlayerResults().get(0);
        PlayerResultDto secondPlayerResult = result.getPlayerResults().get(1);

        assertThat(firstPlayerResult.getProceeds()).isEqualTo(1000);
        assertThat(secondPlayerResult.getProceeds()).isEqualTo(-2000);
        assertThat(dealerResult.getProceeds()).isEqualTo(1000);
    }
}