package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

class ResultTest {

    @Test
    @DisplayName("딜러가 이긴 경우")
    void dealerWinTest() {
        Dealer dealer = new Dealer(new Cards(List.of(new Card(Denomination.EIGHT, Suit.CLUBS))));
        Player player = new Player("a", new Cards(List.of(new Card(Denomination.TWO, Suit.CLUBS))));

        Map<Gamer, Result> map = Result.judge(dealer, player);

        assertThat(map.get(dealer).getValue()).isEqualTo("승");
    }

    @Test
    @DisplayName("플레이어가 이긴 경우")
    void playerWinTest() {
        Dealer dealer = new Dealer(new Cards(List.of(new Card(Denomination.EIGHT, Suit.CLUBS))));
        Player player = new Player("a", new Cards(List.of(new Card(Denomination.NINE, Suit.CLUBS))));

        Map<Gamer, Result> map = Result.judge(dealer, player);

        assertThat(map.get(player).getValue()).isEqualTo("승");
    }

    @Test
    @DisplayName("비긴 경우")
    void drawTest() {
        Dealer dealer = new Dealer(new Cards(List.of(new Card(Denomination.EIGHT, Suit.CLUBS))));
        Player player = new Player("a", new Cards(List.of(new Card(Denomination.EIGHT, Suit.CLUBS))));

        Map<Gamer, Result> map = Result.judge(dealer, player);

        assertThat(map.get(dealer).getValue()).isEqualTo("무");
    }
}