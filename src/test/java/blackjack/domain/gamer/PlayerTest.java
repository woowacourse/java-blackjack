package blackjack.domain.gamer;

import static blackjack.CardConstant.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("딜러와 카드 점수 결과 반환")
    void match() {
        Dealer dealer = new Dealer();
        dealer.addCard(CLOVER_THREE);
        dealer.addCard(CLOVER_NINE);
        dealer.addCard(CLOVER_EIGHT);

        Player pobi = new Player("pobi", 10);
        pobi.addCard(CLOVER_TWO);
        pobi.addCard(CLOVER_EIGHT);
        pobi.addCard(CLOVER_ACE);

        assertThat(pobi.match(dealer)).isEqualTo(10);
    }

    @Test
    @DisplayName("플레이어와 이름이 같다면 True를 반환한다.")
    void isSameName() {
        Player player1 = new Player("더즈", 10);
        assertThat(player1.isSameName("더즈")).isTrue();
    }

    @Test
    @DisplayName("플레이어는 카드 번호합 22 이상이면 더 뽑을 수 없다.")
    void dealerDrawable() {
        Player player = new Player("does", 1000);
        player.addCard(CLOVER_TEN);
        player.addCard(CLOVER_TEN);
        player.addCard(CLOVER_TWO);

        assertThat(player.isDrawable()).isFalse();
    }
}