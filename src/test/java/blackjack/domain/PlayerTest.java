package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @DisplayName("플레이어의 카드 합이 21이하일 때 확인")
    @Test
    void canReceiveMoreCard_SUM_UNDER_21() {
        List<Card> cards = Arrays.asList(new Card(CardNumber.ACE, CardFigure.CLOVER),
                new Card(CardNumber.JACK, CardFigure.CLOVER));
        CardDeck cardDeck = new CardDeck(cards);

        Player player = new Player("pobi");
        player.drawCard(cardDeck);

        boolean expected = true;
        assertThat(player.receivable()).isEqualTo(expected);
    }

    @DisplayName("플레이어의 카드 합이 21초과일 때 확인")
    @Test
    void canReceiveMoreCard_SUM_OVER_21() {
        List<Card> cards = Arrays.asList(new Card(CardNumber.JACK, CardFigure.CLOVER),
                                        new Card(CardNumber.KING, CardFigure.HEART),
                                        new Card(CardNumber.QUEEN, CardFigure.DIAMOND));
        CardDeck cardDeck = new CardDeck(cards);

        Player player = new Player("pobi");
        player.drawCard(cardDeck);
        player.drawCard(cardDeck);

        boolean expected = false;
        assertThat(player.receivable()).isEqualTo(expected);
    }
}
