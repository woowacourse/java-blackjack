package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import blackjack.view.OutputView;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 자신의 패가 16이하이면 한장을 더 받는다")
    @Test
    void should_AddCard_When_HandsScoreBelowThreshold() {
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(9),
                Card.valueOf(5)));
        Deck deck = Deck.createShuffledDeck();

        dealer.addCard(deck.draw());

        assertThat(dealer.getHandsCards()).hasSize(3);
    }

    @DisplayName("딜러는 자신의 패가 17이상이면 한장을 더 받지 않는다")
    @Test
    void should_NotAddCard_When_HandsScoreOverThreshold() {
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(9),
                Card.valueOf(6)));
        Deck deck = Deck.createShuffledDeck();

        dealer.confirmDealerHands(deck, message -> {});

        assertThat(dealer.getHandsCards()).hasSize(2);
    }

    @DisplayName("딜러는 첫번째 패를 보여준다")
    @Test
    void should_ShowFirstCard() {
        Dealer dealer = Dealer.createDealerWithCards(List.of(
                Card.valueOf(6),
                Card.valueOf(45)));

        assertThat(dealer.getFirstCardName())
                .isEqualTo(Card.valueOf(6)
                        .getCardName());
    }
}
