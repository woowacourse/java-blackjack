package domain;

import static domain.participant.ParticipantFactory.createDealerCardsOfRanks;
import static domain.participant.ParticipantFactory.createPlayerCardsOfRanks;
import static domain.participant.ParticipantFactory.createRanks;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Rank;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BettingsTest {

    private Bettings bettings;

    @BeforeEach
    void setUp() {
        bettings = new Bettings();
        bettings.registerBetting("행성", 1000);
        bettings.registerBetting("토성", 2000);
        bettings.registerBetting("유성", 3000);
    }

    @Test
    @DisplayName("베팅 금액 검색 기능 테스트")
    void bettingsFindTest() {
        // given & when & then
        assertAll(
                () -> assertEquals(1000, bettings.findBettingOfPlayer("행성")),
                () -> assertEquals(2000, bettings.findBettingOfPlayer("토성")),
                () -> assertEquals(3000, bettings.findBettingOfPlayer("유성"))
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"KING,JACK,QUEEN:KING,JACK:1.0", "KING,ACE:JACK,ACE:0.0", "KING:JACK,ACE:1.5", "KING:KING,JACK,QUEEN:-1.0"}, delimiterString = ":")
    @DisplayName("베팅 금액 업데이트 기능 테스트")
    void bettingsUpdateTest(String dealerRankNames, String playerRankNames, double profitRate) {
        // given
        List<Rank> dealerRanks = createRanks(dealerRankNames);
        Dealer dealer = createDealerCardsOfRanks(dealerRanks);
        List<Rank> playerRanks = createRanks(playerRankNames);
        Player player = createPlayerCardsOfRanks(playerRanks);
        int betting = (int) (bettings.findBettingOfPlayer(player.getName()) * profitRate);
        // when
        bettings.updateBetting(dealer, player);
        // then
        assertEquals(betting, bettings.findBettingOfPlayer(player.getName()));
    }

    @Test
    @DisplayName("딜러 베팅 금액 계산 기능 테스트")
    void dealerBettingTest() {
        // given & when & then
        assertEquals(-6000, bettings.calculateDealerBetting());
    }
}
