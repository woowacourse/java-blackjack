package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("카드 뭉치에서 카드 한 장을 꺼낸다.")
    void getCard() {
        Deck deck = Deck.of(Card.createDeck());

        assertThat(deck.draw()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 뭉치의 카드가 52장인지 확인한다.")
    void draw52() {
        Deck deck = Deck.of(Card.createDeck());
        assertThatCode(() -> {
            for (int i = 0; i < 52; i++) {
                deck.draw();
            }
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 뭉치가 비어있을때 드로우를 하면 예외를 발생한다.")
    void throwExceptionDrawEmptyCard() {
        Deck deck = Deck.of(Card.createDeck());
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatIllegalArgumentException()
                .isThrownBy(deck::draw)
                .withMessage("더 이상 꺼낼 카드가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("카드가 중복될 경우 예외를 발생한다.")
    void throwExceptionDuplicateCard() {
        List<Card> cards = Card.createDeck();
        cards.add(Card.valueOf(Suit.SPADE, Number.ACE));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Deck.of(cards))
                .withMessage("카드는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("52장이 아닐경우 예외를 발생한다.")
    void throwExceptionCardSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Deck.of(Collections.singletonList(Card.valueOf(Suit.SPADE, Number.ACE))))
                .withMessage("카드는 52장으로 생성되어야 합니다.");
    }

}