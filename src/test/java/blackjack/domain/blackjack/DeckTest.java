package blackjack.domain.blackjack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

import blackjack.domain.blackjack.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DeckTest {
    @Test
    @DisplayName("52개의 카드를 가진 덱을 생성 한다.")
    public void create_one_pack_with_52_card() {

        assertThatCode(() -> {
            var sut = Deck.createPack();
            var size = IntStream.range(0, 52)
                                .mapToObj(i -> {
                                    return sut.draw();
                                })
                                .toList()
                                .size();
            assertThat(size).isEqualTo(52);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 위에서 부터 하나씩 뽑는다.")
    public void draw_one_card() {
        ArrayDeque<Card> cards = new ArrayDeque<>();
        Card card1 = new Card(CardValue.ACE, CardSymbol.DIAMOND);
        Card card2 = new Card(CardValue.FOUR, CardSymbol.HEART);
        cards.add(card1);
        cards.add(card2);

        var sut = new Deck(cards);

        assertThat(sut.draw()).isEqualTo(card2);
        assertThat(sut.draw()).isEqualTo(card1);
    }

    @Test
    @DisplayName("카드가 전부 소진되면 예외를 발생한다.")
    public void throw_exception_when_empty() {
        final Deque<Card> cards = new ArrayDeque<>();
        final Card card1 = new Card(CardValue.ACE, CardSymbol.DIAMOND);
        cards.add(card1);

        final var sut = new Deck(cards);
        sut.draw();

        assertThatThrownBy(() -> {
            sut.draw();
        }).isInstanceOf(IllegalStateException.class);
    }
}
