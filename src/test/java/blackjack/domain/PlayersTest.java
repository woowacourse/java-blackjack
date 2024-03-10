package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.dto.BlackjackResult;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("플레이어들")
public class PlayersTest {

    private final ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private Players players;
    private final String nameA = "a";
    private final String nameB = "b";

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
        players = Players.of(List.of(nameA, nameB), dealer);
    }

    @DisplayName("플레이어 이름이 중복되면 예외가 발생한다.")
    @Test
    void validateDuplicatedNames() {
        //given
        List<String> names = List.of("choco", "choco", "chip");

        //when & then
        assertThatThrownBy(() -> Players.of(names, dealer)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자간의 승패를 계산한다.")
    @Test
    void decideResult() {
        //given
        BlackjackResult blackjackResult = players.createResult(dealer);

        //when & then
        assertAll(
                () -> assertThat(blackjackResult.findPlayerResultByName(nameA)).isEqualTo(GameResult.LOSE),
                () -> assertThat(blackjackResult.findPlayerResultByName(nameB)).isEqualTo(GameResult.LOSE),
                () -> assertThat(blackjackResult.getDealerResult().getWins()).isEqualTo(2),
                () -> assertThat(blackjackResult.getDealerResult().getLoses()).isEqualTo(0)
        );
    }
}
