import domain.Blackjack;
import dto.GameResult;
import dto.ParticipantsResponse;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(final String[] args) {
        final List<String> names = InputView.inputPlayers();
        final List<Integer> betAmounts = names.stream()
                .map(InputView::inputBettingAmount)
                .toList();
        final Blackjack blackjack = Blackjack.startWithInitialization(names, betAmounts);

        OutputView.printInitialStatus(ParticipantsResponse.of(blackjack));

        blackjack.playersHit(InputView::tryHit, OutputView::printStatusWithNewLine);
        blackjack.dealerHit(OutputView::printDealerHitMessage);

        OutputView.printAfterStatus(ParticipantsResponse.of(blackjack));
        OutputView.printProfit(GameResult.of(blackjack));
    }
}
