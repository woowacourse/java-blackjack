package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.machine.Blackjack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.dto.DealerRecordDto;
import blackjack.domain.dto.PlayerRecordDto;
import blackjack.domain.dto.RecordsDto;

public class RecordsTest {
    private RecordsDto dto;

    @BeforeEach
    void setUp() {
        Map<String, Long> playersInfo = new LinkedHashMap<>();
        playersInfo.put("범블비",1000L);
        playersInfo.put("잉", 2000L);

        Players players = new Players(playersInfo);
        Blackjack blackjack = new Blackjack();

        Dealer dealer = new Dealer();
        Player player1 = players.getPlayers().get(0);
        Player player2 = players.getPlayers().get(1);

        dealer.addCard(Card.FIVE_DIAMOND);
        dealer.addCard(Card.SEVEN_HEART);

        player1.addCard(Card.SEVEN_CLOVER);

        player2.addCard(Card.NINE_HEART);
        player2.addCard(Card.FIVE_SPADE);

        //dealer: 12점, player: 7점(범블비), 14점(잉)
        dto = blackjack.record(dealer, players);
    }

    @DisplayName("딜러 전적 테스트")
    @Test
    void dealerRecords() {
        DealerRecordDto dealerRecordDto = dto.getDealerResultDto();

        Map<String, Integer> actual = dealerRecordDto.getOutcome();

        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("승", 1);
        expected.put("무", 0);
        expected.put("패", 1);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이 전적 테스트")
    @Test
    void playersRecords() {
        PlayerRecordDto playerRecordDto = dto.getPlayerResultDto();

        Map<String, String> actual = playerRecordDto.getOutcome();

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("범블비", "패");
        expected.put("잉", "승");

        assertThat(actual).isEqualTo(expected);
    }
}
