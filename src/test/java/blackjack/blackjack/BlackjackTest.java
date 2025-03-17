package blackjack.blackjack;

import static blackjack.fixture.TestFixture.provideBlackjackCards;
import static blackjack.fixture.TestFixture.provideBustCards;
import static blackjack.fixture.TestFixture.provideSum17Cards;
import static blackjack.fixture.TestFixture.provideSum18Cards;
import static blackjack.fixture.TestFixture.provideSum20Cards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.cardMachine.CardRandomMachine;
import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.gamer.Players;
import blackjack.state.finalState.LoseState;
import blackjack.state.finalState.PushState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    private Blackjack blackjack;
    private Dealer dealer;
    private Players players;

    @BeforeEach
    void setUp() {
        blackjack = new Blackjack();
        dealer = new Dealer(new CardRandomMachine());
    }

    @DisplayName("딜러와 플레이어에게 모두 2장씩 나눠준다.")
    @Test
    void deal() {
        // given
        players = Players.from("엠제이, 밍트");

        // when
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
        players = Players.from("엠제이, 밍트");

        dealer.receiveCards(provideBlackjackCards());
        final Player player1 = players.getPlayers().getFirst();
        player1.receiveCards(provideBlackjackCards());

        // when & then
        assertThat(blackjack.isPush(dealer, players)).isTrue();
    }


    @DisplayName("푸시 상황의 플레이어들의 승패를 계산한다.")
    @Test
    void calculateWinningResultWhenPush() {
        // given
        players = Players.from("엠제이, 밍트, 리원, 포스티");

        final Player player1 = players.getPlayers().get(0);
        final Player player2 = players.getPlayers().get(1);
        final Player player3 = players.getPlayers().get(2);
        final Player player4 = players.getPlayers().get(3);

        dealer.receiveCards(provideBlackjackCards());
        player1.receiveCards(provideBlackjackCards());
        player2.receiveCards(provideSum20Cards());
        player3.receiveCards(provideSum17Cards());
        player4.receiveCards(provideSum18Cards());

        // when
        blackjack.calculateState(players, dealer);

        // then
        assertAll(
                () -> assertThat(player1).extracting("state").isInstanceOf(PushState.class),
                () -> assertThat(player2).extracting("state").isInstanceOf(LoseState.class),
                () -> assertThat(player3).extracting("state").isInstanceOf(LoseState.class),
                () -> assertThat(player4).extracting("state").isInstanceOf(LoseState.class)
        );

    }

    @DisplayName("플레이어들의 state로부터 수익을 계산한다.")
    @Test
    void calcualteState() {
        // given
        players = Players.from("엠제이, 밍트, 리원, 포스티, 저스틴");

        final Player player1 = players.getPlayers().get(0);
        final Player player2 = players.getPlayers().get(1);
        final Player player3 = players.getPlayers().get(2);
        final Player player4 = players.getPlayers().get(3);
        final Player player5 = players.getPlayers().get(4);

        player1.betMoney("10000");
        player2.betMoney("10000");
        player3.betMoney("10000");
        player4.betMoney("10000");
        player5.betMoney("10000");

        dealer.receiveCards(provideSum18Cards());
        player1.receiveCards(provideBlackjackCards());
        player2.receiveCards(provideSum20Cards());
        player3.receiveCards(provideSum17Cards());
        player4.receiveCards(provideSum18Cards());
        player5.receiveCards(provideBustCards());

        // when
        blackjack.calculateState(players, dealer);

        // then
        assertAll(
                () -> assertThat(dealer.getProfit()).isEqualTo(5000),
                () -> assertThat(player1.getProfit()).isEqualTo(5000),
                () -> assertThat(player2.getProfit()).isEqualTo(10000),
                () -> assertThat(player3.getProfit()).isEqualTo(-10000),
                () -> assertThat(player4.getProfit()).isEqualTo(0),
                () -> assertThat(player5.getProfit()).isEqualTo(-10000)
        );
    }
}
