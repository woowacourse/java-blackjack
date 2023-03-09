package domain.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDistributorTest {

    private final CardDistributor cardDistributor = new CardDistributor(new RandomCardGenerator());

    @Test
    @DisplayName("한명에게 카드 배분을 테스트")
    public void testGiveCardToOne() {
        //given
        Player player = new Player("player", Cards.empty());

        //when
        cardDistributor.giveCard(player);

        //then
        Assertions.assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("여러명에게 카드 배분을 테스트")
    public void testGiveCardToAll() {
        //given
        Player player1 = new Player("player1", Cards.empty());
        Player player2 = new Player("player2", Cards.empty());

        //when
        cardDistributor.giveCard(List.of(player1, player2));

        //then
        Assertions.assertThat(player1.getCards().size()).isEqualTo(1);
        Assertions.assertThat(player2.getCards().size()).isEqualTo(1);
    }
}
