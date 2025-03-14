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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Players players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player1 = new Player("벡터");
        player2 = new Player("한스");
        players = new Players(List.of(player1, player2));
    }

    @Test
    void 참가자가_버스트면_딜러가_승리한다() {
        // given
        player1.putCard(new Card(CardShape.HEART, CardType.KING));
        player1.putCard(new Card(CardShape.HEART, CardType.QUEEN));
        player1.putCard(new Card(CardShape.HEART, CardType.NORMAL_5)); // 25점, 버스트

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);
        // then
        assertThat(result).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    void 딜러가_버스트면_참가자가_승리한다() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING));
        dealer.putCard(new Card(CardShape.HEART, CardType.QUEEN));
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_5)); // 25점, 버스트

        player1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        player1.putCard(new Card(CardShape.SPADE, CardType.QUEEN)); // 10점 -> 총 20점

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);
        // then
        assertThat(result).isEqualTo(PlayerResult.WIN);
    }

    @Test
    void 참가자의_점수가_딜러보다_높으면_승리한다() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING)); // 10점
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_6)); // 6점 -> 총 16점

        player1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        player1.putCard(new Card(CardShape.SPADE, CardType.NORMAL_8)); // 8점 -> 총 18점

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);
        // then
        assertThat(result).isEqualTo(PlayerResult.WIN);
    }

    @Test
    void 딜러의_점수가_참가자보다_높으면_딜러가_승리한다() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING)); // 10점
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_8)); // 8점 -> 총 18점

        player1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        player1.putCard(new Card(CardShape.SPADE, CardType.NORMAL_6)); // 6점 -> 총 16점

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);
        // then
        assertThat(result).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    void 딜러와_참가자의_점수가_같으면_무승부() {
        // given
        dealer.putCard(new Card(CardShape.HEART, CardType.KING)); // 10점
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_8)); // 8점 -> 총 18점

        player1.putCard(new Card(CardShape.SPADE, CardType.KING)); // 10점
        player1.putCard(new Card(CardShape.SPADE, CardType.NORMAL_8)); // 8점 -> 총 18점

        // when
        Map<Player, PlayerResult> playerPlayerResultMap = GameResult.calculateGameResult(dealer, players);
        PlayerResult result = playerPlayerResultMap.get(player1);
        // then
        assertThat(result).isEqualTo(PlayerResult.DRAW);
    }

}