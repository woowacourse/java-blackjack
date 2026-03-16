package domain.participant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {
    private final Hand hand = Hand.of(List.of(
            Card.of(Suit.CLUB, Rank.K),
            Card.of(Suit.CLUB, Rank.ACE),
            Card.of(Suit.CLUB, Rank.NINE)));

    @Test
    void 정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> Dealer.createReady(Hand.of(List.of())));
    }

    @Test
    void 손패_중_첫번째_카드만_반환해야_한다() {
        Dealer dealer = Dealer.createReady(hand);

        Card firstCard = dealer.getFirstCard();

        Assertions.assertThat(firstCard).isEqualTo(Card.of(Suit.CLUB, Rank.K));
    }
}
