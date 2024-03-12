package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.Rank.NINE;
import static blackjack.domain.card.Rank.SEVEN;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("블랙잭")
public class BlackjackTest {
    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 카드를 한장 더 뽑늗다.")
    void dealerHitTest() {
        // given
        Dealer dealer = new Dealer(new Hand(List.of()));
        List<Card> cards = new ArrayList<>(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB)));
        Deck cardPicker = new Deck(cards) {
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
