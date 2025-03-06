package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackProcessManagerTest {

    @DisplayName("처음에 카드 2장 쥐어준다.")
    @Test
    void test1() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();
        Deck deck = new Deck(cards);
        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(deck);
        Hand hand = new Hand();

        // when
        blackjackProcessManager.giveStartingCards(hand);

        // then
        assertThat(hand.getAllCards()).hasSize(2);
    }

    @DisplayName("손에 카드 1장을 쥐어준다")
    @Test
    void test2() {
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();
        Deck deck = new Deck(cards);
        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(deck);
        Hand hand = new Hand();

        // when
        blackjackProcessManager.giveCard(hand);

        // then
        assertThat(hand.getAllCards()).hasSize(1);
    }
}
