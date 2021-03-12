package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShuffleStrategyTest {
    @Test
    @DisplayName("섞기 전략에 대한 테스트")
    void noShuffleApplied() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardLetter.ACE, CardSuit.HEART));
    }
}
