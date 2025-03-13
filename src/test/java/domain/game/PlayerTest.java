package domain.game;

import static domain.card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static domain.card.CardDeck.DRAW_COUNT_WHEN_START;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 플레이어는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(ArrayList::new);
        String name = "pobi";
        Player player = new Player(name);

        //when
        player.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_START));

        //then
        assertThat(player.getHand().getCards()).hasSize(2);
    }

    @Test
    void 플레이어는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(ArrayList::new);
        String name = "pobi";
        Player player = new Player(name);

        //when
        player.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));

        //then
        assertThat(player.getHand().getCards()).hasSize(1);
    }

    @Test
    void 플레이어가_보유한_카드의_합계를_구한다() {
        //given
        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.NINE)));

        //when & then
        assertThat(player.calculateTotalCardNumber()).isEqualTo(19);
    }

    @Test
    void 플레이어가_보유한_카드의_합계가_21을_넘었는지_판정한다() {
        //given
        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.JACK),
                new Card(Pattern.SPADE, CardNumber.TWO)
        ));

        //when & then
        assertThat(player.isOverBustBound()).isTrue();
    }
}
