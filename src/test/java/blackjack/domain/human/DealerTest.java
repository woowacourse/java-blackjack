package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class DealerTest {

    @Test
    public void 참여자에_카드_추가() {
        Dealer dealer = Dealer.of();

        Card card5 = Card.of(Denomination.of("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.of("6"), Suit.SPADE);

        dealer.addCard(card5);
        dealer.addCard(card6);

        assertThat(dealer.getPoint())
                .isEqualTo(11);
    }


}