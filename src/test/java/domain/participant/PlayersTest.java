package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class PlayersTest {

    @DisplayName("플레이어 수가 8명 초과하면 예외를 발생한다")
    @Test
    void playerSize() {
        final List<Player> players = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            players.add(new Player(new Name("teba" + i), new BetAmount(100)));
        }

        assertThatCode(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어 전원의 수익률을 반환한다.")
    @Test
    void totalProfit() {
        // given
        final List<Player> players = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            final Player player = new Player(new Name("teba" + i), new BetAmount(1000));
            player.drawCards(
                    List.of(new Card(Denomination.KING, Suit.DIAMOND),
                            new Card(Denomination.TWO, Suit.DIAMOND)));
            player.stand();
            players.add(player);
        }
        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCards(
                List.of(new Card(Denomination.KING, Suit.CLUBS),
                        new Card(Denomination.ACE, Suit.CLUBS)));
        // when
        final int totalProfit = new Players(players).totalProfit(dealer);
        //then
        assertThat(totalProfit).isEqualTo(-8000);
    }
}
