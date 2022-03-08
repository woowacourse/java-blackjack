package blackjack.domain.human;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.Card;
import blackjack.domain.Denomination;
import blackjack.domain.Symbol;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class DealerTest {

    @Test
    public void 참여자에_카드_추가() {
        Dealer dealer = Dealer.of();

        Card card5 = Card.of(Denomination.of("5"), Symbol.of("스페이드"));
        Card card6 = Card.of(Denomination.of("6"), Symbol.of("하트"));

        dealer.addCard(card5);
        dealer.addCard(card6);

        assertThat(dealer.getPoint())
                .isEqualTo(11);
    }


}