package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackTest {
    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 카드를 한장 더 뽑늗다.")
    void dealerHitTest() {
        // given
        Dealer dealer = new Dealer(new Hand(List.of()));
        List<Card> cards = List.of(Card.SPADE_NINE, Card.CLUB_SEVEN);
        Deck cardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return cards;
            }
        };
        BlackjackGame blackjackGame = new BlackjackGame(cardPicker);

        // when
        dealer.draw(cards);
        blackjackGame.drawIfScoreUnderBound(dealer);

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }
}
