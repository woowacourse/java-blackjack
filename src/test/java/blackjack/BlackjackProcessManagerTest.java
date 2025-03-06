package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackProcessManagerTest {

    @DisplayName("손에 카드 2장 쥐어준다.")
    @Test
    void test1() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();
        Deck deck = new Deck(cards);
        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(deck);
        Hand hand = new Hand();

        // when
        blackjackProcessManager.giveCardTo(hand);

        // then
        assertThat(hand.getAllCards()).hasSize(2);
    }
}
