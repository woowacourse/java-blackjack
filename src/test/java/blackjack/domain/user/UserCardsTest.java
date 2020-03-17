package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserCardsTest {
    @Test
    @DisplayName("총 점수가 잘 계산됐는지 확인")
    void totalScoreTest() {
        Card card1 = new Card(Suit.CLUB, Symbol.TEN);
        Card card2 = new Card(Suit.HEART, Symbol.KING);
        UserCards userCards = new UserCards(Arrays.asList(card1, card2));
        assertThat(userCards.calculateTotalScore()).isEqualTo(20);
    }
}
