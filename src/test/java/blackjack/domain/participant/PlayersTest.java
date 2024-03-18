package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.BlackjackCardsFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("플레이어 일급컬렉션을 생성한다")
    @Test
    public void create() {
        assertThatCode(() -> Players.createInitialPlayers(List.of(new Name("이상"), new Name("제이미짱"))))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 인원수가 1명 이상 4명 이하가 아니면 에러가 발생한다")
    @Test
    public void createFailByPlayersSize() {
        List<Name> playerNames = List.of(new Name("이상"), new Name("이이상"), new Name("이이이상"), new Name("제이미짱"),
                new Name("제이미짱짱"));
        assertThatCode(() -> Players.createInitialPlayers(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("플레이어는 %d명 이상 %d명 이하만 참여 가능합니다. 입력 인원수: %d", 1, 4, playerNames.size()));
    }

    @DisplayName("플레이어들의 핸드를 초기화한다")
    @Test
    public void initializePlayersHands() {
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        Players initialPlayers = Players.createInitialPlayers(List.of(new Name("이상"), new Name("이건상")));

        Players players = initialPlayers.takeFirstHand(deck);
        List<Player> initializedPlayers = players.getPlayers();

        Player player1 = initializedPlayers.get(0);
        Player player2 = initializedPlayers.get(1);

        assertThat(player1.calculateHand()).isNotEqualTo(Score.getZero());
        assertThat(player2.calculateHand()).isNotEqualTo(Score.getZero());
    }
}
