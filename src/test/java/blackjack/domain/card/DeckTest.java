package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayDeque;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @DisplayName("52개의 카드를 가진 덱을 생성 한다.")
    public void Deck_Instance_create_one_pack() {

        assertThatCode(() -> {
            var sut = Deck.createPack();
            var size = IntStream.range(0, 52)
                                .mapToObj(i -> {
                                    return sut.draw();
                                })
                                .toList()
                                .size();
            assertThat(size).isEqualTo(52);
        });
    }

    @Test
    @DisplayName("카드를 위에서 부터 하나씩 뽑는다.")
    public void Deck_Draw_one_card() {
        ArrayDeque<Card> cards = new ArrayDeque<>();
        Card card1 = new Card(CardValue.ACE, CardSymbol.DIAMOND);
        Card card2 = new Card(CardValue.FOUR, CardSymbol.HEART);
        cards.add(card1);
        cards.add(card2);

        var sut = new Deck(cards);

        assertThat(sut.draw()).isEqualTo(card2);
        assertThat(sut.draw()).isEqualTo(card1);
    }
}
