package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CloverCard;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("플레이어는 처음에 2장의 카드를 받는다.")
    void generatePlayerTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_FIVE);
        Player player = new Player(firstTurnCards);

        //when
        List<Card> cards = player.getCards();

        //then
        assertThat(cards.size()).isEqualTo(2);
    }
}
