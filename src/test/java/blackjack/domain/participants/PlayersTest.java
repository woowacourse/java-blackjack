package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.running.Hit;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void 플레이어_수가_최대_10명을_넘으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player("poba", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobg", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobb", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobh", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobc", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobi", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobd", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobj", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobe", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobk", new Hit(new Cards()), new BettingMoney(1000)),
                new Player("pobf", new Hit(new Cards()), new BettingMoney(1000))
        );

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최대 10명입니다.");
    }

    @Test
    void 플레이어_수가_최소_2명을_넘지_않으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player("pobi", new Hit(new Cards()), new BettingMoney(1000))
        );

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최소 2명입니다.");
    }

    @Test
    void 플레이어들의_총_수익을_알_수_있다() {
        //given
        Dealer dealer = new Dealer(new Cards(new Card(Suit.DIAMOND, Rank.NINE)));
        Players players = new Players(
                new Player("pobi", new Stay(new Cards(new Card(Suit.DIAMOND, Rank.TEN))), new BettingMoney(1000)),
                new Player("neo", new Stay(new Cards(new Card(Suit.DIAMOND, Rank.TEN))), new BettingMoney(2000))
        );

        //when
        int profit = players.calculateTotalProfit(dealer.getCards());

        //then
        assertThat(profit).isEqualTo(3000);
    }

    @Test
    void 플레이어들_각각의_수익을_알_수_있다() {
        //given
        Dealer dealer = new Dealer(new Cards(new Card(Suit.DIAMOND, Rank.NINE)));
        Player pobi = new Player("pobi", new Stay(new Cards(new Card(Suit.DIAMOND, Rank.TEN))), new BettingMoney(1000));
        Player neo = new Player("neo", new Stay(new Cards(new Card(Suit.DIAMOND, Rank.TEN))), new BettingMoney(2000));
        Players players = new Players(pobi, neo);

        //when
        Map<Player, Integer> profit = players.calculateAllProfit(dealer);

        //then
        assertThat(profit).isEqualTo(Map.of(
                pobi, 1000,
                neo, 2000
        ));
    }
}
