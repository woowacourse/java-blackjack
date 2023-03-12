package blackjack.controller;

import blackjack.controller.exception.InvalidCommandException;
import blackjack.domain.card.exception.NoMoreCardException;
import blackjack.domain.exception.CustomException;
import blackjack.domain.game.BettingMoney;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackResult;
import blackjack.domain.game.exception.InvalidMoneyValueException;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();
        start(blackjackGame);
        printResult(blackjackGame);
    }

    private BlackjackGame createBlackjackGame() {
        Participants participants = createParticipants();
        return new BlackjackGame(participants);
    }

    private void start(BlackjackGame blackjackGame) {
        try {
            Participants participants = blackjackGame.getParticipants();
            bet(participants.getPlayers(), blackjackGame);
            dealOutInitCards(blackjackGame, participants);
            play(participants, blackjackGame);
            outputView.printCardResult(participants);
        } catch (NoMoreCardException e) {
            outputView.printError(e.getErrorCode());
        }
    }

    private void bet(List<Player> players, BlackjackGame blackjackGame) {
        for (Player player : players) {
            BettingMoney money = createBettingMoney(player);
            blackjackGame.bet(player, money);
        }
    }

    private BettingMoney createBettingMoney(Player player) {
        try {
            int money = inputView.readBettingMoney(player);
            return new BettingMoney(money);
        } catch (InvalidMoneyValueException e) {
            outputView.printError(e.getErrorCode());
            return createBettingMoney(player);
        }
    }

    private void dealOutInitCards(BlackjackGame blackjackGame, Participants participants) {
        blackjackGame.dealOutCard();
        outputView.printInitCards(participants);
    }

    private void printResult(BlackjackGame blackjackGame) {
        BlackjackResult result = blackjackGame.getResult();
        outputView.printGameResult(result);
    }

    private Participants createParticipants() {
        try {
            List<String> names = inputView.readNames();
            return Participants.from(names);
        } catch (CustomException e) {
            outputView.printError(e.getErrorCode());
            return createParticipants();
        }
    }

    private void play(Participants participants, BlackjackGame blackjackGame) {
        for (Player player : participants.getPlayers()) {
            playPerPlayer(player, blackjackGame);
        }
        Dealer dealer = participants.getDealer();
        playDealer(dealer, blackjackGame);
    }

    private void playPerPlayer(Player player, BlackjackGame blackjackGame) {
        GameCommand command = GameCommand.PLAY;
        while (player.isDrawable() && command.isPlay()) {
            command = getGameCommand(player);
            giveCard(player, blackjackGame, command);
            outputView.printPlayerCards(player);
        }
    }

    private GameCommand getGameCommand(Player player) {
        try {
            String inputCommand = inputView.readIsContinue(player.getName());
            return GameCommand.from(inputCommand);
        } catch (InvalidCommandException e) {
            outputView.printError(e.getErrorCode());
            return getGameCommand(player);
        }
    }

    private void giveCard(Player player, BlackjackGame blackjackGame, GameCommand command) {
        if (command.isPlay()) {
            blackjackGame.drawCard(player);
        }
    }

    private void playDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.isDrawable()) {
            outputView.printDealerState();
            blackjackGame.drawCard(dealer);
        }
    }
}
