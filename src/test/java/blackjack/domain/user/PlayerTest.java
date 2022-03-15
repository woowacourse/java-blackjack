package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.strategy.hit.PlayerHitStrategy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    public static final int MAX_DRAWABLE_COUNT = 11;

    @DisplayName("플레이어 생성 검증")
    @Test
    public void createPlayer() {
        //given
        String name = "pobi";

        //when
        Player player = new Player(name);

        //then
        assertThat(player).isNotNull();
    }

    @DisplayName("플레이어는 카드를 뽑을 수 있다.")
    @Test
    public void testDrawCard() {
        //given
        Player player = new Player("pobi");

        //when
        player.receiveCard(new Card(Suit.CLOVER, Denomination.ACE));
        List<Card> cards = player.getHandCards();

        //then
        assertThat(cards.size()).isEqualTo(1);
    }

    @DisplayName("카드를 뽑을 수 있는지 여부를 확인할 수 있다.")
    @Test
    public void testCardDrawable() {
        //given
        Deck deck = new Deck();
        Player player = new Player("pobi");

        //when
        for (int i = 0; i < MAX_DRAWABLE_COUNT; i++) {
            player.receiveCard(deck.drawCard());
        }
        //then
        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("플레어어가 보여주는 초기 카드는 2장이다.")
    @Test
    public void testShowInitCards() {
        //given
        Deck deck = new Deck();
        Player player = new Player("pobi");

        player.receiveCard(new Card(Suit.CLOVER, Denomination.SEVEN));
        player.receiveCard(new Card(Suit.SPADE, Denomination.JACK));

        //when
        List<Card> cards = player.showInitCards();

        //then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어는 y를 입력받으면 카드를 한장 더 받을 수 있다.")
    public void testPlayerHitWithInputY() {
        // given
        Deck deck = new Deck();
        Player player = new Player("pobi");
        // when
        boolean isHit = player.hitOrStay(deck, new PlayerHitStrategy(() -> "y"));
        // then
        assertThat(isHit).isTrue();
    }

    @Test
    @DisplayName("플레이어는 n을 입력받으면 카드를 한장 더 받을 수 없다다.")
    public void testPlayerHitWithInputN() {
        // given
        Deck deck = new Deck();
        Player player = new Player("pobi");
        // when
        boolean isHit = player.hitOrStay(deck, new PlayerHitStrategy(() -> "n"));
        // then
        assertThat(isHit).isFalse();
    }
}
