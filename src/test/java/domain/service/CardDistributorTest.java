package domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.model.Card;
import domain.model.Cards;
import domain.model.Player;
import domain.model.Players;
import domain.type.Letter;
import domain.type.Suit;
import domain.vo.Bet;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDistributorTest {

    private CardDistributor cardDistributor;

    @Test
    @DisplayName("한명에게 특정 카드 배분을 테스트")
    public void testGiveCardToOneSpecific() {
        //given
        final Card card = new Card(Suit.CLUB, Letter.ACE);
        cardDistributor = new CardDistributor(new CardGenerator() {
            @Override
            public void reset() {
            }

            @Override
            public Card generate() {
                return card;
            }

            @Override
            public List<Card> generate(final int size) {
                return null;
            }
        });
        final Player player = new Player(Cards.makeEmpty(), "player", Bet.of(1000D));

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
        final Cards cards = Cards.makeEmpty();
        cards.add(new Card(Suit.CLUB, Letter.TEN));
        cards.add(new Card(Suit.SPADE, Letter.TEN));
        cards.add(new Card(Suit.DIAMOND, Letter.TEN));
        final Player player = new Player(cards, "player", Bet.of(1000D));

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
        List<String> names = List.of("player1", "player2");
        List<Double> battings = List.of(1000D, 2000D);
        Players players = Players.from(names, battings);

        //when
        cardDistributor.giveInitCards(players);

        //then
        assertThat(players.get(0).getCards().size()).isEqualTo(2);
        assertThat(players.get(1).getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("한명에게 초기 카드 배분을 테스트")
    public void testInitGiveCardToOne() {
        //given
        cardDistributor = new CardDistributor(new RandomCardGenerator());
        final Player player = new Player(Cards.makeEmpty(), "player", Bet.of(1000D));

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
        final Cards cards = Cards.makeEmpty();
        cards.add(new Card(Suit.SPADE, Letter.ACE));
        final Player player = new Player(cards, "player", Bet.of(1000D));

        //when
        //then
        assertThatThrownBy(() -> cardDistributor.giveInitCards(player))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
