package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 카드_합이_21이_넘는_경우_카드를_더_받지_못한다() {
        //given
        Player player = new Player("pobi", new Cards(
                Arrays.asList(
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.TWO)
                )
        ));

        Card card = new Card(Suit.HEART, Rank.TWO);

        //when & then
        assertThatThrownBy(() -> player.additionalTake(card))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
    }

    @Test
    void 플레이어는_추가로_카드를_받을_수_있다() {
        //given
        Player player = new Player(
                "pobi",
                new Cards(
                        new ArrayList<>(Arrays.asList(
                                new Card(Suit.DIAMOND, Rank.ACE),
                                new Card(Suit.DIAMOND, Rank.TWO)))
                ));

        Card card = new Card(Suit.HEART, Rank.THREE);

        //when
        player.additionalTake(card);

        //then
        assertThat(player.getCards().getCards()).isEqualTo(
                Arrays.asList(
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.TWO),
                        new Card(Suit.HEART, Rank.THREE))
        );
    }
}
