package domain.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantInfoTest {

    private ParticipantInfo participantInfo;
    private Map<Participant, ParticipantMoney> playerInfo;

    @BeforeEach
    void init() {
        playerInfo = new LinkedHashMap<>(Map.ofEntries(
                Map.entry(Participant.createDealer(), ParticipantMoney.zero()),
                Map.entry(Player.create("pobi"), ParticipantMoney.create("10000")),
                Map.entry(Player.create("crong"), ParticipantMoney.create("20000"))));
        participantInfo = ParticipantInfo.create(playerInfo);
    }

    @Test
    @DisplayName("create()는 호출하면, 참가자별 베팅 금액 정보를 생성한다")
    void create_whenCall_thenSuccess() {
        final ParticipantInfo participantInfo = assertDoesNotThrow(() -> ParticipantInfo.create(playerInfo));
        assertThat(participantInfo).isExactlyInstanceOf(ParticipantInfo.class);
    }

    @Test
    @DisplayName("findDealerInfo()는 호출하면, 딜러를 반환한다.")
    void findDealerInfo_whenCall_thenReturnDealer() {
        // given
        Participant expected = Participant.createDealer();

        // when
        Participant actual = participantInfo.findDealerInfo();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    private Map<Participant, ParticipantMoney> makePlayerBettingInfo() {
        final Map<Participant, ParticipantMoney> expected = new LinkedHashMap<>();
        expected.put(Player.create("pobi"), ParticipantMoney.create("10000"));
        expected.put(Player.create("crong"), ParticipantMoney.create("20000"));
        return expected;
    }
}
