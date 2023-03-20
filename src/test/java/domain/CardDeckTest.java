package domain;

import domain.card.Card;
import domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    @Test
    @DisplayName("카드덱을 생성하면 카드 타입과 값 별로 52장의 카드가 만들어진다")
    void generateCardDeck() {

        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.getCardDeck().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("pick 메서드를 실행하면 카드덱 스택이 섞인 후 맨 앞의 카드가 뽑힌다")
    void pickCard() {

        CardDeck cardDeck = new CardDeck();

        Card poppedCard = cardDeck.pickCard();
        Card firstCardInDeck = cardDeck.getCardDeck().get(0);

        assertThat(poppedCard).isSameAs(firstCardInDeck);
    }
}
