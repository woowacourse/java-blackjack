package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.view.dto.ExecutingPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerActionExecutorTest {
    @Test
    @DisplayName("대기열 가장 앞에 있는 플레이어의 HIT을 수행한다.")
    public void executeHitTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.FIVE)
        );
        CardGenerator cardGenerator = new SequentialCardGenerator(cards);
        Players players = new Players(List.of("mia", "dora"), cardGenerator);
        PlayerActionExecutor playerActionExecutor = new PlayerActionExecutor(players, cardGenerator);

        // when
        playerActionExecutor.execute(PlayerAction.HIT);

        // then
        ExecutingPlayer executingPlayer = playerActionExecutor.getExecutingPlayer();
        assertThat(executingPlayer.cards()).isEqualTo("2하트, 5하트, 5하트");
    }

    @Test
    @DisplayName("대기열 가장 앞에 있는 플레이어가 STAND라면 대기열에서 제거된다.")
    public void executeStandTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.FIVE)
        );
        CardGenerator cardGenerator = new SequentialCardGenerator(cards);
        Players players = new Players(List.of("mia", "dora"), cardGenerator);
        PlayerActionExecutor playerActionExecutor = new PlayerActionExecutor(players, cardGenerator);

        // when
        playerActionExecutor.execute(PlayerAction.STAND);

        // then
        ExecutingPlayer executingPlayer = playerActionExecutor.getExecutingPlayer();
        assertThat(executingPlayer.name()).isEqualTo("dora");
    }
}
