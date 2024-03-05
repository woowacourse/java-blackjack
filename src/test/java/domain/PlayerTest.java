package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {


    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum() {
        final Player player = new Player();

        player.addCard(new Card(Denomination.FIVE, Symbol.CLOVER));
        player.addCard(new Card(Denomination.FIVE, Symbol.CLOVER));
        player.addCard(new Card(Denomination.ACE, Symbol.CLOVER));

        Assertions.assertThat(player.calculateScore()).isEqualTo(21);
    }
}
