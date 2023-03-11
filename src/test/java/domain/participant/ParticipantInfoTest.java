package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.helper.ParticipantTestHelper.makeOneParticipantInfo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantInfoTest {

    @Test
    @DisplayName("create()는 호출하면, 참가자별 베팅 금액 정보를 생성한다")
    void create_whenCall_thenSuccess() {
        final ParticipantInfo participantInfo = assertDoesNotThrow(() ->
                ParticipantInfo.create(makeOneParticipantInfo(Player.create("pobi"), Participant.createDealer())));
        assertThat(participantInfo).isExactlyInstanceOf(ParticipantInfo.class);
    }

    @Test
    @DisplayName("findDealerInfo()는 호출하면, 딜러를 반환한다.")
    void findDealerInfo_whenCall_thenReturnDealer() {
        // given
        final Participant expected = Participant.createDealer();
        final ParticipantInfo participantInfo =
                ParticipantInfo.create(makeOneParticipantInfo(Player.create("pobi"), expected));

        // when
        Participant actual = participantInfo.findDealerInfo();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("findPlayerInfo()는 호출하면, 플레이어를 반환한다.")
    void findPlayerInfo_whenCall_thenReturnDealer() {
        // given
        final Player expected = Player.create("pobi");
        final ParticipantInfo participantInfo =
                ParticipantInfo.create(makeOneParticipantInfo(expected, Participant.createDealer()));

        // when
        List<Participant> actual = participantInfo.findPlayerInfo();

        // then
        assertThat(actual)
                .isEqualTo(List.of(expected));
    }

    @Test
    @DisplayName("losePlayerMoney()는 주어진 플레이어의 돈을 잃게 하고, 딜러의 돈으로 추가한다.")
    void losePlayerMoney_givenPlayer_thenLosePlayerMoney() {
        // given
        final Player targetPlayer = Player.create("pobi");
        final ParticipantInfo participantInfo =
                ParticipantInfo.create(makeOneParticipantInfo(targetPlayer, Participant.createDealer()));

        // when
        participantInfo.losePlayerMoney(targetPlayer);

        // then
        final Map<Participant, ParticipantMoney> allParticipantInfo = participantInfo.getParticipantInfo();
        final ParticipantMoney playerMoney = allParticipantInfo.get(targetPlayer);
        final ParticipantMoney dealerMoney = allParticipantInfo.get(Participant.createDealer());

        assertThat(playerMoney)
                .isEqualTo(ParticipantMoney.create(-10000));

        assertThat(dealerMoney)
                .isEqualTo(ParticipantMoney.create(10000));
    }

    @Test
    @DisplayName("earnPlayerMoney()는 플레이어의 돈을 그대로 유지한다")
    void earnPlayerMoney_givenPlayer_thenBonusPlayerMoney() {
        // given
        final Player targetPlayer = Player.create("pobi");
        final ParticipantInfo participantInfo =
                ParticipantInfo.create(makeOneParticipantInfo(targetPlayer, Participant.createDealer()));

        // when
        participantInfo.earnPlayerMoney(targetPlayer);

        // then
        final Map<Participant, ParticipantMoney> allParticipantInfo = participantInfo.getParticipantInfo();
        final ParticipantMoney playerMoney = allParticipantInfo.get(targetPlayer);
        final ParticipantMoney dealerMoney = allParticipantInfo.get(Participant.createDealer());

        assertThat(playerMoney)
                .isEqualTo(ParticipantMoney.create(10000));
    }

    @Test
    @DisplayName("earnPlayerBonusMoney()는 플레이어에게 보너스를 주고, 그만큼 딜러의 돈에서 제거한다.")
    void earnPlayerBonusMoney_givenPlayer_thenBonusPlayerMoney() {
        // given
        final Player targetPlayer = Player.create("pobi");
        final ParticipantInfo participantInfo =
                ParticipantInfo.create(makeOneParticipantInfo(targetPlayer, Participant.createDealer()));

        // when
        participantInfo.earnPlayerBonusMoney(targetPlayer);

        // then
        final Map<Participant, ParticipantMoney> allParticipantInfo = participantInfo.getParticipantInfo();
        final ParticipantMoney playerMoney = allParticipantInfo.get(targetPlayer);
        final ParticipantMoney dealerMoney = allParticipantInfo.get(Participant.createDealer());

        assertThat(playerMoney)
                .isEqualTo(ParticipantMoney.create(15000));

        assertThat(dealerMoney)
                .isEqualTo(ParticipantMoney.create(-15000));
    }
}
