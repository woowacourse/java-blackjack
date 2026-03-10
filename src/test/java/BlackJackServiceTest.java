import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.game.Result;
import domain.game.ResultInfo;
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
    Players players;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        winPlayer = new Player(new ParticipantInfo("이김", new Hand()), new Money(0));
        defeatPlayer = new Player(new ParticipantInfo("짐", new Hand()),new Money(0));
        drawPlayer = new Player(new ParticipantInfo("비김", new Hand()),new Money(0));
        dealer.keepCard(new Card(Rank.FIVE, Pattern.CLOVER));
        winPlayer.keepCard(new Card(Rank.SIX, Pattern.CLOVER));
        defeatPlayer.keepCard(new Card(Rank.FOUR, Pattern.CLOVER));
        drawPlayer.keepCard(new Card(Rank.FIVE, Pattern.SPADE));
        players = new Players(List.of(winPlayer, defeatPlayer, drawPlayer));
    }

    @Test
    void 딜러의_총합이_플레이어의_총합보다_낮으면_플레이어가_승리한다() {
        BlackJackService blackJackService = new BlackJackService();
        Result result = blackJackService.calculateResult(dealer, players);
        ResultInfo info = result.getPlayersResult().get(winPlayer.getName());
        assertThat(info).isEqualTo(ResultInfo.WIN);
    }

    @Test
    void 딜러의_총합이_플레이어의_총합보다_높으면_플레이어가_패배한다() {
        BlackJackService blackJackService = new BlackJackService();
        Result result = blackJackService.calculateResult(dealer, players);
        ResultInfo info = result.getPlayersResult().get(defeatPlayer.getName());
        assertThat(info).isEqualTo(ResultInfo.DEFEAT);
    }

    @Test
    void 딜러의_총합과_플레이어의_총합이_같으면_비긴다() {
        BlackJackService blackJackService = new BlackJackService();
        Result result = blackJackService.calculateResult(dealer, players);
        ResultInfo info = result.getPlayersResult().get(drawPlayer.getName());
        assertThat(info).isEqualTo(ResultInfo.DRAW);
    }
}
