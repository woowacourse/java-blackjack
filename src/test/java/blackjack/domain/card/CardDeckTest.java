package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CardDeckTest {
    private List<Card> cards;

    @BeforeEach
    void setUp() {
        cards = new LinkedList<>();
        for (CardLetter letter : CardLetter.values()) {
            Arrays.stream(CardSuit.values())
                    .map(suit -> new Card(letter, suit))
                    .forEach(cards::add);
        }
    }

    @Test
    @DisplayName("카드덱이 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new CardDeck(cards))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드덱의 사이즈가 52인지 확인")
    void size() {
        final CardDeck cardDeck = new CardDeck(cards);
        assertThat(cardDeck.getCards()).hasSize(52);
    }

    @Test
    @DisplayName("카드의 리스트가 52장이 아니면 에러를 발생시킴")
    void sizeValidation() {
        assertThatThrownBy(() -> new CardDeck(Arrays.asList(new Card(CardLetter.FIVE, CardSuit.HEART))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("블랙잭을 진행하기 위해서는 52장의 카드가 필요합니다.");
    }

    @Test
    @DisplayName("카드덱이 정상적으로 카드를 분배하는지 확인")
    void distribute() {
        final CardDeck cardDeck = new CardDeck(cards);
        assertThat(cardDeck.distribute()).isEqualTo(new Card(CardLetter.ACE, CardSuit.HEART));
        assertThat(cardDeck.getCards()).hasSize(51);
    }
}
