package blackjack.domain;

import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.PlayerProfit;
import blackjack.domain.state.State;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private List<Player> players;
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        players = createPlayers();
        blackjackGame = BlackjackGame.create(players);
    }

    private List<Player> createPlayers() {
        return Collections.singletonList(Player.from("seung", "10000"));
    }

    @DisplayName("게임 실행 객체가 정상적으로 생성되었는지 확인")
    @Test
    void create() {
        List<Player> players = blackjackGame.getPlayers();
        Player player = players.get(0);

        assertThat(player.getName()).isEqualTo("seung");
    }

    @DisplayName("기본 카드 세팅이 정상적인지 확인")
    @Test
    void drawBaseCards() {
        blackjackGame.drawBaseCards();
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();
        Player player = players.get(0);
        Participant dealerParticipant = dealer.getParticipant();
        Participant playerParticipant = player.getParticipant();

        assertAll(
            () -> assertThat(dealerParticipant.getCards()).hasSize(2),
            () -> assertThat(playerParticipant.getCards()).hasSize(2)
        );
    }

    @DisplayName("딜러가 카드를 한장 더 받는지 확인")
    @Test
    void takeMoreCardDealer() {
        Dealer dealer = blackjackGame.getDealer();
        blackjackGame.takeMoreCard();
        Participant participant = dealer.getParticipant();

        assertThat(participant.getCards()).hasSize(1);
    }

    @DisplayName("플레이어가 카드를 한장 더 받는지 확인")
    @Test
    void takeMoreCardPlayer() {
        Player player = players.get(0);
        blackjackGame.takeMoreCard(player);

        assertThat(player.getCards()).hasSize(1);
    }


    @DisplayName("게임결과를 잘 계산하는지 확인")
    @Test
    void calculatePlayerProfit() {
        Dealer dealer = blackjackGame.getDealer();
        State dealerState = dealer.getState();
        initCards(dealerState);

        List<PlayerProfit> actual = blackjackGame.calculatePlayerProfit();
        PlayerProfit playerProfit = actual.get(0);
        assertAll(
            () -> assertThat(playerProfit.getProfit()).isEqualTo(15000),
            () -> assertThat(playerProfit.getName()).isEqualTo("seung")
        );
    }

    @Test
    void calculateDealerProfit() {
        Dealer dealer = blackjackGame.getDealer();
        State dealerState = dealer.getState();
        initCards(dealerState);

        int dealerProfit = blackjackGame.calculateDealerProfit();
        assertThat(dealerProfit).isEqualTo(-15000);
    }

    private void initCards(State dealerState) {
        dealerState = dealerState.draw(kingCard);
        dealerState = dealerState.draw(tenCard);
        dealerState = dealerState.draw(jackCard);

        for (Player player : players) {
            player.hit(aceCard);
            player.hit(tenCard);
        }
    }
}
