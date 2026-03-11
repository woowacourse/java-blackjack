package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestUtil.createDealer;
import static util.TestUtil.createPlayer;

import domain.Dealer;
import domain.Player;
import domain.WinningStatus;
import domain.card.Rank;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

class FinalResultDtoTest {

    @Test
    void 이름과_WinningStatus로_FinalResultDto를_생성한다() {
        // given, when
        SortedMap<String, WinningStatus> map = new TreeMap<>();
        map.put("봉구스", WinningStatus.WIN);
        map.put("시오", WinningStatus.LOSE);

        FinalResultDto finalResultDto = FinalResultDto.from(map);
        // then
        assertEquals(1, finalResultDto.dealerWinCount());
        assertEquals(1, finalResultDto.dealerLoseCount());
        assertEquals(Map.of("봉구스", "승", "시오", "패"), finalResultDto.playerResults());
    }

    @Test
    void 딜러와_플레이어로_FinalResultDto를_생성한다() {
        // given
        Dealer dealer = createDealer(Rank.JACK);
        List<Player> players = List.of(
                createPlayer("봉구스", Rank.FIVE),
                createPlayer("시오", Rank.JACK),
                createPlayer("영기", Rank.ACE));
        FinalResultDto finalResultDto = FinalResultDto.of(dealer, players);

        // when, then
        assertEquals(1, finalResultDto.dealerWinCount());
        assertEquals(1, finalResultDto.dealerLoseCount());
        assertEquals(Map.of("봉구스", "패", "시오", "무", "영기", "승"), finalResultDto.playerResults());
    }
}
