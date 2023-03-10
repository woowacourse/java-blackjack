package domain.participant;

import domain.game.BettingMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantMoneyTest {

    private Dealer dealer;
    private Map<Participant, BettingMoney> playerInfo;

    @BeforeEach
    void init() {
        dealer = Dealer.createDealer();
        playerInfo = new LinkedHashMap<>(Map.ofEntries(Map.entry(Player.create("pobi"), BettingMoney.create("10000")),
                Map.entry(Player.create("crong"), BettingMoney.create("20000"))));
    }

    @Test
    @DisplayName("create()는 호출하면, 참가자들의 베팅 금액 정보를 생성한다")
    void create_whenCall_thenSuccess() {
        final ParticipantMoney participantMoney = assertDoesNotThrow(() -> ParticipantMoney.create(dealer, playerInfo));
        assertThat(participantMoney).isExactlyInstanceOf(ParticipantMoney.class);
    }

    @Test
    @DisplayName("getPlayerMoney()는 호출하면, 플레이어의 베팅 금액 정보를 반환한다.")
    void getPlayerMoney_whenCall_thenReturnPlayerMoneyInfo() {
        // given
        final ParticipantMoney participantMoney = ParticipantMoney.create(dealer, playerInfo);
        final Map<Participant, BettingMoney> expected = makePlayerBettingInfo();

        // when
        final Map<Participant, BettingMoney> actual = participantMoney.getPlayerMoney();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    private Map<Participant, BettingMoney> makePlayerBettingInfo() {
        final Map<Participant, BettingMoney> expected = new LinkedHashMap<>();
        expected.put(Player.create("pobi"), BettingMoney.create("10000"));
        expected.put(Player.create("crong"), BettingMoney.create("20000"));
        return expected;
    }
}
