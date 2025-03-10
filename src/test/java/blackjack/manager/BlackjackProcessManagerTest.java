package blackjack.manager;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackProcessManagerTest {

    BlackjackProcessManager blackjackProcessManager;

    @BeforeEach
    void init() {
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();
        Deck deck = new Deck(cards);
        blackjackProcessManager = new BlackjackProcessManager(deck);
    }

    @DisplayName("처음에 플레이어에게 카드 2장 쥐어준다.")
    @Test
    void test1() {
        // given
        Hand hand = new Hand();
        Player player = new Player("꾹이", hand);

        // when
        blackjackProcessManager.giveStartingCardsFor(player);

        // then
        assertThat(hand.getAllCards()).hasSize(2);
    }

    @DisplayName("손에 카드 1장을 쥐어준다")
    @Test
    void test2() {
        Hand hand = new Hand();
        Dealer dealer = new Dealer(hand);
        // when
        blackjackProcessManager.giveCard(dealer);

        // then
        assertThat(hand.getAllCards()).hasSize(1);
    }
}
