package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Player2Test {

    @DisplayName("두 장의 지급받은 카드를 공개한다.")
    @Test
    void testRevealHand() {
        // given
        Player2 player = new Player2(new Name("pobi"), new Hand());

        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();
        player.deal(cardDeck);

        // when
        Hand hand = player.revealHand();

        // then
        assertThat(hand.getCards()).hasSize(2);
    }
}
