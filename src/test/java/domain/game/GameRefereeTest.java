package domain.game;

import domain.participant.Participant;
import domain.participant.ParticipantMoney;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static domain.game.GameReferee.FIRST_TURN_DEALER_WIN;
import static domain.game.GameReferee.FIRST_TURN_PLAYER_WIN;
import static domain.game.GameReferee.IS_ALL_BLACKJACK;
import static domain.game.GameReferee.IS_DEALER_WIN;
import static domain.game.GameReferee.IS_PLAYER_WIN;
import static domain.helper.MockCardShufflerHelper.createAllBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createBustShuffler;
import static domain.helper.MockCardShufflerHelper.createDealerBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createDealerHighScoreShuffler;
import static domain.helper.MockCardShufflerHelper.createPlayerBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createPlayerHighScoreShuffler;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameRefereeTest {

    @Test
    @DisplayName("FIRST_TURN_DEALER_WIN는, 첫 턴에 딜러가 승리한지 확인한다.")
    void FIRST_TURN_DEALER_WIN_whenIsFirstTurnDealerWin_thenTrue() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(createDealerBlackJackShuffler(), participantInfo);
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = FIRST_TURN_DEALER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }

    @Test
    @DisplayName("FIRST_TURN_PLAYER_WIN는, 첫 턴에 플레이어가 승리한지 확인한다.")
    void FIRST_TURN_PLAYER_WIN_whenIsFirstTurnPlayerWin_thenReturnTrue() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(createPlayerBlackJackShuffler(), participantInfo);
        gameManager.handFirstCards();

        // when
        boolean isPlayerWin = FIRST_TURN_PLAYER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isPlayerWin)
                .isTrue();
    }

    @Test
    @DisplayName("IS_DEALER_WIN는 딜러와 플레이어가 둘 다 버스트일 때, 딜러가 승리한다고 판단한다.")
    void IS_DEALER_WIN_whenBothBust_thenReturnIsDealerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(createBustShuffler(), participantInfo);
        gameManager.handFirstCards();
        gameManager.handCard(player);
        gameManager.handCard(dealer);

        // when
        boolean isDealerWin = IS_DEALER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }

    @Test
    @DisplayName("IS_DEALER_WIN는 딜러가 버스트가 아니면서, 더 높은 점수를 가지고 있다면 딜러가 승리한다고 판단한다.")
    void IS_DEALER_WIN_whenDealerHasHighScore_thenReturnIsDealerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(createDealerHighScoreShuffler(), participantInfo);
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = IS_DEALER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }


    @Test
    @DisplayName("IS_DEALER_WIN는 딜러만 블랙잭이라면, 딜러가 승리한다고 판단한다.")
    void IS_DEALER_WIN_whenOnlyDealerBlackjack_thenReturnIsDealerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(createDealerBlackJackShuffler(), participantInfo);
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = IS_DEALER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }

    @Test
    @DisplayName("IS_PLAYER_WIN는 플레이어만 블랙잭이면, 플레이어가 승리한다고 판단한다.")
    void IS_PLAYER_WIN_whenOnlyPlayerBlackjack_thenReturnIsPlayerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(createPlayerBlackJackShuffler(), participantInfo);
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = IS_PLAYER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }

    @Test
    @DisplayName("IS_PLAYER_WIN는 플레이어가 더 높은 점수를 가지고 있다면, 플레이어가 승리한다고 판단한다.")
    void IS_PLAYER_WIN_whenPlayerHasHighScore_thenReturnIsPlayerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(createPlayerHighScoreShuffler(), participantInfo);
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = IS_PLAYER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }

    @Test
    @DisplayName("IS_ALL_BLACKJACK는 딜러와 플레이어가 모두 블랙잭인지 확인한다.")
    void IS_ALL_BLACKJACK_whenAllBlackJack_thenReturnTrue() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(createAllBlackJackShuffler(), participantInfo);
        gameManager.handFirstCards();

        // when
        boolean isAllBlackJack = IS_ALL_BLACKJACK.getReferee().test(dealer, player);

        // then
        assertThat(isAllBlackJack)
                .isTrue();
    }

    private Map<Participant, ParticipantMoney> makeParticipantInfo(final Participant player,
                                                                   final Participant dealer) {
        final Map<Participant, ParticipantMoney> participantInfo = new LinkedHashMap<>();
        participantInfo.put(dealer, ParticipantMoney.zero());
        participantInfo.put(player, ParticipantMoney.create(10000));
        return participantInfo;
    }
}
