package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("가지고있는 카드들의 총합 가능성을 계산한다.")
    @Test
    void testCards() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.DIAMOND, CardRank.TWO));
        cardDeck.add(new Card(CardSuit.SPADE, CardRank.EIGHT));

        Set<Integer> possibleSums = cardDeck.calculatePossibleSums();

        assertThat(possibleSums).isEqualTo(Set.of(10));
    }

    @DisplayName("총합 가능성 계산 시 에이스가 1인 경우와 11인 경우 모두 계산한다.")
    @Test
    void testCards_ace() {
        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
        cardDeck2.add(new Card(CardSuit.HEART, CardRank.ACE));

        Set<Integer> possibleSums = cardDeck2.calculatePossibleSums();

        assertThat(possibleSums).isEqualTo(Set.of(9, 19));
    }

    @DisplayName("에이스가 여러개인 경우에도 정상적으로 계산한다.")
    @Test
    void testCards_multipleAce() {
        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
        cardDeck3.add(new Card(CardSuit.HEART, CardRank.ACE));
        cardDeck3.add(new Card(CardSuit.SPADE, CardRank.ACE));

        Set<Integer> possibleSums = cardDeck3.calculatePossibleSums();

        assertThat(possibleSums).isEqualTo(Set.of(10, 20, 30));
    }

    @DisplayName("에이스가 4개인 경우에도 정상적으로 계산한다.")
    @Test
    void testCards_multipleAce4() {
        CardDeck cardDeck4 = new CardDeck();
        cardDeck4.add(new Card(CardSuit.DIAMOND, CardRank.ACE));
        cardDeck4.add(new Card(CardSuit.HEART, CardRank.ACE));
        cardDeck4.add(new Card(CardSuit.SPADE, CardRank.ACE));
        cardDeck4.add(new Card(CardSuit.CLUB, CardRank.ACE));

        Set<Integer> possibleSums = cardDeck4.calculatePossibleSums();

        assertThat(possibleSums).isEqualTo(Set.of(4, 14, 24, 34, 44));
    }

    @DisplayName("한 장의 주어진 카드를 카드 덱에 추가한다.")
    @Test
    void testAddCards() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.HEART, CardRank.TWO));

        assertThat(cardDeck.getDeckSize()).isEqualTo(1);
    }
}
