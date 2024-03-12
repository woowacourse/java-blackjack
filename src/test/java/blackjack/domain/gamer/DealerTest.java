package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.CardScore.*;
import static blackjack.domain.card.CardSuit.CLUB;
import static blackjack.domain.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러")
class DealerTest {
    @Test
    @DisplayName("는 딜 이후 첫 카드만 반환할 수 있다.")
    void getFirstCard() {
        // given
        Dealer dealer = new Dealer(new Deck(cards ->
                List.of(new Card(SPADE, NINE), new Card(CLUB, QUEEN))));
        Card expectedCard = new Card(CLUB, QUEEN);

        // when
        dealer.deal();

        // then
        assertThat(dealer.getFirstCard()).isEqualTo(expectedCard);
    }

    @Test
    @DisplayName("의 카드 합이 16 이하이면 계속 진행할 수 있다.")
    void canContinue() {
        // given
        Dealer dealer = new Dealer(new Deck(cards ->
                List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN))));

        // when
        dealer.deal();

        // then
        assertThat(dealer.canContinue()).isEqualTo(true);
    }

    @Test
    @DisplayName("의 카드 합이 16 이하이면 카드를 한장 더 뽑는다.")
    void hit() {
        // given
        Dealer dealer = new Dealer(new Deck(cards ->
                List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, TWO))));

        // when
        dealer.deal();
        if (dealer.canContinue()) {
            dealer.hit();
        }

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }
}
