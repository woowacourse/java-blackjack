package domain.user;

import static domain.card.Number.FIVE;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Number.TEN;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.Deck;
import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("합이 16 이하이면 카드를 추가한다.")
    void playTest() {
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, TEN)));
        Deck deck = new Deck(List.of(new Card(SPADE, SEVEN), new Card(SPADE, SIX), new Card(SPADE, FIVE)));

        dealer.play(deck);

        assertThat(dealer.getHand().getCards()).hasSize(3);
    }
}
