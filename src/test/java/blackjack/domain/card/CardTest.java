package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void 같은_카드를_비교할_수_있다() {
        //given
        Card card1 = new Card(Suit.CLUB, Rank.EIGHT);
        Card card2 = new Card(Suit.CLUB, Rank.EIGHT);

        //when & then
        Assertions.assertThat(card1).isEqualTo(card2);
    }
}
