package domain.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDistributorTest {

    private final CardDistributor cardDistributor = new CardDistributor(new RandomCardGenerator());

    @Test
    @DisplayName("한명에게 카드 배분을 테스트")
    public void testGiveCardToOne() {
        //given
        Set<Card> cardSet = new HashSet<>();
        Player player = new Player(new Cards(cardSet));

        //when
        cardDistributor.giveCard(player);

        //then
        Assertions.assertThat(player.getCards().getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("여러명에게 카드 배분을 테스트")
    public void testGiveCardToAll() {
        //given
        Set<Card> cardSet1 = new HashSet<>();
        Player player1 = new Player(new Cards(cardSet1));
        Set<Card> cardSet2 = new HashSet<>();
        Player player2 = new Player(new Cards(cardSet2));

        //when
        cardDistributor.giveCard(List.of(player1, player2));

        //then
        Assertions.assertThat(player1.getCards().getCards().size()).isEqualTo(1);
        Assertions.assertThat(player2.getCards().getCards().size()).isEqualTo(1);
    }
}