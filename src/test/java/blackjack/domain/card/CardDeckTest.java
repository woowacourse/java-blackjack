package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 뭉치의 맨 위의 카드 한 장을 뽑아서 준다.")
    void drawCard() {
        // given
        Card firstCard = Card.of(Pattern.DIAMOND, Denomination.ACE);
        Card secondCard = Card.of(Pattern.DIAMOND, Denomination.TWO);
        CardDeck deck = new CardDeck(List.of(firstCard, secondCard));

        // when
        Card actual = deck.draw();

        // then
        assertThat(actual).isEqualTo(firstCard).isNotEqualTo(secondCard);
    }

    @Test
    @DisplayName("두 장의 카드를 한번에 뽑을 수 있다.")
    void drawDouble() {
        // given
        CardDeck deck = CardDeck.createGameDeck();

        // when
        List<Card> actual = deck.drawDouble();

        // then
        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드가 다 떨어지면 예외가 발생한다.")
    void createNewDeck() {
        // given
        CardDeck deck = new CardDeck(Collections.emptyList());

        // then
        assertThatThrownBy(deck::draw).isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 카드 덱이 비어 있습니다.");
    }
}
