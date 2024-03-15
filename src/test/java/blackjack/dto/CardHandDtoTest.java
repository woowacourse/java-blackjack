package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardHandDtoTest {

    @Test
    void dto로_변환할_수_있다() {
        final CardHand cardHand = new CardHand();
        cardHand.receiveCard(new Card(Suit.HEART, Denomination.ACE));
        cardHand.receiveCard(new Card(Suit.DIAMOND, Denomination.TEN));

        final CardHandDto cardHandDto = CardHandDto.from(cardHand);

        assertThat(cardHandDto.cards()).hasSize(2);
    }
}
