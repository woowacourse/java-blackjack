package blackjackgame.domain.card;

import static blackjackgame.domain.card.CardName.ACE;
import static blackjackgame.domain.card.CardName.TWO;
import static blackjackgame.domain.card.CardType.HEART;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("카드 배열로 덱 클래스의 생성자를 만들 수 있다.")
    void createDeckConstructorWithCardArrayTest() {
        List<Card> cards = List.of(new Card(ACE, HEART), new Card(TWO, HEART));
        Assertions.assertThatCode(() -> new Deck(cards))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복된 카드가 포함된 덱이 생성되지 않는지 확인한다.")
    void validateDuplicateCardTest() {
        Assertions.assertThatThrownBy(() -> new Deck(List.of(new Card(ACE, HEART), new Card(ACE, HEART))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되는 카드가 있습니다.");
    }

    @Test
    @DisplayName("카드가 잘 드로우되는지 확인한다.")
    void drawTest() {
        Deck deck = new Deck(List.of(new Card(ACE, HEART)));
        Card card = deck.draw(cards -> cards.get(0));
        Assertions.assertThat(card)
                .isEqualTo(new Card(ACE, HEART));
    }

    @Test
    @DisplayName("덱에 카드가 없으면 뽑지 못하는지 확인한다.")
    void validateNoCardTest() {
        Deck deck = new Deck(List.of(new Card(ACE, HEART)));
        deck.draw(cards -> cards.get(0));

        Assertions.assertThatThrownBy(() -> deck.draw(cards -> cards.get(0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱이 비어있습니다.");
    }
}
