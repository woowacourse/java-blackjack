package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("카드를 받아 수중에 카드를 추가한다")
    void should_add_Card_card() {
        // given
        Card card = new Card(Shape.HEART, Rank.A);
        Player player = new Player("a");

        // then
        player.addCard(card);

        // when
        assertThat(player.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("모두에게 보여줄 플레이어 카드를 가져온다.")
    void should_return_public_able_cards() {
        //given
        Participant player = new Player("amy");
        player.addCard(new Card(Shape.HEART, Rank.A));
        player.addCard(new Card(Shape.HEART, Rank.ONE));
        //when
        List<Card> shownCard = player.getShownCard();

        //then
        assertThat(shownCard).hasSize(2);
    }
}
