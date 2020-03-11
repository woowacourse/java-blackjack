package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    private Card card1;
    private Card card2;
    private Cards cards;
    private User user;

    @BeforeEach
    private void setup() {
        card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        cards = new Cards();
        user = new User(card1, card2);
    }

    @DisplayName("y를 입력 받을때 카드를 받는지 결정하는 메서드")
    @Test
    void yes_insertCard() {
        AnswerType answerType = AnswerType.YES;

        user.insertCard(cards, answerType);

        Assertions.assertThat(user).extracting("cards").asList().size().isEqualTo(3);
    }

    @DisplayName("n를 입력 받을때 카드를 받는지 결정하는 메서드")
    @Test
    void no_insertCard() {
        AnswerType answerType = AnswerType.NO;

        user.insertCard(cards, answerType);

        Assertions.assertThat(user).extracting("cards").asList().size().isEqualTo(2);
    }
}
