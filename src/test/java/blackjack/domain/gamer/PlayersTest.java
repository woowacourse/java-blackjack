package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.money.Chip;
import blackjack.domain.money.PlayersProfit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어들")
public class PlayersTest {
    @Test
    @DisplayName("플레이어 여러명을 생성한다.")
    void createPlayers() {
        // given & when
        Player player1 = new Player(new Name("lemone"), new Chip(0L));
        Player player2 = new Player(new Name("seyang"), new Chip(0L));
        Player player3 = new Player(new Name("pobi"), new Chip(0L));
        Players players = new Players(List.of(player1, player2, player3));

        // then
        assertThat(players.names()).isEqualTo(List.of("lemone", "seyang", "pobi"));
    }

    @Test
    @DisplayName("플레이어 전체에게 draw를 한다.")
    void drawForAllPlayers() {
        // given
        Player player1 = new Player(new Name("lemone"), new Chip(0L));
        Player player2 = new Player(new Name("seyang"), new Chip(0L));
        Player player3 = new Player(new Name("pobi"), new Chip(0L));
        Players players = new Players(List.of(player1, player2, player3));
        List<Card> cards = List.of(Card.of(Rank.TEN, Suit.DIAMOND), Card.of(Rank.ACE, Suit.SPADE));
        Supplier<List<Card>> pickCard = () -> cards;

        // when
        players.draw(pickCard);

        // then
        assertThat(players.values().get(0).cards()).isEqualTo(cards);
        assertThat(players.values().get(1).cards()).isEqualTo(cards);
        assertThat(players.values().get(2).cards()).isEqualTo(cards);
    }

    @Test
    @DisplayName("플레이어 전체 profit을 계산한다.")
    void allProfit() {
        // given
        Player player1 = new Player(new Name("lemone"), new Chip(100L));
        Player player2 = new Player(new Name("seyang"), new Chip(2000L));
        Dealer dealer = new Dealer(new Chip(0L));
        Players players = new Players(List.of(player1, player2));

        player1.draw(Card.of(Rank.ACE, Suit.SPADE));
        player2.draw(Card.of(Rank.TWO, Suit.SPADE));
        dealer.draw(Card.of(Rank.NINE, Suit.SPADE));

        // when
        PlayersProfit actual = players.calculateProfit(dealer);

        // then
        assertThat(actual.findProfitBy(player1))
                .isEqualTo(100L);

        assertThat(actual.findProfitBy(player2))
                .isEqualTo(-2000L);
    }
}
