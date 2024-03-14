package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.dto.ProfitResult;
import blackjack.strategy.shuffle.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("플레이어들")
public class PlayersTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private Players players;
    private final String nameA = "choco";
    private final String nameB = "clover";
    private final String betting = "1000";

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
        players = Players.of(List.of(nameA, nameB), List.of(betting, betting), dealer);
    }

    @DisplayName("플레이어 이름이 중복되면 예외가 발생한다.")
    @Test
    void validateDuplicatedNames() {
        //given
        List<String> names = List.of("choco", "choco", "chip");
        List<String> bettings = List.of("1000", "1000", "1000");

        //when & then
        assertThatThrownBy(() -> Players.of(names, bettings, dealer))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어들의 승패를 토대로 수익률을 계산한다.")
    @Test
    void calculateProfits() {
        //given
        Player choco = players.getPlayers().get(0);
        choco.draw(dealer);
        Player clover = players.getPlayers().get(1);

        ProfitResult profitResult = new ProfitResult();
        profitResult.addProfitResult(choco, GameResult.WIN);
        profitResult.addProfitResult(clover, GameResult.LOSE);

        //when & then
        assertAll(
                () -> assertThat(players.createProfitResult(dealer).findByPlayer(choco)).isEqualTo(BigDecimal.valueOf(1000)),
                () -> assertThat(players.createProfitResult(dealer).findByPlayer(clover)).isEqualTo(BigDecimal.valueOf(-1000))
        );
    }
}
