package blackjack.domain.card;

import fixture.CardFixture;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardDeckTest {

    @DisplayName("카드 덱이 비었는데 카드를 빼내서 반환하려고 하면 예외가 발생한다.")
    @Test
    void testPopCardWithEmptyDeck() {
        // given
        CardDeck cardDeck = new CardDeck(new ArrayList<>());

        // when & then
        assertThatThrownBy(cardDeck::popCard)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 덱에서 카드를 한 장 빼내어 반환한다.")
    @Test
    void testPopCard() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(CardFixture.createAHeart());

        CardDeck cardDeck = new CardDeck(cards);

        // when
        Card actual = cardDeck.popCard();

        // then
        assertAll(
                () -> assertThat(actual).isEqualTo(CardFixture.createAHeart()),
                () -> assertThatThrownBy(cardDeck::popCard)
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("모든 카드 랭크와 모양의 조합으로 이루어진 52장의 카드를 가지고 카드 덱을 생성한다.")
    @Test
    void testCreateFullCardDeck() {
        // given
        CardDeck shuffledFullCardDeck = CardDeck.createShuffledFullCardDeck();

        // when
        List<Card> cards = IntStream.range(0, 52)
                .mapToObj(i -> shuffledFullCardDeck.popCard())
                .toList();

        // then
        assertThat(cards)
                .doesNotHaveDuplicates()
                .hasSize(52);
    }
}
