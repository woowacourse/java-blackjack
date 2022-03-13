package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 덱에서 카드를 뽑는다.")
    void getCard() {
        CardDeck cardDeck = new CardDeck();
        Assertions.assertThat(cardDeck.getCard()).isInstanceOf(Card.class);
    }
}
