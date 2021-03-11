package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchResultTest {

    @Test
    @DisplayName("승패 확인")
    void checkPlayerMatchResult() {
        Player player = new Player("pika");
        Dealer dealer = new Dealer();


        player.firstDraw(new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.SPADE, Denomination.KING)
                )));
        dealer.firstDraw(new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.FIVE),
                new Card(Shape.SPADE, Denomination.KING)
                )));

        player.stay();
        dealer.stay();

        assertThat(MatchResult.matchPlayerAndDealer(player, dealer)).isEqualTo(MatchResult.WIN);

        player.firstDraw(new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.SPADE, Denomination.TWO)
                )));
        dealer.firstDraw(new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.SPADE, Denomination.KING)
                )));

        player.stay();
        dealer.stay();

        assertThat(MatchResult.matchPlayerAndDealer(player, dealer)).isEqualTo(MatchResult.LOSE);

        player.firstDraw(new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.SPADE, Denomination.KING)
                )));
        dealer.firstDraw(new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.SPADE, Denomination.KING)
                )));

        player.stay();
        dealer.stay();

        assertThat(MatchResult.matchPlayerAndDealer(player, dealer)).isEqualTo(MatchResult.DRAW);
    }
}
