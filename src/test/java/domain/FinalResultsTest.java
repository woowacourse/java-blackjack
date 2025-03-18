package domain;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.gamer.Betting;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Nickname;
import domain.gamer.Player;
import domain.gamer.Players;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalResultsTest {

    @DisplayName("플레이어들과 딜러의 수익을 구한다.")
    @Test
    void 플레이어들과_딜러의_수익을_구한다() {

        // given
        final Player player1 = new Player(new Nickname("체체"), new Betting(1000));
        final Player player2 = new Player(new Nickname("제이미"), new Betting(1500));
        final Player player3 = new Player(new Nickname("추추"), new Betting(1500));
        final List<Player> playerGroup = List.of(player1, player2, player3);
        final Players players = new Players(playerGroup);
        final Dealer dealer = new Dealer();

        player1.hit(new Card(Rank.ACE, Shape.SPADE));
        player1.hit(new Card(Rank.JACK, Shape.SPADE));
        player2.hit(new Card(Rank.ACE, Shape.SPADE));
        player2.hit(new Card(Rank.ACE, Shape.SPADE));
        player3.hit(new Card(Rank.ACE, Shape.SPADE));
        player3.hit(new Card(Rank.FIVE, Shape.SPADE));
        dealer.hit(new Card(Rank.ACE, Shape.SPADE));
        dealer.hit(new Card(Rank.FIVE, Shape.SPADE));

        final FinalResults finalResults = new FinalResults(players.createFinalResults(dealer));

        // when
        final Map<Gamer, Integer> profitResults = finalResults.createProfitResults(dealer);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(profitResults.get(dealer)).isEqualTo(0);
            softly.assertThat(profitResults.get(player1)).isEqualTo(1500);
            softly.assertThat(profitResults.get(player2)).isEqualTo(-1500);
            softly.assertThat(profitResults.get(player3)).isEqualTo(0);
        });
    }

}
