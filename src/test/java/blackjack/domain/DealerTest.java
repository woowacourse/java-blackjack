package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.CardFigure;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.component.BettingAmount;
import blackjack.domain.user.component.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러의 카드 합이 16미만일 때 확인")
    @Test
    void canReceiveMoreCardTest_SUM_UNDER_16() {
        Cards cards = new Cards(Arrays.asList(new Card(CardNumber.FIVE, CardFigure.CLOVER),
                new Card(CardNumber.FIVE, CardFigure.HEART)));
        CardDeck cardDeck = new CardDeck(cards);

        Dealer dealer = new Dealer();
        dealer.drawCard(cardDeck);

        boolean expected = true;
        assertThat(dealer.receivable()).isEqualTo(expected);
    }

    @DisplayName("딜러의 카드 합이 16초과할 때 확인")
    @Test
    void canReceiveMoreCard_SUM_OVER_16() {
        Cards cards = new Cards(Arrays.asList(new Card(CardNumber.QUEEN, CardFigure.CLOVER),
                new Card(CardNumber.KING, CardFigure.HEART)));
        CardDeck cardDeck = new CardDeck(cards);

        Dealer dealer = new Dealer();
        dealer.drawCard(cardDeck);

        boolean expected = false;
        assertThat(dealer.receivable()).isEqualTo(expected);
    }
}
