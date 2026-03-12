package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private final Hand emptyHand = new Hand();

    @Test
    void 본인의_이름을_반환한다() {
        // given
        String playerName = "Player Name";
        // when
        Player player = new Player(playerName, emptyHand);
        // then
        assertThat(player.getName()).isEqualTo(playerName);
    }

    @Test
    void 이름이_null인_경우_에러를_던진다() {
        // given
        String playerName = null;
        // when & then
        assertThatThrownBy(() -> new Player(playerName, emptyHand))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이름이_blank인_경우_에러를_던진다() {
        // given
        String playerName = "";
        // when & then
        assertThatThrownBy(() -> new Player(playerName, emptyHand))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 처음_딜링된_카드는_모두_반환한다() {
        // given
        Player player = new Player("player", emptyHand);
        player.hit(new Card(Rank.ACE, Suit.CLOVER));
        player.hit(new Card(Rank.TWO, Suit.CLOVER));
        // when
        List<Card> visibleCards = player.getInitialVisibleCards();
        // then
        assertThat(visibleCards.size()).isEqualTo(2);
    }

    @Test
    void 딜링된_카드가_없는_경우_빈_리스트를_반환한다() {
        // given
        Player player = new Player("player", emptyHand);
        // when
        List<Card> visibleCards = player.getInitialVisibleCards();
        // then
        assertThat(visibleCards.size()).isEqualTo(0);
    }
}
