package blackjack.domain.human;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.Card;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class DealerTest {

    @Test
    public void 참여자에_카드_추가() {
        Dealer dealer = Dealer.of();

        Card card5 = Card.of(5, "spade");
        Card card6 = Card.of(6, "heart");

        dealer.addCard(card5);
        dealer.addCard(card6);

        assertThat(dealer.getPoint())
                .isEqualTo(11);
    }
}