package blackjack.model.user;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어 핸드의 합계가 21 미만인 경우 카드 추가 지급 가능 (true 반환)")
    void isHitAvailable_return_true() {
        //given
        Player player = new Player("pobi");
        player.addCard(new Card(Rank.K, Suit.DIAMOND));
        player.addCard(new Card(Rank.J, Suit.DIAMOND));

        //when & then
        Assertions.assertThat(player.isHitAvailable()).isTrue();
    }

    @Test
    @DisplayName("플레이어 핸드의 합계가 21 이상인 경우 카드 추가 지급 불가 (false 반환)")
    void isHitAvailable_return_false() {
        //given
        Player player = new Player("pobi");
        player.addCard(new Card(Rank.K, Suit.DIAMOND));
        player.addCard(new Card(Rank.ACE, Suit.DIAMOND));

        //when & then
        Assertions.assertThat(player.isHitAvailable()).isFalse();
    }
}