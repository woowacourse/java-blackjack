package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class GameResultTest {
    private GameResult gameResult;
    private Dealer dealer;
    private Player player;
    private List<Player> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player("bada");
        players.add(player);
        player.receiveOneCard(new Card(CardNumber.ACE, CardType.CLOVER));
        player.receiveOneCard(new Card(CardNumber.JACK, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.JACK, CardType.DIAMOND));
        dealer.receiveOneCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        gameResult = new GameResult(dealer, players);
    }

    @Test
    @DisplayName("GameResult가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new GameResult(dealer, players))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어의 승패를 잘 결정하는지 확인")
    void playersResult() {
        final List<Result> expected = new ArrayList<>(Arrays.asList(Result.WIN));
        assertThat(gameResult.getPlayersResult()).isEqualTo(expected);
    }

    @Test
    @DisplayName("승, 패, 무를 잘 카운트하는지 확인")
    void resultCounts() {
        final List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 0));
        assertThat(gameResult.getResultCounts()).isEqualTo(expected);
    }
}
