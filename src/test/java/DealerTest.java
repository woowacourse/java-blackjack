import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Dealer;
import domain.participant.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DealerTest {

    Hand dummyHand;
    String name;

    @BeforeEach
    void init() {
        dummyHand = new Hand();
        name = "딜러";
    }

    @Test
    void 핸드가_16_이하면_카드를_뽑는다() {
        Card card = new Card(Rank.FOUR, Pattern.CLOVER);
        Dealer dealer = new Dealer();
        int beforeSize = dealer.handSize();

        dealer.keepCard(card);

        int afterSize = dealer.handSize();

        assertThat(beforeSize + 1).isEqualTo(afterSize);
    }

    @Test
    void 핸드가_17_이상이면_카드를_뽑지_않는다() {
        Card card1 = new Card(Rank.JACK, Pattern.CLOVER);
        Card card2 = new Card(Rank.QUEEN, Pattern.CLOVER);
        Card card3 = new Card(Rank.KING, Pattern.CLOVER);
        Dealer dealer = new Dealer();
        dealer.keepCard(card1);
        dealer.keepCard(card2);
        int beforeSize = dealer.handSize();

        dealer.keepCard(card3);
        int afterSize = dealer.handSize();

        assertThat(beforeSize).isEqualTo(afterSize);
    }
}
