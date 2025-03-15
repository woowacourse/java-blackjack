package blackjack;

import blackjack.participant.Dealer;
import blackjack.participant.GameParticipant;
import blackjack.participant.GameParticipants;
import blackjack.participant.Nickname;
import blackjack.participant.Player;
import blackjack.result.Betting;
import blackjack.result.GameStatistics;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.RepeatUntilCorrectInput;

import java.util.List;

public class BlackjackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        GameParticipants participants = initializeGameParticipants();

        participants.dealInitialCards();
        outputView.printCards(participants);

        participants.executeHitPhase();
        outputView.printCardsWithSum(participants);

        GameStatistics gameStatistics = participants.calculateGameStatistics();
        outputView.printProfit(participants, gameStatistics);
    }

    private GameParticipants initializeGameParticipants() {
        List<Player> players = inputView.readNicknames().stream()
                .map(nickname ->
                        Player.of(Nickname.from(nickname),
                                Betting.from(inputView.readBetting(nickname)),
                                this::askHit,
                                this::showHands))
                .toList();

        Dealer dealer = Dealer.create(players.size(),
                this::displayHitDecision);

        return GameParticipants.of(players, dealer);
    }

    private boolean askHit(Player player) {
        return RepeatUntilCorrectInput.repeat(() -> inputView.readShouldHit(player), outputView);
    }

    private void showHands(GameParticipant participant) {
        outputView.printParticipantCards(participant);
    }

    private void displayHitDecision() {
        outputView.printDealerHit();
    }
}
