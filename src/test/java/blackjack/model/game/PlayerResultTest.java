package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PlayerResultTest {

    @Test
    void 플레이어가_버스트가_아니고_참가자가_패배인_경우() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("프리");
        Player player2 = new Player("벡터");

        Players players = new Players(List.of(player1, player2));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        player1.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_2));

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    void 참가자가_버스트고_딜러가_버스트가_아닌_경우_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("프리");
        Player player2 = new Player("벡터");

        Players players = new Players(List.of(player1, player2));

        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        player1.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        player1.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        player1.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.LOSE);

    }

    @Test
    void 딜러가_버스트고_참가자가_버스트가_아닌_경우_참가자승리한다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("프리");
        Player player2 = new Player("벡터");

        Players players = new Players(List.of(player1, player2));

        player1.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.WIN);

    }

    @Test
    void 플레이어_모두_버스트인_경우_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("프리");
        Player player2 = new Player("벡터");

        Players players = new Players(List.of(player1, player2));

        player1.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));
        player1.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        player1.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        player1.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);        // then
        assertThat(result).isEqualTo(PlayerResult.LOSE);

    }
}
