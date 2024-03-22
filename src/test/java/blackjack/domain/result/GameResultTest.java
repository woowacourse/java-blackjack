package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @DisplayName("플레이어만 블랙잭이면 게임 결과는 블랙잭이다.")
    @Test
    void playerBlackjackTest() {
        // given
        Player player = new Player("eden", 0);
        Dealer dealer = new Dealer();

        // when
        player.receiveCard(Card.of(Suit.HEART, Rank.KING));
        player.receiveCard(Card.of(Suit.HEART, Rank.ACE));

        dealer.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(Card.of(Suit.HEART, Rank.KING));
        dealer.receiveCard(Card.of(Suit.DIAMOND, Rank.KING));

        GameResult gameResult = GameResult.ofPlayer(player, dealer);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.BLACKJACK);
    }

    @DisplayName("딜러와 플레이어가 모두 블랙잭이면 게임결과는 무승부이다.")
    @Test
    void drawTest() {
        // given
        Player player = new Player("eden", 0);
        Dealer dealer = new Dealer();

        // when
        player.receiveCard(Card.of(Suit.HEART, Rank.KING));
        player.receiveCard(Card.of(Suit.HEART, Rank.ACE));

        dealer.receiveCard(Card.of(Suit.HEART, Rank.KING));
        dealer.receiveCard(Card.of(Suit.HEART, Rank.ACE));

        GameResult gameResult = GameResult.ofPlayer(player, dealer);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("딜러가 버스트이면 승패 상관없이 플레이어가 승리한다.")
    @Test
    void playerWinWhenDealerOver21Test() {
        // given
        Player player = new Player("eden", 0);
        Dealer dealer = new Dealer();

        // when
        player.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        player.receiveCard(Card.of(Suit.HEART, Rank.KING));

        dealer.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(Card.of(Suit.HEART, Rank.KING));
        dealer.receiveCard(Card.of(Suit.DIAMOND, Rank.KING));

        GameResult gameResult = GameResult.ofPlayer(player, dealer);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어가 버스트이고 딜러는 버스트가 아니면 플레이어는 패배한다")
    @Test
    void playerLoseWhenBustTest() {
        // given
        Player player = new Player("eden", 0);
        Dealer dealer = new Dealer();

        // when
        player.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        player.receiveCard(Card.of(Suit.HEART, Rank.SEVEN));
        player.receiveCard(Card.of(Suit.DIAMOND, Rank.SEVEN));

        dealer.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(Card.of(Suit.HEART, Rank.KING));

        GameResult gameResult = GameResult.ofPlayer(player, dealer);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("플레이어와 딜러가 버스트가 아니고 딜러의 점수보다 높으면 플레이어는 승리한다")
    @Test
    void playerWinWhenScoreGreaterThenDealerTest() {
        // given
        Player player = new Player("eden", 0);
        Dealer dealer = new Dealer();

        // when
        player.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        player.receiveCard(Card.of(Suit.HEART, Rank.KING));

        dealer.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(Card.of(Suit.HEART, Rank.SEVEN));

        GameResult gameResult = GameResult.ofPlayer(player, dealer);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 플레이어가 버스트가 아니고 동점이면 게임 결과는 무승부이다.")
    @Test
    void drawWhenPlayerAndDealerScoreSameTest() {
        // given
        Player player = new Player("eden", 0);
        Dealer dealer = new Dealer();

        // when
        player.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        player.receiveCard(Card.of(Suit.HEART, Rank.KING));

        dealer.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(Card.of(Suit.HEART, Rank.KING));

        GameResult gameResult = GameResult.ofPlayer(player, dealer);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("플레이어와 딜러가 아니고 딜러의 점수보다 낮으면 플레이어는 패배한다")
    @Test
    void playerLoseWhenScoreLittleThenDealerTest() {
        // given
        Player player = new Player("eden", 0);
        Dealer dealer = new Dealer();

        // when
        player.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        player.receiveCard(Card.of(Suit.HEART, Rank.SEVEN));

        dealer.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(Card.of(Suit.HEART, Rank.KING));

        GameResult gameResult = GameResult.ofPlayer(player, dealer);

        // then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }
}
