package blackjack.domain;

import blackjack.domian.Card;
import blackjack.domian.Player;
import blackjack.domian.Rank;
import blackjack.domian.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 카드_합이_21이_넘는_경우_카드를_더_받지_못한다() {
        //given
        Player player = new Player(new ArrayList<>(Arrays.asList(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.TWO)
        )));

        Card card = new Card(Suit.HEART, Rank.TWO);

        //when & then
        Assertions.assertThatThrownBy(() -> player.send(card))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
    }

    @DisplayName("")
    @Test
    void 플레이어는_추가로_카드를_받을_수_있다() {
        //given
        Player player = new Player(
                new ArrayList<>(
                        Arrays.asList(
                                new Card(Suit.DIAMOND, Rank.ACE),
                                new Card(Suit.DIAMOND, Rank.TWO)
                        )));

        Card card = new Card(Suit.HEART, Rank.THREE);

        //when
        player.send(card);

        //then
        Assertions.assertThat(player.getCards()).hasSize(3);
        Assertions.assertThat(player.getCards()).isEqualTo(
                Arrays.asList(
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.TWO),
                        new Card(Suit.HEART, Rank.THREE))
        );
    }
}
