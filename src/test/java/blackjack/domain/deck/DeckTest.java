package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드 덱은 카드를 한장씩 반환한다")
    @Test
    void should_giveOneCard() {
        Deck shuffledDeck = new Deck(new ShuffledDeckCreateStrategy());
        assertThat(shuffledDeck.draw()).isInstanceOf(Card.class);
    }

    @DisplayName("덱의 카드가 없으면, 더이상 반환을 요청할 수 없다")
    @Test
    void should_ThrowIllegalArgumentException_When_NoMoreCard() {
        Deck shuffledDeck = new Deck(new DeckCreateStrategy() {
            @Override
            public Deque<Card> createDeck() {
                return new ArrayDeque<Card>();
            }
        });;

        assertThatThrownBy(shuffledDeck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 카드가 더이상 없습니다.");
    }
}
