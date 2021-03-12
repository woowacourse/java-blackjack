package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import blackjack.domain.player.strategy.AllCardsOpenStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

    @DisplayName("A가 1로 계산되어야 할 때 총 점수 계산 테스트 - 10, 10, A(1) = 총 합: 21 / 블랙잭 false")
    @Test
    void getScoreAceLowValue1() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        user.drawOneCard(tenCard);
        user.drawOneCard(tenCard);
        user.drawOneCard(aceCard);

        assertThat(user.getScore()).isEqualTo(21);
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
    }

    @DisplayName("A가 1로 계산되어야 할 때 총 점수 계산 테스트 - 10, A(1), 10 = 총 합: 21 / 블랙잭 false")
    @Test
    void getScoreAceLowValue2() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        user.drawOneCard(tenCard);
        user.drawOneCard(aceCard);
        user.drawOneCard(tenCard);

        assertThat(user.getScore()).isEqualTo(21);
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
    }

    @DisplayName("A가 11로 계산되어야 할 때 총 점수 계산 테스트 - 10, A(11) = 총 합: 21 / 블랙잭 true")
    @Test
    void getScoreAceHighValue() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        user.drawOneCard(tenCard);
        user.drawOneCard(aceCard);

        assertThat(user.getScore()).isEqualTo(21);
        assertThat(user.isBlackJack()).isTrue();
        assertThat(user.isBust()).isFalse();
    }

    @DisplayName("A가 2개일 때 : A(11) + A(1) = 총 합: 12 / 블랙잭 false")
    @Test
    void getScoreDoubleAce() {
        AbstractPlayer user = new User(TEST_NAME);
        Card aceCard = new Card(CardShapeType.DIAMOND, CardNumberType.ACE);

        user.drawOneCard(aceCard);
        user.drawOneCard(aceCard);

        assertThat(user.getScore()).isEqualTo(12);
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
    }

    @DisplayName("A가 없을 때 총 점수 계산 테스트 = 총 합: 10 / 블랙잭 false")
    @Test
    void getScoreNotContainsAceTenOrLess() {
        AbstractPlayer user = new User(TEST_NAME);
        Card fiveCard = new Card(CardShapeType.DIAMOND, CardNumberType.FIVE);

        user.drawOneCard(fiveCard);
        user.drawOneCard(fiveCard);

        assertThat(user.getScore()).isEqualTo(10);
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
    }

    @DisplayName("A가 없을 때 총 점수 계산 테스트 - 총 합: 11 / 블랙잭 false")
    @Test
    void getScoreNotContainsAce11OrMore() {
        AbstractPlayer user = new User(TEST_NAME);
        Card fiveCard = new Card(CardShapeType.DIAMOND, CardNumberType.FIVE);
        Card sixCard = new Card(CardShapeType.DIAMOND, CardNumberType.SIX);

        user.drawOneCard(fiveCard);
        user.drawOneCard(sixCard);

        assertThat(user.getScore()).isEqualTo(11);
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
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

    @DisplayName("isCanDraw() 호출 후 카드 순서 안섞이는지 테스트")
    @Test
    void getCardsInOrder() {
        Dealer dealer = new Dealer();
        dealer.setCardOpenStrategy(new AllCardsOpenStrategy());
        drawCardsInOrder(dealer);

        User user = new User("유저");
        drawCardsInOrder(user);

        assertThat(dealer.getCards()).isSortedAccordingTo(Comparator.comparing(Card::getValue));
        assertThat(user.getCards()).isSortedAccordingTo(Comparator.comparing(Card::getValue));

        dealer.isCanDraw();
        user.isCanDraw();

        assertThat(dealer.getCards()).isSortedAccordingTo(Comparator.comparing(Card::getValue));
        assertThat(user.getCards()).isSortedAccordingTo(Comparator.comparing(Card::getValue));
    }

    private void drawCardsInOrder(Player player) {
        List<Card> cards = new ArrayList<>(Arrays.asList(
            new Card(CardShapeType.HEART, CardNumberType.ACE),
            new Card(CardShapeType.CLUB, CardNumberType.TWO),
            new Card(CardShapeType.SPADE, CardNumberType.THREE),
            new Card(CardShapeType.DIAMOND, CardNumberType.FOUR),
            new Card(CardShapeType.HEART, CardNumberType.FIVE)
        ));

        for (Card card : cards) {
            player.drawOneCard(card);
        }
    }
}
