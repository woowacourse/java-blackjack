package blackjack.domain;

import blackjack.domian.Card;
import blackjack.domian.Player;
import blackjack.domian.Rank;
import blackjack.domian.Suit;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 카드_합이_21이_넘는_경우_카드를_더_받지_못한다() {
        //given
        Player player = new Player(Arrays.asList(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.TWO)
        ));

        Card card = new Card(Suit.HEART, Rank.TWO);

        //when & then
        Assertions.assertThatThrownBy(() -> player.send(card))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
    }
}
