package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Name;

public class BettingTableTest {

    @Test
    @DisplayName("딜러의 수익을 반환한다.")
    void dealerRevenue() {
        //given
        BettingTable table = new BettingTable(List.of(new Betting(Name.of("pobi"), 10000),
            new Betting(Name.of("jason"), 20000)));

        //when
        long actual = table.getRevenues(Map.of(Name.of("pobi"), PlayRecord.WIN,
            Name.of("jason"), PlayRecord.LOSS)).get(Name.of("딜러"));
        //then
        assertThat(actual).isEqualTo(10000);
    }
}
