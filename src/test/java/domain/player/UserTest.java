package domain.player;

import domain.AnswerType;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    private Cards cards;
    private User user;

    @BeforeEach
    private void setup() {
        cards = new Cards();
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        user = new User("pobi", card1, card2);
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
