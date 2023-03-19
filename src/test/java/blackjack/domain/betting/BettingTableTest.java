package blackjack.domain.betting;

import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class BettingTableTest {
    private Participant pobi;
    private Betting betting;
    private Map<Participant, Betting> table;

    @BeforeEach
    void setUp() {
        pobi = Player.of(Name.from("pobi"), new ArrayList<>());
        betting = Betting.from(10000);
        table = new HashMap<>();
    }

    @Test
    @DisplayName("BettingTable 생성 테스트")
    void createBettingTable() {
        table.put(pobi, betting);

        assertThatNoException().isThrownBy(() -> BettingTable.from(table));
    }

    @Test
    @DisplayName("BettingTable에 플레이의 베팅을 추가하다")
    void addBetting() {
        // given
        BettingTable bettingTable = BettingTable.from(table);

        // when
        bettingTable.addBetting(pobi, betting);

        // then
        assertThat(bettingTable.getTable().get(pobi)).isEqualTo(betting);
    }

    @Test
    @DisplayName("BettingTable를 getter로 얻을 때 불변이어야 한다")
    void throwExceptionWhenGetBettingTable() {
        // given
        table.put(pobi, betting);

        // when
        BettingTable bettingTable = BettingTable.from(table);


        // then
        assertThatThrownBy(() -> bettingTable.getTable().put(pobi, betting))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
