package domain;

import java.util.List;
import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("뽑은 카드가 올바른 카드인지 확인하는 테스트")
    @Test
    void drawTest_drawnCardIsValid_SameCard() {
        String cardNumber = "10";
        String cardPattern = "하트";
        Card tempCard = new Card(cardNumber, cardPattern);
        List<Card> cards = List.of(tempCard);
        Deck deck = new FixedDeck(cards);

        String expectedCardName = cardNumber + cardPattern;

        Assertions.assertThat(deck.draw().cardName()).isEqualTo(expectedCardName);
    }

    @DisplayName("Deck이 비어있는데 카드를 뽑는 경우 예외 발생 테스트")
    @Test
    void drawTest_deckIsEmpty_ThrowException() {
        Deck deck = new FixedDeck(List.of());

        Assertions.assertThatThrownBy(deck::draw)
                .isInstanceOf(NoSuchElementException.class);
    }
}
