package blackjack;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Command;
import blackjack.domain.PlayerOutcome;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.dto.CardCountingResult;
import blackjack.dto.FirstTurnCards;
import blackjack.dto.PlayerCardResult;
import blackjack.dto.PlayerGameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlackjackGame {

    public void start() {
        BlackjackTable blackjackTable = new BlackjackTable(InputView.inputNames());
        OutputView.printFirstTurnCards(toFirstTurnCards(blackjackTable.getParticipants()));
        hit(blackjackTable);
        OutputView.printCardCountingResult(toCardCountingResult(blackjackTable.getParticipants()));
        OutputView.printGameResult(toGameResults(blackjackTable.countGameResult()));
    }

    private List<FirstTurnCards> toFirstTurnCards(List<Participant> participants) {
        return participants.stream()
            .map(participant -> new FirstTurnCards(participant.getName(), participant.openCard()))
            .collect(Collectors.toList());
    }

    private void hit(BlackjackTable blackjackTable) {
        hitPlayers(blackjackTable);
        hitDealer(blackjackTable);
    }

    private void hitPlayers(BlackjackTable blackjackTable) {
        List<Player> players = blackjackTable.getPlayers();
        for (Player player : players) {
            hitPlayer(blackjackTable, player);
        }
    }

    private void hitPlayer(BlackjackTable blackjackTable, Player player) {
        Command command = Command.find(InputView.inputCommand(player.getName()));
        if (command == Command.STAY) {
            OutputView.printPlayerCards(toPlayerCard(player));
            return;
        }
        moreHit(blackjackTable, player, command);
    }

    private void moreHit(BlackjackTable blackjackTable, Player player, Command command) {
        while (blackjackTable.canHit(player, command)) {
            blackjackTable.hit(player);
            OutputView.printPlayerCards(toPlayerCard(player));
            command = Command.find(InputView.inputCommand(player.getName()));
        }
    }

    private void hitDealer(BlackjackTable blackjackTable) {
        while (blackjackTable.needMoreCardByDealer()) {
            blackjackTable.hitDealer();
            OutputView.printReceivingMoreCardOfDealer();
        }
    }

    private PlayerCardResult toPlayerCard(Player player) {
        return new PlayerCardResult(player.getName(), player.getHoldCards().getCards());
    }

    private List<CardCountingResult> toCardCountingResult(List<Participant> participants) {
        return participants.stream()
            .map(this::toCardCountingResult)
            .collect(Collectors.toList());
    }

    private CardCountingResult toCardCountingResult(Participant participant) {
        return new CardCountingResult(
            participant.getName(),
            participant.getHoldCards().getCards(),
            participant.countCards()
        );
    }

    public List<PlayerGameResult> toGameResults(Map<PlayerOutcome, List<Player>> gameResults) {
        return gameResults.keySet().stream()
            .flatMap(outcome -> toPlayerGameResult(gameResults, outcome))
            .collect(Collectors.toList());
    }

    private Stream<PlayerGameResult> toPlayerGameResult(
        Map<PlayerOutcome, List<Player>> gameResult,
        PlayerOutcome outcome
    ) {
        return gameResult.get(outcome).stream()
            .map(player -> new PlayerGameResult(player.getName(), outcome));
    }
}
