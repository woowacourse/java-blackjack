package domain.participant;

import domain.game.BettingMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantMoneyTest {

    private Dealer dealer;
    private Map<Player, BettingMoney> bettingInfo;

    @BeforeEach
    void init() {
        dealer = Dealer.createDealer();
        bettingInfo = Map.ofEntries(Map.entry(Player.create("pobi"), BettingMoney.create("10000")),
                Map.entry(Player.create("crong"), BettingMoney.create("20000")));
    }

    @Test
    @DisplayName("create()는 호출하면, 참가자들의 베팅 금액 정보를 생성한다")
    void create_whenCall_thenSuccess() {
        final ParticipantMoney participantMoney = assertDoesNotThrow(() -> ParticipantMoney.create(dealer, bettingInfo));
        assertThat(participantMoney).isExactlyInstanceOf(ParticipantMoney.class);
    }
}
