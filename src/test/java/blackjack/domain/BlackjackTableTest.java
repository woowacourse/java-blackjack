package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.human.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class BlackjackTableTest {
    private final Players players = Players.fromText("pobi");
    private BlackjackTable blackjackTable;
    
    @BeforeEach
    void 테이블생성() {
        blackjackTable = BlackjackTable.from(players);
    }
    
    @Test
    void 테이블_초기_카드나눠주기() {
        blackjackTable.initCard();
        assertAll(
                () -> assertThat(blackjackTable.getPlayers().get().size()).isEqualTo(1),
                () -> assertThat(blackjackTable.getPlayers().get().get(0).getCards().size()).isEqualTo(2),
                () -> assertThat(blackjackTable.getDealer().getCards().size()).isEqualTo(2)
        );
    }
    
    @Test
    void 테이블_플레이어들_getPlayers() {
        assertThat(blackjackTable.getPlayers()).isEqualTo(players);
    }
}
