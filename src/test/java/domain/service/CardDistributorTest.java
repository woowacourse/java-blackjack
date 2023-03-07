package domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.model.Card;
import domain.model.Cards;
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
    @DisplayName("한명에게 특정 카드 배분을 테스트")
    public void testGiveCardToOneSpecific() {
        //given
        final Card card = new Card(Suit.CLUB, Letter.ACE);
        cardDistributor = new CardDistributor(() -> card);
        final Player player = new Player(Cards.makeEmptyCards(), "player");

        //when
        cardDistributor.giveCard(player);

        //then
        assertThat(player.getCards().isEmpty()).isFalse();
        assertThat(player.getCards().stream().findFirst().isPresent()).isTrue();
        assertThat(player.getCards().stream().findFirst().get()).isEqualTo(card);
    }

    @Test
    @DisplayName("버스트가 난 한명에게 특정 카드 배분 테스트")
    public void testGiveCardToBustedOne() {
        //given
        cardDistributor = new CardDistributor(new RandomCardGenerator());
        final Cards cards = Cards.makeEmptyCards();
        cards.add(new Card(Suit.CLUB, Letter.TEN));
        cards.add(new Card(Suit.SPADE, Letter.TEN));
        cards.add(new Card(Suit.DIAMOND, Letter.TEN));
        final Player player = new Player(cards, "player");

        //when
        //then
        assertThatThrownBy(() -> cardDistributor.giveCard(player))
            .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("여러명에게 초기 카드 배분을 테스트")
    public void testInitGiveCardToAll() {
        //given
        cardDistributor = new CardDistributor(new RandomCardGenerator());
        final Set<Card> cardSet1 = new HashSet<>();
        final Player player1 = new Player(new Cards(cardSet1), "player1");
        final Set<Card> cardSet2 = new HashSet<>();
        final Player player2 = new Player(new Cards(cardSet2), "player2");

        //when
        cardDistributor.giveInitCards(List.of(player1, player2));

        //then
        assertThat(player1.getCards().size()).isEqualTo(2);
        assertThat(player2.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("한명에게 초기 카드 배분을 테스트")
    public void testInitGiveCardToOne() {
        //given
        cardDistributor = new CardDistributor(new RandomCardGenerator());
        final Player player = new Player(Cards.makeEmptyCards(), "player");

        //when
        cardDistributor.giveInitCards(player);

        //then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 카드 배분 시 카드가 이미 있을 경우 테스트")
    public void testInitGiveCardWhenCardIsNotEmpty() {
        //given
        cardDistributor = new CardDistributor(new RandomCardGenerator());
        final Cards cards = Cards.makeEmptyCards();
        cards.add(new Card(Suit.SPADE, Letter.ACE));
        final Player player = new Player(cards, "player");

        //when
        //then
        assertThatThrownBy(() -> cardDistributor.giveInitCards(player))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
