package player;

import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 해당_플레이어가_버스트_되었다면_TRUE를_반환한다() {
        // given
        Player player = new Player();

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN)
        )));
        player.receiveInitialCards(deck);
        player.receiveInitialCards(deck);

        // when
        boolean isBust = player.isBust();

        // then
        Assertions.assertThat(isBust).isTrue();
    }

    @Test
    void 해당_플레이어가_버스트_되지_않았다면_FALSE를_반환한다() {
        // given
        Player player = new Player();

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN),
                new Card(CardSuit.SPADE, CardRank.TEN)
        )));
        player.receiveInitialCards(deck);

        // when
        boolean isBust = player.isBust();

        // then
        Assertions.assertThat(isBust).isFalse();
    }
}
