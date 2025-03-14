package blackjack.blackjack;

import static blackjack.fixture.TestFixture.provideBlackjackCards;
import static blackjack.fixture.TestFixture.provideSum17Cards;
import static blackjack.fixture.TestFixture.provideSum18Cards;
import static blackjack.fixture.TestFixture.provideSum20Cards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.cardMachine.CardRandomMachine;
import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.gamer.Players;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @DisplayName("딜러와 플레이어에게 모두 2장씩 나눠준다.")
    @Test
    void deal() {
        // given
        final Dealer dealer = Dealer.getDealer(new CardRandomMachine());
        final Players players = Players.from("엠제이, 밍트");
        final Blackjack blackjack = new Blackjack();

        // when
        dealer.initCardMachine();

        final Player player1 = players.getPlayers().get(0);
        final Player player2 = players.getPlayers().get(1);

        blackjack.spreadInitCardsToDealer(dealer);
        blackjack.spreadInitCardsToPlayers(dealer, players);

        // then
        assertAll(
                () -> assertThat(dealer.showAllCards()).hasSize(2),
                () -> assertThat(player1.showAllCards()).hasSize(2),
                () -> assertThat(player2.showAllCards()).hasSize(2)
        );
    }

    @DisplayName("푸시인지 판단한다.")
    @Test
    void isPush() {
        // given
        final Blackjack blackjack = new Blackjack();
        final Dealer dealer = Dealer.getDealer(new CardRandomMachine());
        final Players players = Players.from("엠제이, 밍트");

        dealer.receiveCards(provideBlackjackCards());
        final Player player1 = players.getPlayers().getFirst();
        player1.receiveCards(provideBlackjackCards());

        // when & then
        assertThat(blackjack.isPush(dealer, players)).isTrue();
    }

    @DisplayName("승, 패, 무, 블랙잭 상황의 플레이어들의 승패를 계산한다.")
    @Test
    void calculateWinningResult() {
        // given
        final Blackjack blackjack = new Blackjack();
        final Dealer dealer = Dealer.getDealer(new CardRandomMachine());
        final Players players = Players.from("엠제이, 밍트, 리원, 포스티");

        final Player player1 = players.getPlayers().get(0);
        final Player player2 = players.getPlayers().get(1);
        final Player player3 = players.getPlayers().get(2);
        final Player player4 = players.getPlayers().get(3);

        dealer.receiveCards(provideSum18Cards());
        player1.receiveCards(provideBlackjackCards());
        player2.receiveCards(provideSum20Cards());
        player3.receiveCards(provideSum17Cards());
        player4.receiveCards(provideSum18Cards());

        // when & then
        assertThat(blackjack.calculateWinningResult(false, dealer, players)).isEqualTo(
                Map.of(
                        player1, WinningStatus.BLACKJACK,
                        player2, WinningStatus.WIN,
                        player3, WinningStatus.LOSE,
                        player4, WinningStatus.DRAW
                )
        );
    }

    @DisplayName("푸시 상황의 플레이어들의 승패를 계산한다.")
    @Test
    void calculateWinningResultWhenPush() {
        // given
        final Blackjack blackjack = new Blackjack();
        final Dealer dealer = Dealer.getDealer(new CardRandomMachine());
        final Players players = Players.from("엠제이, 밍트, 리원, 포스티");

        final Player player1 = players.getPlayers().get(0);
        final Player player2 = players.getPlayers().get(1);
        final Player player3 = players.getPlayers().get(2);
        final Player player4 = players.getPlayers().get(3);

        dealer.receiveCards(provideBlackjackCards());
        player1.receiveCards(provideBlackjackCards());
        player2.receiveCards(provideSum20Cards());
        player3.receiveCards(provideSum17Cards());
        player4.receiveCards(provideSum18Cards());

        // when & then
        assertThat(blackjack.calculateWinningResult(true, dealer, players)).isEqualTo(
                Map.of(
                        player1, WinningStatus.PUSH,
                        player2, WinningStatus.LOSE,
                        player3, WinningStatus.LOSE,
                        player4, WinningStatus.LOSE
                )
        );
    }
}
