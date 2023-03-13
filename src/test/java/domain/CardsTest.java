package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.Cards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("Card 객체들을 매개변수로 입력할 경우 Cards 객체가 정상적으로 생성된다")
    void generateCards() {
        List<Card> cards = List.of(new Card(CardType.CLOVER, CardValue.ACE), new Card(CardType.DIAMOND, CardValue.JACK));

        assertDoesNotThrow(() -> new Cards(cards));
    }

    @Test
    @DisplayName("Cards 객체에 속한 개별 카드들의 합을 구한다")
    void getSumOfCards() {
        Cards cards = new Cards(List.of(new Card(CardType.CLOVER, CardValue.FIVE), new Card(CardType.DIAMOND, CardValue.FOUR)));
        int expectedSum = 9;

        assertThat(cards.calculateSum()).isEqualTo(expectedSum);
    }

    @Test
    @DisplayName("합을 구하려는 카드 중 Ace가 포함될 경우 Ace는 1이 아닌 11로 계산될 수 있다.")
    void getSumOfCardsWhenAceValueIs11() {
        Cards cards = new Cards(List.of(new Card(CardType.CLOVER, CardValue.ACE), new Card(CardType.DIAMOND, CardValue.KING)));
        int expectedSum = 21;

        assertThat(cards.calculateSum()).isEqualTo(expectedSum);
    }

    @Test
    @DisplayName("합을 구하려는 카드 중 Ace가 포함될 경우 Ace는 1로 계산될 수 있다.")
    void getSumOfCardsWhenAceValueIs1() {
        Cards cards = new Cards(List.of(new Card(CardType.CLOVER, CardValue.ACE), new Card(CardType.DIAMOND, CardValue.KING), new Card(CardType.HEART, CardValue.EIGHT)));
        int expectedSum = 19;

        assertThat(cards.calculateSum()).isEqualTo(expectedSum);
    }

    @Test
    @DisplayName("Ace가 두장 포함 될 경우 한장은 1, 다른 한장은 11로 계산된다.")
    void getSumOfCardsWhenCardsContainTwoAce() {
        Cards cards = new Cards(List.of(new Card(CardType.CLOVER, CardValue.ACE), new Card(CardType.DIAMOND, CardValue.ACE)));
        int expectedSum = 12;

        assertThat(cards.calculateSum()).isEqualTo(expectedSum);
    }

    @Test
    @DisplayName("초기 카드 두장의 합이 21(블랙잭)이면 true를 리턴한다")
    void isBlackjack() {
        Cards cards = new Cards(List.of(new Card(CardType.HEART, CardValue.ACE), new Card(CardType.DIAMOND, CardValue.JACK)));

        assertThat(cards.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("초기 카드 두장의 합이 21(블랙잭)이 아니면 false를 리턴한다")
    void isNotBlackjack() {
        Cards cardsUnder21 = new Cards(List.of(new Card(CardType.HEART, CardValue.ACE), new Card(CardType.DIAMOND, CardValue.TWO)));
        Cards cardsOver21 = new Cards(List.of(new Card(CardType.HEART, CardValue.ACE), new Card(CardType.DIAMOND, CardValue.JACK), new Card(CardType.SPADE, CardValue.FIVE)));

        Assertions.assertAll(
                () -> assertThat(cardsUnder21.isBlackjack()).isFalse(),
                () -> assertThat(cardsOver21.isBlackjack()).isFalse()
        );
    }

    @Test
    @DisplayName("기존의 카드 목록에 새로운 카드를 추가하면 카드의 개수가 1만큼 추가된다.")
    void addCard() {
        Cards cards = new Cards(new ArrayList<>(List.of(new Card(CardType.HEART, CardValue.ACE), new Card(CardType.DIAMOND, CardValue.TWO))));

        Card additionalCard = new Card(CardType.SPADE, CardValue.THREE);
        int expectedCardsSize = 3;

        cards.addCard(additionalCard);

        assertThat(cards.getCards().size()).isEqualTo(expectedCardsSize);
    }
}


