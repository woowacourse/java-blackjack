package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CloverCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("플레이어는 ")
class PlayerTest {
    @Test
    @DisplayName("처음에 2장의 카드를 받는다.")
    void generatePlayerTest() {
        //given
        Player player = new Player("player");
        player.receiveCards(List.of(CloverCard.ACE, CloverCard.FIVE));

        //when
        List<Card> cards = player.getCards();

        //then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 한 장을 받아 패에 넣는다.")
    void receiveCardTest() {
        //given
        Player player = new Player("player");
        player.receiveCards(List.of(CloverCard.ACE, CloverCard.FIVE));

        //when
        player.receiveCard(CloverCard.FOUR);

        //then
        assertThat(player.getCards().size()).isEqualTo(3);
    }
}
