package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.MockDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.HoldCards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;

public class ScoreTest {

    @Nested
    @DisplayName("BLACKJACK_WIN은")
    class Blackjack {
        @Test
        @DisplayName("플레이어가 블랙잭, 딜러가 블래잭이 아닐 때 true")
        void match_true() {
            MockDeck blackjackDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.ACE),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)));
            Player player = new Player(new Name("player"),
                HoldCards.drawTwoCards(blackjackDeck));

            MockDeck dealerDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)));
            Dealer dealer = new Dealer(HoldCards.drawTwoCards(dealerDeck));

            Assertions.assertThat(Score.BLACKJACK_WIN.match(player, dealer)).isTrue();
        }

        @Test
        @DisplayName("플레이어가 블랙잭이 아니면 false")
        void match_false() {
            MockDeck blackjackDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)));
            Player player = new Player(new Name("player"),
                HoldCards.drawTwoCards(blackjackDeck));

            MockDeck dealerDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)));
            Dealer dealer = new Dealer(HoldCards.drawTwoCards(dealerDeck));

            Assertions.assertThat(Score.BLACKJACK_WIN.match(player, dealer)).isFalse();
        }
    }
}
