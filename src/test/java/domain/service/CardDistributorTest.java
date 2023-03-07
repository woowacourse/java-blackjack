package domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Card;
import domain.model.Cards;
import domain.model.Dealer;
import domain.model.Player;
import domain.type.Letter;
import domain.type.Suit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDistributorTest {

    private CardDistributor cardDistributor;

    @Test
    @DisplayName("한명에게 카드 배분을 테스트")
    public void testGiveCardToOne() {
        //given
        cardDistributor = new CardDistributor(new RandomCardGenerator());
        final Set<Card> cardSet = new HashSet<>();
        final Player player = new Player(new Cards(cardSet), "player");

        //when
        cardDistributor.giveCard(player);

        //then
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("한명에게 특정 카드 배분을 테스트")
    public void testGiveCardToOneSpecific() {
        //given
        final Card card = new Card(Suit.CLUB, Letter.ACE);
        cardDistributor = new CardDistributor(() -> card);
        final Set<Card> cardSet = new HashSet<>();
        final Player player = new Player(new Cards(cardSet), "player");

        //when
        cardDistributor.giveCard(player);

        //then
        assertThat(player.getCards().isEmpty()).isFalse();
        assertThat(player.getCards().stream().findFirst().isPresent()).isTrue();
        assertThat(player.getCards().stream().findFirst().get()).isEqualTo(card);
    }


    @Test
    @DisplayName("여러명에게 카드 배분을 테스트")
    public void testGiveCardToAll() {
        //given
        cardDistributor = new CardDistributor(new RandomCardGenerator());
        final Set<Card> cardSet = new HashSet<>();
        final Dealer dealer = new Dealer(new Cards(cardSet));
        final Set<Card> cardSet1 = new HashSet<>();
        final Player player1 = new Player(new Cards(cardSet1), "player1");
        final Set<Card> cardSet2 = new HashSet<>();
        final Player player2 = new Player(new Cards(cardSet2), "player2");

        //when
        cardDistributor.giveInitCards(dealer, List.of(player1, player2));

        //then
        assertThat(dealer.getCards().size()).isEqualTo(2);
        assertThat(player1.getCards().size()).isEqualTo(2);
        assertThat(player2.getCards().size()).isEqualTo(2);
    }
}
