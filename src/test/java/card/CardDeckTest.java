package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("덱에서 카드를 뽑는 경우 해당하는 카드가 위에서 사라진다.")
    @Test
    void getTopTwoCard() {
        CardDeck cardDeck = new CardDeck();
        Card card = cardDeck.pickCard();

        Assertions.assertThat(cardDeck.firstCards().contains(card)).isFalse();
    }

    @DisplayName("카드를 처음 세팅하는 경우 두개의 카드를 세팅한다.")
    @Test
    void isSetTwoCards() {
        CardDeck cardDeck = new CardDeck();
        int initSize = cardDeck.getDeckSize();
        cardDeck.firstCards();

        Assertions.assertThat(cardDeck.getDeckSize()).isEqualTo(initSize - 2);
    }

    @DisplayName("카드덱은 처음 만들경우, 패턴 하나당 각 패턴의 숫자의 개수만큼 생성된다.")
    @Test
    void makeCardDeck() {
        CardDeck cardDeck = new CardDeck();

        Assertions.assertThat(cardDeck.getDeckSize())
                .isEqualTo(CardNumber.values().length * CardPattern.values().length);
    }
}
