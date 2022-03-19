package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Name;

public class StateDealerTest {

    @Test
    @DisplayName("딜러의 수익을 반환한다.")
    void dealerRevenue() {
        //given
        Dealer dealer = new Dealer(List.of(new Betting(Name.of("pobi"), 10000),
            new Betting(Name.of("jason"), 20000)));

        //when
        long actual = dealer.getRevenues(Map.of(Name.of("pobi"), PlayRecord.WIN,
            Name.of("jason"), PlayRecord.LOSS)).get(Name.of("딜러"));
        //then
        assertThat(actual).isEqualTo(10000);
    }
}
