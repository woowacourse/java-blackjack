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
        Assertions.assertThat(cardDeck.getCard(1)).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드가 모두 소진 된 상태에서 카드를 뽑을 경우 예외를 발생시킨다.")
    void throwExceptionWhenOutOfIndex() {
        CardDeck cardDeck = new CardDeck();
        Assertions.assertThatThrownBy(() -> cardDeck.getCard(52))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("[ERROR] 카드 덱의 범위를 넘어갔습니다.");
    }
}
