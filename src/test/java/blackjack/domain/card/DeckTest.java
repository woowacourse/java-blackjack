package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class DeckTest {

    @Test
    @DisplayName("초기에 전달받는 카드는 2장이다.")
    void returnDistributeCards() {
        List<Card> cards = new Deck().makeDistributeCard();

        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기에 전달받는 카드는 중복일 수 없다.")
    void notRedundantCards() {
        List<Card> cards = new Deck().makeDistributeCard();

        assertThat(new HashSet<>(cards).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드의 타입은 스페이드, 하트, 다이아몬드, 클로버이다.")
    void checkKindOfType() {
        Card card = new Deck().draw();

        assertThat(Type.getTypeValues().contains(card.getType()))
                .isEqualTo(true);
    }

    @Test
    @DisplayName("덱에서 카드를 한 장 반환한다.")
    void drawCard() {
        Deck deck = new Deck();
        Card card = deck.draw();

        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("덱에서 draw된 카드는 서로 다르다.")
    void drawDifferentCard() {
        Deck deck = new Deck();
        Card card1 = deck.draw();
        Card card2 = deck.draw();

        assertThat(card1).isNotEqualTo(card2);
    }

    @Test
    @DisplayName("덱에서 draw된 카드는 덱에서 제외되어야 한다.")
    void pickedCardNotInDeck() {
        Deck deck = new Deck();
        Card card = deck.draw();

        assertThat(deck.containCard(card)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("getCombinationOfCard")
    @DisplayName("덱에 카드가 포함되어 있는지 확인한다.")
    void checkContainsCard(Card card) {
        Deck deck = new Deck();

        assertThat(deck.containCard(card)).isTrue();
    }

    private static Stream<Card> getCombinationOfCard() {
        return Stream.of(
                new Card(Type.SPADE, Score.ACE),
                new Card(Type.SPADE, Score.THREE),
                new Card(Type.CLOVER, Score.FIVE),
                new Card(Type.CLOVER, Score.SEVEN),
                new Card(Type.DIAMOND, Score.TEN),
                new Card(Type.DIAMOND, Score.JACK),
                new Card(Type.HEART, Score.KING),
                new Card(Type.HEART, Score.QUEEN)
        );
    }
}
