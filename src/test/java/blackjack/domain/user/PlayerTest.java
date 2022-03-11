package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

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
        Deck deck = new Deck();
        Player player = new Player("pobi");

        //when
        player.drawCard(deck);
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
            player.drawCard(deck);
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

        player.drawCard(deck);
        player.drawCard(deck);

        //when
        List<Card> cards = player.showInitCards();

        //then
        assertThat(cards.size()).isEqualTo(2);
    }

}
