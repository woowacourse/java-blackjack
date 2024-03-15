package domain.blackjack;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;
import domain.player.PlayerResult;
import domain.player.Players;
import dto.GameResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {
    @Test
    @DisplayName("딜러에게 2장의 카드가 주어졌는지 확인한다")
    void initializeDealer() {
        final Blackjack blackjack = Blackjack.of(Players.fromNames(List.of("a", "b")));

        assertThat(blackjack.getDealer().getHands().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아닐 때 카드를 더 받을 수 있다")
    void playerCanHit() {
        final Player player = new Player(new Name("a"));
        player.init(new Card(Rank.TEN, Suit.CLUBS),new Card(Rank.TEN, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(new Players(List.of(player)), new Dealer());

        assertThat(blackjack.canPlayerHit("a")).isTrue();
    }

    @Test
    @DisplayName("플레이어가 블랙잭일 때 카드를 더 받을 수 없다")
    void playerCanNotHit() {
        final Player player = new Player(new Name("a"));
        player.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(new Players(List.of(player)), new Dealer());

        assertThat(blackjack.canPlayerHit("a")).isFalse();
    }

    @Test
    @DisplayName("딜러의 합계가 17미만이라면 더이상 카드를 받을 수 있다")
    void dealerCanHit() {
        final Dealer dealer = new Dealer();
        dealer.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.SIX, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(Players.fromNames(List.of("a")), dealer);

        assertThat(blackjack.canDealerHit()).isTrue();
    }

    @Test
    @DisplayName("딜러의 합계가 17이상이라면 더이상 카드를 받을 수 있다")
    void dealerCanNotHit() {
        final Dealer dealer = new Dealer();
        dealer.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.SEVEN, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(Players.fromNames(List.of("a")), dealer);

        assertThat(blackjack.canDealerHit()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 히트하면 플레이어의 카드가 한 장 늘어난다")
    void playerHitCount() {
        final Players players = Players.fromNames(List.of("a"));
        final Blackjack blackjack = new Blackjack(players, new Dealer());
        players.stream().forEach(player -> player.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS)));
        blackjack.playerHit("a");

        assertThat(blackjack.getPlayer("a").getHands().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러가 히트하면 딜러의 카드가 한 장 늘어난다")
    void dealerHitCount() {
        final Dealer dealer = new Dealer();
        dealer.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(Players.fromNames(List.of("a")), dealer);
        blackjack.dealerHit();

        assertThat(blackjack.getDealer().getHands().size()).isEqualTo(3);
    }


    @Test
    @DisplayName("게임의 결과가 정상적으로 계산됐는지 확인한다")
    void gameResultTest() {
        final Dealer dealer = new Dealer();
        final Player teba = new Player(new Name("테바"));
        final Player jonge = new Player(new Name("종이"));
        dealer.init(new Card(Rank.TWO, Suit.CLUBS), new Card(Rank.TWO, Suit.CLUBS));
        teba.init(new Card(Rank.THREE, Suit.CLUBS), new Card(Rank.THREE, Suit.CLUBS));
        jonge.init(new Card(Rank.THREE, Suit.CLUBS), new Card(Rank.THREE, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(new Players(List.of(teba, jonge)), dealer);

        final GameResult gameResult = blackjack.toGameResult();

        assertAll(
                () -> assertThat(gameResult.playerResult(teba.getName())).isSameAs(PlayerResult.WIN),
                () -> assertThat(gameResult.playerResult(jonge.getName())).isSameAs(PlayerResult.WIN),
                () -> assertThat(gameResult.dealerLose()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("플레이어가 버스트 되면 딜러가 게임을 이긴다")
    void playerBust() {
        final Dealer dealer = new Dealer();
        final Player teba = new Player(new Name("테바"));
        dealer.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        teba.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        teba.add(new Card(Rank.TWO, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(new Players(List.of(teba)), dealer);

        final GameResult gameResult = blackjack.toGameResult();

        assertThat(gameResult.playerResult(teba.getName())).isSameAs(PlayerResult.LOSE);
        assertThat(gameResult.dealerWin()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 버스트 되고 플레이어가 버스트 되지 않으면 플레이어가 게임을 이긴다")
    void dealerBust() {
        final Dealer dealer = new Dealer();
        final Player teba = new Player(new Name("테바"));
        dealer.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        teba.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        dealer.add(new Card(Rank.TEN, Suit.CLUBS));

        final Blackjack blackjack = new Blackjack(new Players(List.of(teba)), dealer);

        final GameResult gameResult = blackjack.toGameResult();

        assertThat(gameResult.dealerLose()).isEqualTo(1);
        assertThat(gameResult.playerResult(teba.getName())).isSameAs(PlayerResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 버스트 둘 다 버스트 되지 않고 점수가 같으면 무승부가 된다")
    void tie() {
        final Dealer dealer = new Dealer();
        final Player teba = new Player(new Name("테바"));
        dealer.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        teba.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));

        final Blackjack blackjack = new Blackjack(new Players(List.of(teba)), dealer);

        final GameResult gameResult = blackjack.toGameResult();

        assertThat(gameResult.dealerTie()).isEqualTo(1);
        assertThat(gameResult.playerResult(teba.getName())).isSameAs(PlayerResult.TIE);
    }
}
