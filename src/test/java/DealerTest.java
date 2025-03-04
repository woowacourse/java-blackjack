import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import constant.Emblem;
import domain.Card;
import domain.Dealer;
import domain.Deck;

public class DealerTest {
    @Nested
    @DisplayName("")
    class pickCard {
        @Test
        @DisplayName("Dealer는 카드를 뽑는다.")
        void test_pickCard() {
            //given
            Card card = new Card(1, Emblem.CLUB);
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
