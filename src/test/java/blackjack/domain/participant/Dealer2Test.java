package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Dealer2Test {

    @DisplayName("한 장의 지급받은 카드를 공개한다.")
    @Test
    void testRevealHand() {
        // given
        Dealer2 dealer = new Dealer2(new Hand());

        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();
        dealer.deal(cardDeck);

        // when
        List<Card> cards = dealer.revealHand();

        // then
        assertThat(cards).hasSize(1);
    }
}
