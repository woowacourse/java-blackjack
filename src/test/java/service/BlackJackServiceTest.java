package service;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.game.Result;
import domain.game.Outcome;
import domain.participant.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackJackServiceTest {

    private final BlackJackService blackJackService = new BlackJackService();
    private Dealer dealer;

    @BeforeEach
    void init() {
        dealer = new Dealer();
    }

    private Player dummyPlayer(String name) {
        return new Player(new PlayerInfo(name, new Money(BigDecimal.ZERO)));
    }

    @Test
    void 딜러의_총합이_플레이어의_총합보다_낮으면_플레이어가_승리한다() {
        Player player = dummyPlayer("이김");
        Players players = new Players(List.of(player));
        player.keepCard(new Card(Rank.SIX, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.FIVE, Pattern.CLOVER));

        Result result = blackJackService.calculateResult(dealer, players);
        Outcome info = result.getPlayersResult().get(player);

        assertThat(info).isEqualTo(Outcome.WIN);
    }

    @Test
    void 딜러의_총합이_플레이어의_총합보다_높으면_플레이어가_패배한다() {
        Player player = dummyPlayer("짐");
        Players players = new Players(List.of(player));
        player.keepCard(new Card(Rank.FOUR, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.FIVE, Pattern.CLOVER));

        Result result = blackJackService.calculateResult(dealer, players);
        Outcome info = result.getPlayersResult().get(player);

        assertThat(info).isEqualTo(Outcome.DEFEAT);
    }

    @Test
    void 딜러의_총합과_플레이어의_총합이_같으면_비긴다() {
        Player player = dummyPlayer("비김");
        Players players = new Players(List.of(player));
        player.keepCard(new Card(Rank.FIVE, Pattern.SPADE));
        dealer.keepCard(new Card(Rank.FIVE, Pattern.CLOVER));

        Result result = blackJackService.calculateResult(dealer, players);
        Outcome info = result.getPlayersResult().get(player);

        assertThat(info).isEqualTo(Outcome.PUSH);
    }

    @Test
    void 먼저_버스트된_플레이어는_딜러의_결과에_관계없이_패배한다() {
        Player player = dummyPlayer("버스트됨");
        Players players = new Players(List.of(player));
        player.keepCard(new Card(Rank.JACK, Pattern.CLOVER));
        player.keepCard(new Card(Rank.JACK, Pattern.SPADE));
        player.keepCard(new Card(Rank.TWO, Pattern.HEART));
        dealer.keepCard(new Card(Rank.KING, Pattern.HEART));
        dealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));

        Result result = blackJackService.calculateResult(dealer, players);
        Outcome info = result.getPlayersResult().get(player);

        assertThat(info).isEqualTo(Outcome.DEFEAT);
    }

    @Test
    void 딜러가_버스트시_딜러보다_낮은_점수의_플레이어들은_무조건_승리한다() {
        Player player = dummyPlayer("이김당함");
        Players players = new Players(List.of(player));
        player.keepCard(new Card(Rank.FOUR, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.TWO, Pattern.SPADE));
        dealer.keepCard(new Card(Rank.KING, Pattern.HEART));
        dealer.keepCard(new Card(Rank.QUEEN, Pattern.HEART));

        Result result = blackJackService.calculateResult(dealer, players);
        Outcome info = result.getPlayersResult().get(player);

        assertThat(info).isEqualTo(Outcome.WIN);
    }

    @Test
    void 딜러가_블랙잭이면_플레이어가_21을_달성해도_딜러의_승리다() {
        Player player = dummyPlayer("21이어도짐");
        Players players = new Players(List.of(player));
        player.keepCard(new Card(Rank.FIVE, Pattern.HEART));
        player.keepCard(new Card(Rank.TEN, Pattern.HEART));
        player.keepCard(new Card(Rank.SIX, Pattern.HEART));
        dealer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        dealer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));

        Result result = blackJackService.calculateResult(dealer, players);
        Outcome info = result.getPlayersResult().get(player);

        assertThat(info).isEqualTo(Outcome.DEFEAT);
    }
}
