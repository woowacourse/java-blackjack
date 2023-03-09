package domain.model;

import java.util.ArrayList;
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
        List<Card> cardList = new ArrayList<>();
        Player player = new Player(new Cards(cardList), "player");

        //when
        cardDistributor.giveCard(player);

        //then
        Assertions.assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("여러명에게 카드 배분을 테스트")
    public void testGiveCardToAll() {
        //given
        List<Card> cardList1 = new ArrayList<>();
        Player player1 = new Player(new Cards(cardList1), "player1");
        List<Card> cardList2 = new ArrayList<>();
        Player player2 = new Player(new Cards(cardList2), "player2");

        //when
        cardDistributor.giveCard(List.of(player1, player2));

        //then
        Assertions.assertThat(player1.getCards().size()).isEqualTo(1);
        Assertions.assertThat(player2.getCards().size()).isEqualTo(1);
    }
}
