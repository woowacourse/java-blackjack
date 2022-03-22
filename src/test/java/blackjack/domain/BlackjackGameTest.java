package blackjack.domain;

import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.betting.BettingPlayer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.PlayerProfit;
import blackjack.domain.state.State;
import blackjack.view.InputView;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private List<Participant> players;
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        players = createPlayers();
        blackjackGame = BlackjackGame.create(players);
    }

    private List<Participant> createPlayers() {
        return Collections.singletonList(new Player("seung"));
    }

    @DisplayName("게임 실행 객체가 정상적으로 생성되었는지 확인")
    @Test
    void create() {
        List<Participant> players = blackjackGame.getPlayers();
        Participant player = players.get(0);

        assertThat(player.getName()).isEqualTo("seung");
    }

    @DisplayName("기본 카드 세팅이 정상적인지 확인")
    @Test
    void drawBaseCards() {
        blackjackGame.drawBaseCards();
        Participant dealer = blackjackGame.getDealer();
        List<Participant> players = blackjackGame.getPlayers();
        Participant player = players.get(0);

        assertAll(
            () -> assertThat(dealer.getCards()).hasSize(2),
            () -> assertThat(player.getCards()).hasSize(2)
        );
    }

    @DisplayName("딜러가 카드를 한장 더 받는지 확인")
    @Test
    void takeMoreCardDealer() {
        Participant dealer = blackjackGame.getDealer();
        blackjackGame.takeMoreCard(dealer);;

        assertThat(dealer.getCards()).hasSize(1);
    }

    @DisplayName("플레이어가 카드를 한장 더 받는지 확인")
    @Test
    void takeMoreCardPlayer() {
        Participant player = players.get(0);
        blackjackGame.takeMoreCard(player);

        assertThat(player.getCards()).hasSize(1);
    }


    @DisplayName("플레이어의 게임결과를 잘 계산하는지 확인")
    @Test
    void calculatePlayerProfit() {
        Participant dealer = blackjackGame.getDealer();
        State dealerState = dealer.getState();
        initCards(dealerState);
        List<BettingPlayer> bettingPlayers = getBettingPlayers();

        List<PlayerProfit> actual = blackjackGame.calculatePlayerProfit(bettingPlayers);
        PlayerProfit playerProfit = actual.get(0);
        assertAll(
            () -> assertThat(playerProfit.getProfit()).isEqualTo(15000),
            () -> assertThat(playerProfit.getName()).isEqualTo("seung")
        );
    }

    @DisplayName("딜러의 게임결과를 잘 계산하는지 확인")
    @Test
    void calculateDealerProfit() {
        Participant dealer = blackjackGame.getDealer();
        State dealerState = dealer.getState();
        initCards(dealerState);
        List<BettingPlayer> bettingPlayers = getBettingPlayers();

        int dealerProfit = blackjackGame.calculateDealerProfit(bettingPlayers);
        assertThat(dealerProfit).isEqualTo(-15000);
    }

    private List<BettingPlayer> getBettingPlayers() {
        return players.stream()
            .map(player -> BettingPlayer.of(player, "10000"))
            .collect(Collectors.toList());
    }

    private void initCards(State dealerState) {
        dealerState = dealerState.draw(kingCard);
        dealerState = dealerState.draw(tenCard);
        dealerState = dealerState.draw(jackCard);

        for (Participant player : players) {
            player.hit(aceCard);
            player.hit(tenCard);
        }
    }
}
