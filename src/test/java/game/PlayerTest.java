package game;

import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;
import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 플레이어는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        String name = "pobi";
        Player player = new Player(name);

        //when
        player.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_START));

        //then
        assertThat(player.getHand().getCards()).hasSize(2);
    }

    @Test
    void 이름이_같은_플레이어는_같게_취급한다() {
        //given
        Player player1 = new Player("a");
        Player player2 = new Player("a");
        Player player3 = new Player("b");

        //when & then
        assertThat(player1.equals(player2)).isTrue();
        assertThat(player2.equals(player3)).isFalse();
    }

    @Test
    void 플레이어는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        String name = "pobi";
        Player player = new Player(name);

        //when
        player.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));

        //then
        assertThat(player.getHand().getCards()).hasSize(1);
    }

    @Test
    void 플레이어가_보유한_카드의_합계를_구한다() {
        //given
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.NINE)));

        //when & then
        assertThat(player.calculateTotalPoints()).isEqualTo(19);
    }

    @Test
    void 플레이어가_보유한_카드의_합계가_21을_넘었는지_판정한다() {
        //given
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.JACK),
                new Card(Pattern.SPADE, CardNumber.TWO)
        ));

        //when & then
        assertThat(player.isBust()).isTrue();
    }

    @Test
    void 플레이어는_원하는_금액을_배팅한다() {
        //given
        Player player = new Player("pobi", 10000);

        //when & then
        assertThat(player.getPlayerBettingMoney()).isEqualTo(10000);
    }
}
