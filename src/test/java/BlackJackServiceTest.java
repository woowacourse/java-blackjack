import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.game.Result;
import domain.game.GameResult;
import domain.participant.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.BlackJackService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackJackServiceTest {

    Dealer dealer;
    Player winPlayer;
    Player defeatPlayer;
    Player drawPlayer;
    Player bustPlayer;
    Players players;
    BlackJackService blackJackService;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        winPlayer = new Player(new ParticipantInfo("이김", new Hand()), new Money(0));
        defeatPlayer = new Player(new ParticipantInfo("짐", new Hand()), new Money(0));
        drawPlayer = new Player(new ParticipantInfo("비김", new Hand()), new Money(0));
        bustPlayer = new Player(new ParticipantInfo("버스트", new Hand()), new Money(0));
        dealer.keepCard(new Card(Rank.FIVE, Pattern.CLOVER));
        winPlayer.keepCard(new Card(Rank.SIX, Pattern.CLOVER));
        defeatPlayer.keepCard(new Card(Rank.FOUR, Pattern.CLOVER));
        drawPlayer.keepCard(new Card(Rank.FIVE, Pattern.SPADE));
        players = new Players(List.of(winPlayer, defeatPlayer, drawPlayer, bustPlayer));
        blackJackService = new BlackJackService();

    }

    @Test
    void 딜러의_총합이_플레이어의_총합보다_낮으면_플레이어가_승리한다() {
        Result result = blackJackService.calculateResult(dealer, players);
        GameResult info = result.getPlayersResult().get(winPlayer);
        assertThat(info).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러의_총합이_플레이어의_총합보다_높으면_플레이어가_패배한다() {
        Result result = blackJackService.calculateResult(dealer, players);
        GameResult info = result.getPlayersResult().get(defeatPlayer);
        assertThat(info).isEqualTo(GameResult.DEFEAT);
    }

    @Test
    void 딜러의_총합과_플레이어의_총합이_같으면_비긴다() {
        Result result = blackJackService.calculateResult(dealer, players);
        GameResult info = result.getPlayersResult().get(drawPlayer);
        assertThat(info).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 먼저_버스트된_플레이어는_딜러의_결과에_관계없이_패배한다() {
        Result result = blackJackService.calculateResult(dealer, players);
        bustPlayer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));
        bustPlayer.keepCard(new Card(Rank.JACK, Pattern.SPADE));
        bustPlayer.keepCard(new Card(Rank.JACK, Pattern.HEART));
        dealer.keepCard(new Card(Rank.KING, Pattern.HEART));
        dealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));

        GameResult info = result.getPlayersResult().get(bustPlayer);
        assertThat(info).isEqualTo(GameResult.DEFEAT);
    }

    @Test
    void 딜러가_버스트시_딜러보다_낮은_점수의_플레이어들은_무조건_승리한다() {
        dealer.keepCard(new Card(Rank.KING, Pattern.HEART));
        dealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));
        Result result = blackJackService.calculateResult(dealer, players);

        GameResult info = result.getPlayersResult().get(defeatPlayer);
        assertThat(info).isEqualTo(GameResult.WIN);
    }
}
