package blackjack;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Command;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.dto.request.PlayerRequest;
import blackjack.dto.response.CardCountingResult;
import blackjack.dto.response.PlayerCardResult;
import blackjack.dto.response.PlayerGameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    public void start() {
        BlackjackTable blackjackTable = new BlackjackTable(toPlayerRequest(InputView.inputNames()));
        OutputView.printFirstTurnCards(toFirstTurnCards(blackjackTable.getParticipants()));
        hit(blackjackTable);
        OutputView.printCardCountingResult(toCardCountingResult(blackjackTable.getParticipants()));
        OutputView.printGameResult(toGameResults(blackjackTable.countGameResult()));
    }

    private List<PlayerRequest> toPlayerRequest(List<String> names) {
        return names.stream()
            .map(InputView::inputBettingMoney)
            .collect(Collectors.toList());
    }

    private List<PlayerCardResult> toFirstTurnCards(List<Participant> participants) {
        return participants.stream()
            .map(participant -> new PlayerCardResult(participant.getName(), participant.openCard()))
            .collect(Collectors.toList());
    }

    private void hit(BlackjackTable blackjackTable) {
        for (Participant participant : blackjackTable.getPlayers()) {
            hitPlayer(blackjackTable, participant);
        }
        hitDealer(blackjackTable);
    }

    private void hitPlayer(BlackjackTable blackjackTable, Participant participant) {
        if (participant.hasBlackjack()) {
            return;
        }
        hitOrStay(blackjackTable, participant);
    }

    private void hitOrStay(BlackjackTable blackjackTable, Participant participant) {
        Command command = Command.find(InputView.inputCommand(participant.getName()));
        if (command.isStay()) {
            OutputView.printPlayerCards(toPlayerCard(participant));
            return;
        }
        moreHit(blackjackTable, participant);
    }

    private void moreHit(BlackjackTable blackjackTable, Participant participant) {
        blackjackTable.hit(participant);
        OutputView.printPlayerCards(toPlayerCard(participant));
        if (!participant.canHit()) {
            return;
        }
        hitOrStay(blackjackTable, participant);
    }

    private void hitDealer(BlackjackTable blackjackTable) {
        Participant dealer = blackjackTable.getDealer();
        while (dealer.canHit()) {
            blackjackTable.hit(dealer);
            OutputView.printReceivingMoreCardOfDealer();
        }
    }

    private PlayerCardResult toPlayerCard(Participant participant) {
        return new PlayerCardResult(participant.getName(), participant.getHoldCards().getCards());
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

    private List<PlayerGameResult> toGameResults(Map<Player, Integer> countGameResult) {
        return countGameResult.keySet().stream()
            .map(player -> new PlayerGameResult(player.getName(), countGameResult.get(player)))
            .collect(Collectors.toList());
    }
}
