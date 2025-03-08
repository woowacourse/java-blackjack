package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.Pattern;
import domain.card.TestShuffler;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 플레이어는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        String name = "pobi";
        Player player = new Player(name);

        //when
        player.drawCardWhenStart(cardDeck);

        //then
        assertThat(player.getHand().getCards()).hasSize(2);
    }

    @Test
    void 플레이어는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(new TestShuffler());
        String name = "pobi";
        Player player = new Player(name);

        //when
        player.drawCard(cardDeck);

        //then
        assertThat(player.getHand().getCards()).hasSize(1);
    }

    @Test
    void 플레이어가_보유한_카드의_합계를_구한다() {
        //given
        Player player = new Player("pobi");
        Hand hand = player.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when & then
        assertThat(player.calculateTotalCardNumber()).isEqualTo(20);
    }

    @Test
    void 플레이어가_보유한_카드의_합계가_21을_넘었는지_판정한다() {
        //given
        Player player = new Player("pobi");
        Hand hand = player.getHand();
        List<Card> cards = hand.getCards();
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when & then
        assertThat(player.isOverBustBound()).isFalse();
    }
}
