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

public class ResultsTest {

    public static final Player DINO = new Player(new Name("dino"));
    public static final Player SUNGHA = new Player(new Name("sungha"));
    public static final Player POBI = new Player(new Name("pobi"));
    public static final Dealer DEALER = new Dealer();

    @Test
    @DisplayName("대결 시 승패 여부가 올바르게 저장된다.")
    void shouldSuccessSaveFightWinOrLoseResult() {
        Results resultCalculator = new Results(new Players(List.of(DINO)), DEALER);
        DINO.drawCard(new Card("5다이아몬드", 5));
        DINO.drawCard(new Card("10다이아몬드", 10));
        DEALER.drawCard(new Card("3하트", 3));
        DEALER.drawCard(new Card("8클로버", 8));
        resultCalculator.fight(DINO, DEALER);

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
        Results resultCalculator = new Results(new Players(List.of(DINO)), DEALER);
        DINO.drawCard(new Card("5다이아몬드", 5));
        DINO.drawCard(new Card("10다이아몬드", 10));
        DEALER.drawCard(new Card("10하트", 10));
        DEALER.drawCard(new Card("5클로버", 5));
        resultCalculator.fight(DINO, DEALER);

        List<Integer> playerResults = OutputView.getResultsByName(resultCalculator.getResults(), "dino");
        List<Integer> dealerResults = OutputView.getResultsByName(resultCalculator.getResults(), "딜러");

        assertSoftly(softly -> {
            softly.assertThat(playerResults).isEqualTo(List.of(0, 1, 0));
            softly.assertThat(dealerResults).isEqualTo(List.of(0, 1, 0));
        });
    }

    @Test
    @DisplayName("대결 시 딜러가 Bust면 모든 참가자가 승리한다.")
    void everyPlayerWinWhenDealerBust() {
        Players players = new Players(List.of(DINO, SUNGHA, POBI));
        Results resultCalculator = new Results(players, DEALER);
        DINO.drawCard(new Card("5다이아몬드", 5));
        DINO.drawCard(new Card("10다이아몬드", 10));
        DINO.drawCard(new Card("10스페이드", 10));

        DEALER.drawCard(new Card("10하트", 10));
        DEALER.drawCard(new Card("5클로버", 5));
        DEALER.drawCard(new Card("7다이아몬드", 7));

        SUNGHA.drawCard(new Card("2하트", 2));
        SUNGHA.drawCard(new Card("7스페이드", 7));

        POBI.drawCard(new Card("6다이아몬드", 6));
        POBI.drawCard(new Card("8클로버", 8));

        resultCalculator.executeGame(players, DEALER);

        List<Integer> dinoResult = OutputView.getResultsByName(resultCalculator.getResults(), "dino");
        List<Integer> sunghaResult = OutputView.getResultsByName(resultCalculator.getResults(), "sungha");
        List<Integer> pobiResult = OutputView.getResultsByName(resultCalculator.getResults(), "pobi");
        List<Integer> dealerResults = OutputView.getResultsByName(resultCalculator.getResults(), "딜러");

        assertSoftly(softly -> {
            softly.assertThat(dinoResult).isEqualTo(List.of(1, 0, 0));
            softly.assertThat(sunghaResult).isEqualTo(List.of(1, 0, 0));
            softly.assertThat(pobiResult).isEqualTo(List.of(1, 0, 0));
            softly.assertThat(dealerResults).isEqualTo(List.of(0, 0, 3));
        });
    }
}
