package domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.bet.BettingMoney;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RefereeTest {

    private Referee referee;

    @BeforeEach
    void beforeEach() {
        referee = new Referee();
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부다")
    @Test
    void 양쪽_모두_블랙잭이면_무승부다() {
        Player player = createPlayer("플레이어");
        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.ACE, Suit.HEART));
        dealer.addCard(new Card(Rank.QUEEN, Suit.SPADE));

        assertThat(referee.judge(player, dealer)).isEqualTo(Result.TIE);
    }

    @DisplayName("플레이어만 블랙잭이면 블랙잭 승리다")
    @Test
    void 플레이어만_블랙잭이면_블랙잭_승리다() {
        Player player = createPlayer("플레이어");
        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.KING, Suit.SPADE));
        dealer.addCard(new Card(Rank.NINE, Suit.HEART));

        assertThat(referee.judge(player, dealer)).isEqualTo(Result.BLACKJACK_WIN);
    }

    @DisplayName("플레이어가 버스트면 패배한다")
    @Test
    void 플레이어가_버스트면_패배한다() {
        Player player = createPlayer("플레이어");
        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));
        player.addCard(new Card(Rank.TWO, Suit.DIAMOND));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.FIVE, Suit.SPADE));
        dealer.addCard(new Card(Rank.FIVE, Suit.HEART));

        assertThat(referee.judge(player, dealer)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어 점수가 딜러보다 높으면 승리한다")
    @Test
    void 플레이어_점수가_딜러보다_높으면_승리한다() {
        Player player = createPlayer("플레이어");
        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.NINE, Suit.HEART));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.KING, Suit.HEART));
        dealer.addCard(new Card(Rank.FIVE, Suit.SPADE));

        assertThat(referee.judge(player, dealer)).isEqualTo(Result.WIN);
    }

    @DisplayName("동점이면 무승부다")
    @Test
    void 동점이면_무승부다() {
        Player player = createPlayer("플레이어");
        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.FIVE, Suit.HEART));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.KING, Suit.HEART));
        dealer.addCard(new Card(Rank.FIVE, Suit.SPADE));

        assertThat(referee.judge(player, dealer)).isEqualTo(Result.TIE);
    }

    @DisplayName("플레이어 점수가 딜러보다 낮으면 패배한다")
    @Test
    void 플레이어_점수가_딜러보다_낮으면_패배한다() {
        Player player = createPlayer("플레이어");
        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.FIVE, Suit.HEART));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.KING, Suit.HEART));
        dealer.addCard(new Card(Rank.NINE, Suit.SPADE));

        assertThat(referee.judge(player, dealer)).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 버스트면 플레이어가 승리한다")
    @Test
    void 딜러가_버스트면_플레이어가_승리한다() {
        Player player = createPlayer("플레이어");
        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.FIVE, Suit.HEART));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.KING, Suit.HEART));
        dealer.addCard(new Card(Rank.KING, Suit.SPADE));
        dealer.addCard(new Card(Rank.TWO, Suit.DIAMOND));

        assertThat(referee.judge(player, dealer)).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러만 블랙잭이고 플레이어가 3장 21점이면 패배한다")
    @Test
    void 딜러만_블랙잭이면_플레이어가_패배한다() {
        Player player = createPlayer("플레이어");
        player.addCard(new Card(Rank.FIVE, Suit.SPADE));
        player.addCard(new Card(Rank.SIX, Suit.HEART));
        player.addCard(new Card(Rank.KING, Suit.DIAMOND));

        Dealer dealer = new Dealer("딜러");
        dealer.addCard(new Card(Rank.ACE, Suit.HEART));
        dealer.addCard(new Card(Rank.KING, Suit.SPADE));

        assertThat(referee.judge(player, dealer)).isEqualTo(Result.LOSE);
    }

    @DisplayName("블랙잭 승리는 딜러 패배로 카운트된다")
    @Test
    void 블랙잭_승리는_딜러_패배로_카운트된다() {
        Map<Player, Result> playerResults = new LinkedHashMap<>();
        playerResults.put(createPlayer("a"), Result.BLACKJACK_WIN);
        playerResults.put(createPlayer("b"), Result.WIN);
        playerResults.put(createPlayer("c"), Result.LOSE);

        Map<Result, Integer> dealerResult = referee.countDealerResult(playerResults);

        assertThat(dealerResult.get(Result.WIN)).isEqualTo(1);
        assertThat(dealerResult.get(Result.LOSE)).isEqualTo(2);
        assertThat(dealerResult.get(Result.TIE)).isEqualTo(0);
    }

    private Player createPlayer(String name) {
        return new Player(name, new BettingMoney(1000));
    }
}
