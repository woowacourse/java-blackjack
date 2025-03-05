import static domain.card.Number.TWO;
import static domain.card.Shape.DIAMOND;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    @Test
    @DisplayName("카드 개수 테스트")
    void cardSizeTest() {
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();

        assertThat(cardDeck.getCardsSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드 한 장 뽑기 테스트")
    void hitCardTest() {
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();

        assertThat(cardDeck.hitCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardTest() {
        // given
        CardDeck cardDeck = new CardDeck(new ArrayList<>());
        Card card = new Card(DIAMOND, TWO);

        // when
        cardDeck.addCard(card);

        // then
        assertThat(cardDeck.getCards().size()).isEqualTo(1);

    }
}
