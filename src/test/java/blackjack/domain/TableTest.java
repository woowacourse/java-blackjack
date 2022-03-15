package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class TableTest {
    private final Player player1 = Player.of("pobi");
    private final Players players = Players.of(List.of(player1));
    private Table table;
    
    @BeforeEach
    void 테이블생성() {
        table = Table.of(players);
    }
    
    @Test
    void 테이블_초기_카드나눠주기() {
        table.initCard();
        assertThat(table.getPlayers().get().size() == 1 &&
                table.getPlayers().get().get(0).getCards().size() == 2 &&
                table.getDealer().getCards().size() == 2)
                .isTrue();
    }
    
    @Test
    void 테이블_플레이어들_getPlayers() {
        assertThat(table.getPlayers()).isEqualTo(players);
    }
}
