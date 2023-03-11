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
import static domain.game.GameReferee.IS_DEALER_WIN;
import static domain.game.GameReferee.IS_PLAYER_WIN;
import static domain.helper.MockCardShufflerHelper.createBustShuffler;
import static domain.helper.MockCardShufflerHelper.createDealerBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createDealerHighScoreShuffler;
import static domain.helper.MockCardShufflerHelper.createPlayerBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createPlayerHighScoreShuffler;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameRefereeTest {

    @Test
    @DisplayName("FIRST_TURN_DEALER_WIN은 호출하면 첫 턴에 딜러가 승리한지 확인한다.")
    void FIRST_TURN_DEALER_WIN_whenCall_thenReturnIsDealerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(participantInfo, createDealerBlackJackShuffler());
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = FIRST_TURN_DEALER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }

    private LinkedHashMap<Participant, ParticipantMoney> makeParticipantInfo(final Participant player,
                                                                             final Participant dealer) {
        return new LinkedHashMap<>(Map.ofEntries(
                Map.entry(dealer, ParticipantMoney.zero()),
                Map.entry(player, ParticipantMoney.create(10000))
        ));
    }

    @Test
    @DisplayName("FIRST_TURN_PLAYER_WIN은 호출하면 첫 턴에 플레이어가 승리한지 확인한다.")
    void FIRST_TURN_PLAYER_WIN_whenCall_thenReturnIsPlayerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(participantInfo, createPlayerBlackJackShuffler());
        gameManager.handFirstCards();

        // when
        boolean isPlayerWin = FIRST_TURN_PLAYER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isPlayerWin)
                .isTrue();
    }

    @Test
    @DisplayName("IS_DEALER_WIN은 호출하면, 딜러와 플레이어가 둘 다 버스트일 때 딜러가 승리한다고 판단한다.")
    void IS_DEALER_WIN_whenBothBust_thenReturnIsDealerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(participantInfo, createBustShuffler());
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
    @DisplayName("IS_DEALER_WIN은 호출하면, 딜러가 버스트가 아니면서, 더 높은 점수를 가지고 있다면 딜러가 승리한다고 판단한다.")
    void IS_DEALER_WIN_whenDealerHasHighScore_thenReturnIsDealerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(participantInfo, createDealerHighScoreShuffler());
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = IS_DEALER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }


    @Test
    @DisplayName("IS_DEALER_WIN은 호출하면, 딜러만 블랙잭이라면 딜러가 승리한다고 판단한다.")
    void IS_DEALER_WIN_whenOnlyDealerBlackjack_thenReturnIsDealerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(participantInfo, createDealerBlackJackShuffler());
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = IS_DEALER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }

    @Test
    @DisplayName("IS_PLAYER_WIN은 호출하면, 플레이어만 블랙잭이면 플레이어가 승리한다고 판단한다.")
    void IS_PLAYER_WIN_whenOnlyPlayerBlackjack_thenReturnIsPlayerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(participantInfo, createPlayerBlackJackShuffler());
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = IS_PLAYER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }

    @Test
    @DisplayName("IS_PLAYER_WIN은 호출하면, 플레이어가 더 높은 점수를 가지고 있다면 플레이어가 승리한다고 판단한다.")
    void IS_PLAYER_WIN_whenPlayerHasHighScore_thenReturnIsPlayerWin() {
        // given
        final Participant player = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo = makeParticipantInfo(player, dealer);
        final GameManager gameManager = GameManager.create(participantInfo, createPlayerHighScoreShuffler());
        gameManager.handFirstCards();

        // when
        boolean isDealerWin = IS_PLAYER_WIN.getReferee().test(dealer, player);

        // then
        assertThat(isDealerWin)
                .isTrue();
    }
}
