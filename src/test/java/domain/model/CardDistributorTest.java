package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDistributorTest {

    private final CardDistributor cardDistributor = new CardDistributor(new RandomCardGenerator());

    @Test
    @DisplayName("한명에게 카드 배분을 테스트")
    public void testGiveCardToOne() {
        //given
        Set<Card> cardSet = new HashSet<>();
        Player player = new Player(new Cards(cardSet), "player");

        //when
        cardDistributor.giveCard(player);

        //then
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("여러명에게 카드 배분을 테스트")
    public void testGiveCardToAll() {
        //given
        Set<Card> cardSet = new HashSet<>();
        Dealer dealer = new Dealer(new Cards(cardSet));
        Set<Card> cardSet1 = new HashSet<>();
        Player player1 = new Player(new Cards(cardSet1), "player1");
        Set<Card> cardSet2 = new HashSet<>();
        Player player2 = new Player(new Cards(cardSet2), "player2");

        //when
        cardDistributor.giveInitCards(dealer, List.of(player1, player2));

        //then
        assertThat(dealer.getCards().size()).isEqualTo(2);
        assertThat(player1.getCards().size()).isEqualTo(2);
        assertThat(player2.getCards().size()).isEqualTo(2);
    }
}
