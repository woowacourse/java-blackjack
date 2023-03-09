package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import domain.card.RandomShuffleStrategy;

class DeckTest {

    @Test
    @DisplayName("정적 팩토리 메서드에 shuffle 전략을 이용해 deck 생성 시 정상적으로 동작한다.")
    void DeckFromTest() {
        Assertions.assertThatCode(() -> Deck.from(new RandomShuffleStrategy()))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 덱에서 한 장을 뽑는다.")
    void DeckDrawTest() {
        Deck deck = Deck.from((orderedDeck) -> orderedDeck);
        Assertions.assertThat(deck.draw()).isEqualTo(Card.from(Suit.CLOVER, Rank.ACE));
    }
}
