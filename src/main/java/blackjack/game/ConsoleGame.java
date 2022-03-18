package blackjack.game;

import blackjack.domain.card.DeckGenerator;
import blackjack.domain.card.RandomGenerator;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleGame {

    private final RandomGenerator randomGenerator;

    public ConsoleGame(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame(randomGenerator);
        blackjackGame.initCardsAllParticipants();

        Participants participants = blackjackGame.getParticipants();
        OutputView.printInitialCards(participants);

        playPlayersTurn(blackjackGame, participants);
        playDealerTurn(blackjackGame, participants);

        showGameResult(blackjackGame, participants);
    }

    private BlackjackGame createBlackjackGame(DeckGenerator deckGenerator) {
        try {
            List<Name> playerNames = InputView.inputPlayerNames();
            Map<Name, BettingAmount> participantInfos = getBettingAmounts(playerNames);
            return new BlackjackGame(participantInfos, deckGenerator);

        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return createBlackjackGame(deckGenerator);
        }
    }

    private Map<Name, BettingAmount> getBettingAmounts(List<Name> names) {
        Map<Name, BettingAmount> map = new HashMap<>();
        for (Name name : names) {
            map.put(name, inputBettingAmounts(name));
        }
        return map;
    }

    private BettingAmount inputBettingAmounts(Name name) {
        try {
            return InputView.inputBettingAmount(name);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputBettingAmounts(name);
        }
    }

    private void playPlayersTurn(BlackjackGame blackjackGame, Participants participants) {
        for (Player player : participants.getPlayers()) {
            playPlayerTurn(blackjackGame, player);
        }
    }

    private void playPlayerTurn(BlackjackGame blackjackGame, Player player) {
        while (!player.isFinished() && !inputCommand(player).isStay()) {
            blackjackGame.drawCard(player);
            OutputView.printCards(player);
        }
    }

    private Command inputCommand(Participant player) {
        try {
            return InputView.inputWantDraw(player.getName());

        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputCommand(player);
        }
    }

    private void playDealerTurn(BlackjackGame blackjackGame, Participants participants) {
        Dealer dealer = participants.getDealer();

        while (!dealer.isFinished()) {
            OutputView.printDealerDrawInfo(dealer.getName());
            blackjackGame.drawCard(dealer);
        }
    }

    private void showGameResult(BlackjackGame blackjackGame, Participants participants) {
        GameResult gameResult = blackjackGame.createGameResult();
        OutputView.printCardsResult(participants.getDealer(), participants.getPlayers());
        OutputView.printGameResult(gameResult);
    }
}
