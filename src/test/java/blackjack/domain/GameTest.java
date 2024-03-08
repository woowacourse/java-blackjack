package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    private static Game getGame() {
        CardFactory cardFactory = new CardFactory();
        List<Card> blackJackCard = cardFactory.createBlackJackCard();
        Deck deck = new Deck(blackJackCard);
        Dealer dealer = new Dealer(deck);
        Players players = new Players(List.of(new Player("마크"), new Player("이상")));
        return new Game(dealer, players);
    }

    @DisplayName("게임을 생성한다")
    @Test
    public void create() {
        assertThatCode(GameTest::getGame)
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어와 딜러의 핸드를 초기화한다")
    @Test
    public void initializeHand() {
        Game game = getGame();

        game.initializeHand();
        Dealer findDealer = game.getDealer();
        List<Player> findPlayers = game.getPlayers();

        assertThat(findDealer.calculate()).isNotZero();
        assertThat(findPlayers.stream().allMatch(player -> player.calculate() != 0)).isTrue();
    }

    @DisplayName("게임의 결과를 반환한다")
    @Test
    public void calculateResult() {
        Game game = getGame();
        game.initializeHand();

        Result result = game.calculateResult();
        Map<Player, ResultStatus> results = result.getResults();

        assertThat(results.size()).isEqualTo(game.getPlayers().size());

    }
}
