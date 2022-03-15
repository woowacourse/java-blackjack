package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.human.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class BlackjackTableTest {
    private final Players players = Players.ofInputText("pobi");
    private BlackjackTable blackjackTable;
    
    @BeforeEach
    void 테이블생성() {
        blackjackTable = BlackjackTable.of(players);
    }
    
    @Test
    void 테이블_초기_카드나눠주기() {
        blackjackTable.initCard();
        assertThat(blackjackTable.getPlayers().get().size() == 1 &&
                blackjackTable.getPlayers().get().get(0).getCards().size() == 2 &&
                blackjackTable.getDealer().getCards().size() == 2)
                .isTrue();
    }
    
    @Test
    void 테이블_플레이어들_getPlayers() {
        assertThat(blackjackTable.getPlayers()).isEqualTo(players);
    }
}
