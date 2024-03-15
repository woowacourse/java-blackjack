package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import fixture.CardFixture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("모든 카드 랭크와 모양의 조합으로 카드 덱을 생성한다.")
    @Test
    void testCreateFullCardDeck() {
        // given
        List<Card> cards = new ArrayList<>();
        Arrays.stream(CardRank.values()).forEach(
                rank -> Arrays.stream(CardSuit.values()).forEach(
                        suit -> cards.add(new Card(rank, suit))
                )
        );
        CardDeck expected = new CardDeck(cards);

        // when
        CardDeck actual = CardDeck.createShuffledFullCardDeck();

        // then
        assertThat(actual).isEqualTo(expected);
    }

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

    @DisplayName("동일한 카드를 포함하고 있다면 같은 카드 덱이다.")
    @Test
    void testEquals() {
        // given
        CardDeck cardDeck1 = new CardDeck(List.of(
                new Card(CardRank.ACE, CardSuit.HEART),
                new Card(CardRank.TWO, CardSuit.HEART),
                new Card(CardRank.THREE, CardSuit.HEART)
        ));

        CardDeck cardDeck2 = new CardDeck(List.of(
                new Card(CardRank.THREE, CardSuit.HEART),
                new Card(CardRank.TWO, CardSuit.HEART),
                new Card(CardRank.ACE, CardSuit.HEART)
        ));

        // when & then
        assertThat(cardDeck1).isEqualTo(cardDeck2);
    }
}
