package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Dealer;
import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.ResultDto;
import blackjack.utils.HandFixture;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultManagerTest {

    BlackJackResultManager blackJackResultManager;

    @BeforeEach
    void init() {
        blackJackResultManager = new BlackJackResultManager();
    }

    @DisplayName("결과를 연산한다.")
    @Test
    void test5() {
        // given
        Dealer dealer = new Dealer(HandFixture.createHandWithOptimisticValue20());

        ArrayList<Player> playerList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            playerList.add(new Player("꾹이", HandFixture.createHandWithOptimisticValue15()));
        }

        Players players = Players.from(playerList);

        // when
        ResultDto resultDto = blackJackResultManager.calculateCardResult(players, dealer);

        // then
        assertThat(resultDto.dealerResult()).containsEntry(GameResultType.WIN, 5);
    }

    @DisplayName("플레이어와 딜러가 busted 라면 무를 반환한다.")
    @Test
    void test6() {

        Dealer dealer = new Dealer(HandFixture.busted());

        ArrayList<Player> playerList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            playerList.add(new Player("꾹이", HandFixture.busted()));
        }

        Players players = Players.from(playerList);

        // when
        ResultDto resultDto = blackJackResultManager.calculateCardResult(players, dealer);

        assertThat(resultDto.dealerResult()).containsEntry(GameResultType.TIE, 5);
    }

    @DisplayName("플레이어만 busted라면 딜러가 승리한다.")
    @Test
    void test7() {
        // given
        Dealer dealer = new Dealer(HandFixture.normal());

        ArrayList<Player> playerList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            playerList.add(new Player("꾹이", HandFixture.busted()));
        }

        Players players = Players.from(playerList);

        // when
        ResultDto resultDto = blackJackResultManager.calculateCardResult(players, dealer);

        // then
        assertThat(resultDto.dealerResult()).containsEntry(GameResultType.WIN, 5);
    }

    @DisplayName("딜러만 busted라면 플레이어가 승리한다.")
    @Test
    void test8() {
        // given
        Dealer dealer = new Dealer(HandFixture.busted());

        ArrayList<Player> playerList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            playerList.add(new Player("꾹이", HandFixture.normal()));
        }

        Players players = Players.from(playerList);

        // when
        ResultDto resultDto = blackJackResultManager.calculateCardResult(players, dealer);

        // then
        assertThat(resultDto.dealerResult()).containsEntry(GameResultType.LOSE, 5);
    }
}
