import static org.assertj.core.api.Assertions.assertThat;

import constant.CardNumber;
import constant.Emblem;
import domain.Card;
import domain.Dealer;
import domain.Deck;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Nested
    @DisplayName("")
    class pickCard {
        @Test
        @DisplayName("Dealer는 카드를 뽑는다.")
        void test_pickCard() {
            //given
            Card card = new Card(CardNumber.TWO, Emblem.CLUB);
            final var d = new ArrayDeque<>(List.of(card));
            final var deck = new Deck(d);
            var dealer = new Dealer();

            //when
            dealer.pickUpCard(deck);

            //then
            assertThat(dealer.hand()).contains(card);
        }
    }
}
