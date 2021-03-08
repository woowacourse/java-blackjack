package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AbstractPlayerTest {
    private static final String TEST_NAME = "pobi";

    private final Cards cards = Cards.createAllShuffledCards();

    @DisplayName("카드 뽑기 테스트")
    @Test
    void drawCard() {
        AbstractPlayer user = new User(TEST_NAME);
        Card twoCard = new Card(CardShapeType.DIAMOND, CardNumberType.TWO);

        user.drawOneCard(twoCard);

        assertThat(user.getCards()).containsExactly(twoCard);
    }

    @DisplayName("A가 1로 계산되어야 할 때 총 점수 계산 테스트 - 10, 10, A")
    @Test
    void getScoreAceLowValue1() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        user.drawOneCard(tenCard);
        user.drawOneCard(tenCard);
        user.drawOneCard(aceCard);

        assertThat(user.getScore()).isEqualTo(21);
    }

    @DisplayName("A가 1로 계산되어야 할 때 총 점수 계산 테스트 - 10, A, 10")
    @Test
    void getScoreAceLowValue2() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        user.drawOneCard(tenCard);
        user.drawOneCard(aceCard);
        user.drawOneCard(tenCard);

        assertThat(user.getScore()).isEqualTo(21);
    }

    @DisplayName("A가 11로 계산되어야 할 때 총 점수 계산 테스트")
    @Test
    void getScoreAceHighValue() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        user.drawOneCard(tenCard);
        user.drawOneCard(aceCard);

        assertThat(user.getScore()).isEqualTo(21);
    }

    @DisplayName("A가 2개일 때 : A(11) + A(1) = 12")
    @Test
    void getScoreDoubleAce() {
        AbstractPlayer user = new User(TEST_NAME);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        user.drawOneCard(aceCard);
        user.drawOneCard(aceCard);

        assertThat(user.getScore()).isEqualTo(12);
    }

    @DisplayName("A가 없을 때 총 점수 계산 테스트 - 총 합이 10")
    @Test
    void getScoreNotContainsAceTenOrLess() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);

        user.drawOneCard(tenCard);

        assertThat(user.getScore()).isEqualTo(10);
    }

    @DisplayName("A가 없을 때 총 점수 계산테스트 - 총 합이 11")
    @Test
    void getScoreNotContainsAce11OrMore() {
        AbstractPlayer user = new User(TEST_NAME);
        Card fiveCard = new Card(CardShapeType.DIAMOND, CardNumberType.FIVE);
        Card sixCard = new Card(CardShapeType.DIAMOND, CardNumberType.SIX);

        user.drawOneCard(fiveCard);
        user.drawOneCard(sixCard);

        assertThat(user.getScore()).isEqualTo(11);
    }

    @DisplayName("카드 반환 테스트")
    @Test
    void getCards() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getCards().isEmpty()).isTrue();

        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        user.drawOneCard(tenCard);
        assertThat(user.getCards()).containsExactly(tenCard);
    }

    @DisplayName("랜덤 카드 한 개 뽑기 테스트")
    @Test
    void drawRandomOneCard() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getCards().size()).isEqualTo(0);

        user.drawRandomOneCard(cards);
        assertThat(user.getCards().size()).isEqualTo(1);
    }

    @DisplayName("랜덤 카드 두 개 뽑기 테스트")
    @Test
    void drawRandomTwoCard() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getCards().size()).isEqualTo(0);

        user.drawRandomTwoCards(cards);
        assertThat(user.getCards().size()).isEqualTo(2);
    }

    @DisplayName("이름 반환 테스트")
    @Test
    void getName() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getName()).isEqualTo(new Name(TEST_NAME));
    }
}
