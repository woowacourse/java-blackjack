package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private static final int ALL_CARDS_SIZE = 52;

    @DisplayName("52장의 전체 카드로 채워진 Cards 생성 테스트")
    @Test
    void createAllShuffledCards() {
        Cards cards = Cards.createAllShuffledCards();
        Set<Card> notDuplicateCards = new HashSet<>();

        for (int i = 0; i < ALL_CARDS_SIZE; i++) {
            notDuplicateCards.add(cards.drawOneCard());
        }

        assertThat(notDuplicateCards).hasSize(ALL_CARDS_SIZE);

        for (CardShapeType cardShape : CardShapeType.values()) {
            for (CardNumberType cardNumber : CardNumberType.values()) {
                assertThat(notDuplicateCards).contains(new Card(cardShape, cardNumber));
            }
        }

        assertThatThrownBy(cards::drawOneCard)
            .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("비어있는 Cards 생성 테스트")
    @Test
    void createEmptyCards() {
        Cards cards = Cards.createEmptyCards();

        assertThatThrownBy(cards::drawOneCard)
            .isInstanceOf(IndexOutOfBoundsException.class);

        Card card = new Card(CardShapeType.HEART, CardNumberType.TWO);
        cards.add(card);
        assertThat(cards.getCards()).containsExactly(card);

        assertThat(cards.drawOneCard()).isEqualTo(card);
        assertThatThrownBy(cards::drawOneCard)
            .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("A가 1로 계산되어야 할 때 총 점수 계산 테스트 - 10, 10, A")
    @Test
    void getScoreAceLowValue1() {
        Cards cards = Cards.createEmptyCards();
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        cards.add(tenCard);
        cards.add(tenCard);
        cards.add(aceCard);

        assertThat(cards.getScore()).isEqualTo(21);
    }

    @DisplayName("A가 1로 계산되어야 할 때 총 점수 계산 테스트 - 10, A, 10")
    @Test
    void getScoreAceLowValue2() {
        Cards cards = Cards.createEmptyCards();
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        cards.add(tenCard);
        cards.add(aceCard);
        cards.add(tenCard);

        assertThat(cards.getScore()).isEqualTo(21);
    }

    @DisplayName("A가 11로 계산되어야 할 때 총 점수 계산 테스트")
    @Test
    void getScoreAceHighValue() {
        Cards cards = Cards.createEmptyCards();
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        cards.add(tenCard);
        cards.add(aceCard);

        assertThat(cards.getScore()).isEqualTo(21);
    }

    @DisplayName("A가 2개일 때 : A(11) + A(1) = 12")
    @Test
    void getScoreDoubleAce() {
        Cards cards = Cards.createEmptyCards();
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        cards.add(aceCard);
        cards.add(aceCard);

        assertThat(cards.getScore()).isEqualTo(12);
    }

    @DisplayName("A가 없을 때 총 점수 계산 테스트 - 총 합이 10")
    @Test
    void getScoreNotContainsAceTenOrLess() {
        Cards cards = Cards.createEmptyCards();
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);

        cards.add(tenCard);

        assertThat(cards.getScore()).isEqualTo(10);
    }

    @DisplayName("A가 없을 때 총 점수 계산테스트 - 총 합이 11")
    @Test
    void getScoreNotContainsAce11OrMore() {
        Cards cards = Cards.createEmptyCards();
        Card fiveCard = new Card(CardShapeType.DIAMOND, CardNumberType.FIVE);
        Card sixCard = new Card(CardShapeType.DIAMOND, CardNumberType.SIX);

        cards.add(fiveCard);
        cards.add(sixCard);

        assertThat(cards.getScore()).isEqualTo(11);
    }
}
