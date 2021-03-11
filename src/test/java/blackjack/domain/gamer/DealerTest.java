package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("카드 지급")
    void takeCard() {
        Participant dealer = new Dealer();
        dealer.takeCard(Card.from(Suits.DIAMOND, Denominations.KING));

        assertThat(dealer.getUnmodifiableCards()).hasSize(1);
    }

    @Test
    @DisplayName("Dealer 16 초과한 경우")
    void isNotAbleToTake() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Participant dealer = new Dealer(new Cards(cards));

        assertThat(dealer.isNotAbleToTake()).isTrue();
    }

}
