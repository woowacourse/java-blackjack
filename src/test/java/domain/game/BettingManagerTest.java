package domain.game;

import domain.participant.Dealer;
import domain.participant.ParticipantMoney;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.game.BettingManager.FIRST_TURN_DEALER_WIN;
import static domain.game.BettingManager.FIRST_TURN_PLAYER_WIN;
import static domain.game.BettingManager.IS_DEALER_WIN;
import static domain.game.BettingManager.IS_PLAYER_WIN;
import static domain.helper.MockCardShufflerHelper.createBustShuffler;
import static domain.helper.MockCardShufflerHelper.createDealerBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createDealerHighScoreShuffler;
import static domain.helper.MockCardShufflerHelper.createPlayerBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createPlayerHighScoreShuffler;

class BettingManagerTest {

    @Test
    @DisplayName("FIRST_TURN_DEALER_WIN는, 첫 턴에 딜러가 승리하면 돈을 분배한다.")
    void FIRST_TURN_DEALER_WIN_whenIsFirstTurnDealerWin_thenTrue() {
        // given
        final Participants participants = Participants.create(Players.create(List.of("pobi")));
        final GameManager gameManager = GameManager.create(createDealerBlackJackShuffler(), participants);
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();
        players.forEach(player -> player.bet(ParticipantMoney.create(10000)));

        gameManager.dealingFirstTurn();

        // when
        FIRST_TURN_DEALER_WIN
                .getReferee()
                .accept(dealer, players.get(0));

        // then
        Assertions.assertThat(dealer.getMoney())
                .isEqualTo(10000);
        Assertions.assertThat(players.get(0).getMoney())
                .isEqualTo(-10000);
    }

    @Test
    @DisplayName("FIRST_TURN_PLAYER_WIN는, 첫 턴에 플레이어가 블랙잭이면 돈을 분배한다.")
    void FIRST_TURN_PLAYER_WIN_whenIsFirstTurnPlayerWin_thenReturnTrue() {
        // given
        final Participants participants = Participants.create(Players.create(List.of("pobi")));
        final GameManager gameManager = GameManager.create(createPlayerBlackJackShuffler(), participants);
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();
        players.forEach(player -> player.bet(ParticipantMoney.create(10000)));

        gameManager.dealingFirstTurn();

        // when
        FIRST_TURN_PLAYER_WIN
                .getReferee()
                .accept(dealer, players.get(0));

        // then
        Assertions.assertThat(dealer.getMoney())
                .isEqualTo(-15000);
        Assertions.assertThat(players.get(0).getMoney())
                .isEqualTo(15000);
    }

    @Test
    @DisplayName("IS_DEALER_WIN는 딜러와 플레이어가 둘 다 버스트일 때, 딜러에게 돈을 분배한다.")
    void IS_DEALER_WIN_whenBothBust_thenReturnIsDealerWin() {
        // given
        final Participants participants = Participants.create(Players.create(List.of("pobi")));
        final GameManager gameManager = GameManager.create(createBustShuffler(), participants);
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();
        players.forEach(player -> player.bet(ParticipantMoney.create(10000)));

        gameManager.dealingFirstTurn();
        dealer.addCard(gameManager.getCard());
        players.get(0).addCard(gameManager.getCard());

        // when
        IS_DEALER_WIN
                .getReferee()
                .accept(dealer, players.get(0));

        // then
        Assertions.assertThat(dealer.getMoney())
                .isEqualTo(10000);
        Assertions.assertThat(players.get(0).getMoney())
                .isEqualTo(-10000);
    }

    @Test
    @DisplayName("IS_DEALER_WIN는 딜러가 버스트가 아니면서, 더 높은 점수를 가지고 있다면 딜러에게 돈을 분배한다.")
    void IS_DEALER_WIN_whenDealerHasHighScore_thenReturnIsDealerWin() {
        // given
        final Participants participants = Participants.create(Players.create(List.of("pobi")));
        final GameManager gameManager = GameManager.create(createDealerHighScoreShuffler(), participants);
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();
        players.forEach(player -> player.bet(ParticipantMoney.create(10000)));

        gameManager.dealingFirstTurn();

        // when
        IS_DEALER_WIN
                .getReferee()
                .accept(dealer, players.get(0));

        // then
        Assertions.assertThat(dealer.getMoney())
                .isEqualTo(10000);
        Assertions.assertThat(players.get(0).getMoney())
                .isEqualTo(-10000);
    }


    @Test
    @DisplayName("IS_DEALER_WIN는 딜러만 블랙잭이라면, 딜러에게 돈을 분배한다.")
    void IS_DEALER_WIN_whenOnlyDealerBlackjack_thenReturnIsDealerWin() {
        // given
        final Participants participants = Participants.create(Players.create(List.of("pobi")));
        final GameManager gameManager = GameManager.create(createDealerBlackJackShuffler(), participants);
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();
        players.forEach(player -> player.bet(ParticipantMoney.create(10000)));

        gameManager.dealingFirstTurn();

        // when
        IS_DEALER_WIN
                .getReferee()
                .accept(dealer, players.get(0));

        // then
        Assertions.assertThat(dealer.getMoney())
                .isEqualTo(10000);
        Assertions.assertThat(players.get(0).getMoney())
                .isEqualTo(-10000);
    }

    @Test
    @DisplayName("IS_PLAYER_WIN는 플레이어만 블랙잭이면, 플레이어에게 돈을 분배한다.")
    void IS_PLAYER_WIN_whenOnlyPlayerBlackjack_thenReturnIsPlayerWin() {
        // given
        final Participants participants = Participants.create(Players.create(List.of("pobi")));
        final GameManager gameManager = GameManager.create(createPlayerBlackJackShuffler(), participants);
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();
        players.forEach(player -> player.bet(ParticipantMoney.create(10000)));

        gameManager.dealingFirstTurn();

        // when
        IS_PLAYER_WIN
                .getReferee()
                .accept(dealer, players.get(0));

        // then
        Assertions.assertThat(dealer.getMoney())
                .isEqualTo(-10000);
        Assertions.assertThat(players.get(0).getMoney())
                .isEqualTo(10000);
    }

    @Test
    @DisplayName("IS_PLAYER_WIN는 플레이어가 더 높은 점수를 가지고 있다면, 플레이어에게 돈을 분배한다.")
    void IS_PLAYER_WIN_whenPlayerHasHighScore_thenReturnIsPlayerWin() {
        // given
        final Participants participants = Participants.create(Players.create(List.of("pobi")));
        final GameManager gameManager = GameManager.create(createPlayerHighScoreShuffler(), participants);
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();
        players.forEach(player -> player.bet(ParticipantMoney.create(10000)));

        gameManager.dealingFirstTurn();

        // when
        IS_PLAYER_WIN
                .getReferee()
                .accept(dealer, players.get(0));

        // then
        Assertions.assertThat(dealer.getMoney())
                .isEqualTo(-10000);
        Assertions.assertThat(players.get(0).getMoney())
                .isEqualTo(10000);
    }
}
