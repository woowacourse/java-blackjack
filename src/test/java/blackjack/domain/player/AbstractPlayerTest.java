package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AbstractPlayerTest {
    private static final String TEST_NAME = "pobi";

    @DisplayName("카드 뽑기 테스트")
    @Test
    void drawCard() {
        AbstractPlayer user = new User(TEST_NAME);
        Card twoCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TWO);

        user.drawCard(twoCard);

        assertThat(user.getCards()).contains(twoCard);
    }

    @DisplayName("A lowValue 테스트")
    @Test
    void aceLowValue() {
        Card tenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TEN);
        Card aceCard = Card.valueOf(CardShape.DIAMOND, CardNumber.ACE);
        AbstractPlayer user = new User(TEST_NAME, tenCard, tenCard);

        user.drawCard(aceCard);

        assertThat(user.getScore()).isEqualTo(21);
    }

    @DisplayName("A highValue 테스트")
    @Test
    void aceHighValue() {
        Card tenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TEN);
        Card aceCard = Card.valueOf(CardShape.DIAMOND, CardNumber.ACE);
        AbstractPlayer user = new User(TEST_NAME, tenCard, aceCard);

        assertThat(user.getScore()).isEqualTo(21);
    }

    @DisplayName("카드 반환 테스트")
    @Test
    void getCards() {
        Card tenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TEN);
        AbstractPlayer user = new User(TEST_NAME, tenCard, tenCard);
        assertThat(user.getCards()).containsExactly(tenCard, tenCard);
    }

    @DisplayName("랜덤 카드 한 개 뽑기 테스트")
    @Test
    void drawRandomOneCard() {
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        AbstractPlayer user = new User(TEST_NAME, aceCard, aceCard);
        assertThat(user.getCards()).hasSize(2);

        user.drawRandomOneCard();
        assertThat(user.getCards()).hasSize(3);
    }

    @DisplayName("이름 반환 테스트")
    @Test
    void getName() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getName()).isEqualTo(new Name(TEST_NAME));
    }

    @DisplayName("bust 인지 테스트")
    @Test
    void isBust() {
        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);
        AbstractPlayer user = new User(TEST_NAME, tenCard, tenCard);

        assertThat(user.isBust()).isFalse();

        user.drawCard(tenCard);
        assertThat(user.isBust()).isTrue();
    }

    @DisplayName("blackjack 인지 테스트")
    @Test
    void isBlackjack() {
        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);

        AbstractPlayer user = new User(TEST_NAME, tenCard, tenCard);
        assertThat(user.isBlackjack()).isFalse();

        user = new User(TEST_NAME, tenCard, aceCard);
        assertThat(user.isBlackjack()).isTrue();
    }
}