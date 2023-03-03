package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CloverCard;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어는 ")
class PlayerTest {
    @Test
    @DisplayName("처음에 2장의 카드를 받는다.")
    void generatePlayerTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_FIVE);
        Player player = new Player(new Name("플레이어"), firstTurnCards);

        //when
        List<Card> cards = player.getCards();

        //then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 한 장을 받아 패에 넣는다.")
    void receiveCardTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_FIVE);
        Player player = new Player(new Name("플레이어"), firstTurnCards);
        List<Card> cards = player.getCards();

        //when
        player.receiveCard(CloverCard.CLOVER_FOUR);

        //then
        assertThat(cards.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("합산 점수가 21을 초과하면 Bust 상태가 된다.")
    void checkBustByScoreTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.CLOVER_TEN, CloverCard.CLOVER_KING);
        Player player = new Player(new Name("플레이어"), firstTurnCards);

        //when
        player.receiveCard(CloverCard.CLOVER_QUEEN);
        UserStatus result = player.getStatus();

        //then
        assertThat(result).isEqualTo(PlayerStatus.BUST);
    }
}
