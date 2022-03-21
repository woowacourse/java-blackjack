package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 처음에 맨 앞 한 장만 보여준다.")
    void showOnlyOneCard() {
        // given
        Card card1 = Card.of(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = Card.of(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        Dealer dealer = new Dealer(cards);

        // when
        List<Card> actual = dealer.showInitialCards();

        // then
        assertThat(actual).containsOnly(card1);
    }
}
