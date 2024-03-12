package domain.blackjack;

import domain.Blackjack;
import domain.GameResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;
import domain.player.PlayerResult;
import domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackjackTest {
    @Test
    @DisplayName("딜러에게 2장의 카드가 주어졌는지 확인한다")
    void initializeDealer() {
        final Dealer dealer = new Dealer();
        Blackjack.startGameWithInitialization(Players.from(List.of("a", "b")), dealer);
        assertThat(dealer.getHands().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 2장의 카드가 주어졌는지 확인한다")
    void initializePlayers() {
        final Blackjack blackjack = Blackjack.startGameWithInitialization(Players.from(List.of("a", "b")),
                new Dealer());
        assertThat(blackjack.getPlayers().getValue().get(0).getHands().size()).isEqualTo(2);
    }


    @Test
    @DisplayName("게임의 결과가 제대로 계산됐는지 확인한다")
    void gameResultTest() {
        final Dealer dealer = new Dealer();
        final Player teba = new Player(new Name("테바"));
        final Player jonge = new Player(new Name("종이"));
        teba.hit(new Card(Rank.ACE, Suit.CLUBS));
        jonge.hit(new Card(Rank.ACE, Suit.HEARTS));
        final Blackjack blackjack = new Blackjack(new Players(List.of(teba, jonge)), dealer);

        final GameResult gameResult = blackjack.finishGame();

        assertAll(
                () -> assertThat(gameResult.playerResult(teba)).isSameAs(PlayerResult.WIN),
                () -> assertThat(gameResult.playerResult(jonge)).isSameAs(PlayerResult.WIN),
                () -> assertThat(gameResult.dealerLose()).isEqualTo(2)
        );

    }

    @Test
    @DisplayName("플레이어가 버스트 되고 딜러가 버스트 되지 않으면 딜러가 게임을 이긴다")
    void playerBust() {
        final Dealer dealer = new Dealer();
        final Player teba = new Player(new Name("테바"));
        teba.hit(new Card(Rank.TEN, Suit.CLUBS));
        teba.hit(new Card(Rank.TEN, Suit.CLUBS));
        teba.hit(new Card(Rank.TWO, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(new Players(List.of(teba)), dealer);

        final GameResult gameResult = blackjack.finishGame();

        assertThat(gameResult.playerResult(teba)).isSameAs(PlayerResult.LOSE);
        assertThat(gameResult.dealerWin()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 버스트 되고 플레이어가 버스트 되지 않으면 플레이어가 게임을 이긴다")
    void dealerBust() {
        final Dealer dealer = new Dealer();
        final Player teba = new Player(new Name("테바"));
        dealer.hit(new Card(Rank.TEN, Suit.CLUBS));
        dealer.hit(new Card(Rank.TEN, Suit.CLUBS));
        dealer.hit(new Card(Rank.TWO, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(new Players(List.of(teba)), dealer);

        final GameResult gameResult = blackjack.finishGame();

        assertThat(gameResult.dealerLose()).isEqualTo(1);
        assertThat(gameResult.playerResult(teba)).isSameAs(PlayerResult.WIN);
    }
}
