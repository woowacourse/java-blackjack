package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.constant.Result;
import domain.card.Suit;
import domain.dto.GameResultDto;
import domain.game.GameResultJudge;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultJudgeTest {

    @Test
    void 플레이어가_딜러보다_점수가_높으면_WIN이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEART));

        Player player = new Player("pobi",1000);
        player.receiveCard(new Card(Rank.TEN, Suit.CLUB));
        player.receiveCard(new Card(Rank.NINE, Suit.DIAMOND));

        Players players = new Players();
        players.add(player);

        List<GameResultDto> result = GameResultJudge.judge(dealer, players);

        assertThat(result).hasSize(2);
        assertThat(result.get(1).getPlayerName()).isEqualTo("pobi");
        assertThat(result.get(1).getResult()).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어가_딜러보다_점수가_낮으면_LOSE이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.NINE, Suit.HEART));

        Player player = new Player("pobi", 1000);
        player.receiveCard(new Card(Rank.TEN, Suit.CLUB));
        player.receiveCard(new Card(Rank.SEVEN, Suit.DIAMOND));

        Players players = new Players();
        players.add(player);

        List<GameResultDto> result = GameResultJudge.judge(dealer, players);

        assertThat(result.get(1).getResult()).isEqualTo(Result.LOSE);
    }

    @Test
    void 플레이어와_딜러의_점수가_같으면_DRAW이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.EIGHT, Suit.HEART));

        Player player = new Player("pobi", 1000);
        player.receiveCard(new Card(Rank.NINE, Suit.CLUB));
        player.receiveCard(new Card(Rank.NINE, Suit.DIAMOND));

        Players players = new Players();
        players.add(player);

        List<GameResultDto> result = GameResultJudge.judge(dealer, players);

        assertThat(result.get(1).getResult()).isEqualTo(Result.DRAW);
    }

    @Test
    void 플레이어가_버스트면_딜러와_상관없이_LOSE이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEART));

        Player player = new Player("pobi", 1000);
        player.receiveCard(new Card(Rank.KING, Suit.CLUB));
        player.receiveCard(new Card(Rank.QUEEN, Suit.DIAMOND));
        player.receiveCard(new Card(Rank.TWO, Suit.HEART));

        Players players = new Players();
        players.add(player);

        List<GameResultDto> result = GameResultJudge.judge(dealer, players);

        assertThat(result.get(1).getResult()).isEqualTo(Result.LOSE);
    }

    @Test
    void 딜러가_버스트면_플레이어가_버스트가_아닌_한_WIN이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.KING, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.QUEEN, Suit.HEART));
        dealer.receiveCard(new Card(Rank.TWO, Suit.CLUB));

        Player player = new Player("pobi", 1000);
        player.receiveCard(new Card(Rank.TEN, Suit.DIAMOND));
        player.receiveCard(new Card(Rank.SEVEN, Suit.HEART));

        Players players = new Players();
        players.add(player);

        List<GameResultDto> result = GameResultJudge.judge(dealer, players);

        assertThat(result.get(1).getResult()).isEqualTo(Result.WIN);
    }

    @Test
    void 최종결과_첫번째에는_딜러가_포함된다() {
        Dealer dealer = new Dealer();
        Players players = new Players();
        players.add(new Player("pobi", 1000));

        List<GameResultDto> result = GameResultJudge.judge(dealer, players);

        assertThat(result.getFirst().getPlayerName()).isEqualTo("딜러");
    }
}