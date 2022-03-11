package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("객체가 생성되면 52장의 카드를 갖는다.")
    void initDeck() {
        CardDeck cardDeck = CardDeck.create();

        assertThatCode(() -> {
            for (int i = 0; i < 52; i++) {
                cardDeck.drawCard();
            }
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 한 장 만들어서 반환한다.")
    void createCard() {
        // give
        CardDeck cardDeck = CardDeck.createNoShuffle();

        // when
        Card card = cardDeck.drawCard();

        // then
        assertThat(card).isInstanceOf(Card.class);
    }
}
