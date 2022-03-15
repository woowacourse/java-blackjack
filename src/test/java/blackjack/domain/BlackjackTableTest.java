package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.human.group.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class BlackjackTableTest {
    private final Players players = Players.fromText("pobi");
    private BlackjackTable blackjackTable;
    
    @BeforeEach
    void setup() {
        blackjackTable = BlackjackTable.from(players);
    }
    
    @Test
    @DisplayName("테이블 초기 카드 나눠주기 기능 검사")
    void initCardTest() {
        blackjackTable.initCard();
        assertAll(
                () -> assertThat(blackjackTable.getPlayers().get().size()).isEqualTo(1),
                () -> assertThat(blackjackTable.getPlayers().get().get(0).getCards().size()).isEqualTo(2),
                () -> assertThat(blackjackTable.getDealer().getCards().size()).isEqualTo(2)
        );
    }
    
    @Test
    @DisplayName("테이블 getPlayers() 기능 검사")
    void etPlayersTest() {
        assertThat(blackjackTable.getPlayers()).isEqualTo(players);
    }
}
