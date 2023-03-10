package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.OutputView;

public class ResultCalculatorTest {

    public static final Player PLAYER = new Player(new Name("dino"));
    public static final Dealer DEALER = new Dealer();

    @Test
    @DisplayName("대결 시 승패 여부가 올바르게 저장된다.")
    void shouldSuccessSaveFightWinOrLoseResult() {
        ResultCalculator resultCalculator = new ResultCalculator(new Players(List.of(PLAYER)), DEALER);
        PLAYER.drawCard(new Card("5다이아몬드", 5));
        PLAYER.drawCard(new Card("10다이아몬드", 10));
        DEALER.drawCard(new Card("3하트", 3));
        DEALER.drawCard(new Card("8클로버", 8));
        resultCalculator.fight(PLAYER, DEALER);

        List<Integer> playerResults = OutputView.getResultsByName(resultCalculator.getResults(), "dino");
        List<Integer> dealerResults = OutputView.getResultsByName(resultCalculator.getResults(), "딜러");

        assertAll(
                () -> assertThat(playerResults).isEqualTo(List.of(1, 0, 0)),
                () -> assertThat(dealerResults).isEqualTo(List.of(0, 0, 1))
        );
    }

    @Test
    @DisplayName("대결 시 무승부 여부가 올바르게 저장된다.")
    void shouldSuccessSaveFightDrawResult() {
        ResultCalculator resultCalculator = new ResultCalculator(new Players(List.of(PLAYER)), DEALER);
        PLAYER.drawCard(new Card("5다이아몬드", 5));
        PLAYER.drawCard(new Card("10다이아몬드", 10));
        DEALER.drawCard(new Card("10하트", 10));
        DEALER.drawCard(new Card("5클로버", 5));
        resultCalculator.fight(PLAYER, DEALER);

        List<Integer> playerResults = OutputView.getResultsByName(resultCalculator.getResults(), "dino");
        List<Integer> dealerResults = OutputView.getResultsByName(resultCalculator.getResults(), "딜러");

        assertSoftly(softly -> {
            softly.assertThat(playerResults).isEqualTo(List.of(0, 1, 0));
            softly.assertThat(dealerResults).isEqualTo(List.of(0, 1, 0));
        });
    }
}
