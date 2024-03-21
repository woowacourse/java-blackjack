package domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    private final int MAX_PLAYER_NUMBER = 8;

    @Test
    @DisplayName("플레이어 수가 8명 초과하면 예외가 발생한다")
    void playerSize() {
        final List<Player> players = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            players.add(new Player(new Name("teba" + i), new BetAmount(100), new Card(Rank.NINE, Suit.CLUBS),
                    new Card(Rank.NINE, Suit.CLUBS)));
        }

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("플레이어의 수는 %d명을 초과할 수 없습니다.", MAX_PLAYER_NUMBER));
    }

    @Test
    @DisplayName("플레이어의 이름이 겹치면 예외가 발생한다")
    void duplicateName() {
        final List<Player> players = new ArrayList<>();

        players.add(new Player(new Name("teba"), new BetAmount(100),new Card(Rank.NINE,Suit.CLUBS),new Card(Rank.NINE,Suit.CLUBS)));
        players.add(new Player(new Name("teba"), new BetAmount(100),new Card(Rank.NINE,Suit.CLUBS),new Card(Rank.NINE,Suit.CLUBS)));

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다");
    }

    @Test
    @DisplayName("이름과 배팅금액 정보의 길이가 다르면 예외가 발생한다")
    void length() {
        final List<String> names = List.of("a", "b", "c");
        final List<Integer> betAmounts = List.of(100, 200);
        assertThatThrownBy(() -> Players.of(names, betAmounts,new Card(Rank.NINE,Suit.CLUBS),new Card(Rank.NINE,Suit.CLUBS)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이름(%d)과 배팅 금액(%d)의 정보의 길이가 일치하지 않습니다.", names.size(), betAmounts.size()));
    }

    @Test
    @DisplayName("사용자들의 수익 총합을 계산한다")
    void totalSum() {
        final Player player1 = new Player(new Name("a"), new BetAmount(100),new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        final Player player2 = new Player(new Name("b"), new BetAmount(100),new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        final Dealer dealer = new Dealer(new Card(Rank.TWO, Suit.CLUBS), new Card(Rank.TWO, Suit.CLUBS));
        player1.standIfRunning();
        player2.standIfRunning();
        dealer.standIfRunning();
        final Players players = new Players(List.of(player1, player2));

        final double totalSum = players.getTotalSum(dealer);

        assertThat(totalSum).isEqualTo(250);
    }
}
