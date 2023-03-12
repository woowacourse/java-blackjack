package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.service.BlackJackRule;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Application {

    public static void main(final String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final List<String> playerNames = inputPlayerNames(inputView);
        final List<Integer> moneys = new ArrayList<>();
        for (final String playerName : playerNames) {
            moneys.add(inputPlayerMoney(inputView, playerName));
        }
        final Deck deck = new ShuffledDeckFactory().generate();
        final Participants participants = Participants.of(playerNames, moneys);
        participants.distributeInitialCards(deck);
        outputView.printInitialCards(participants.getDealerFirstCard(), participants.getPlayersCards());

        for (final String playerName : participants.getPlayerNames()) {
            DrawCommand playerChoice = DrawCommand.DRAW;
            while (participants.isPlayerDrawable(playerName) && playerChoice != DrawCommand.STAY) {
                playerChoice = inputPlayerChoice(playerName, inputView);
                if (playerChoice == DrawCommand.DRAW) {
                    participants.drawPlayerCard(playerName, deck.popCard());
                }
                outputView.printCardStatusOfPlayer(playerName, participants.getPlayerCards(playerName));
            }
        }
        while (participants.isDealerDrawable()) {
            participants.drawDealerCard(deck.popCard());
            outputView.printDealerCardDrawMessage();
        }

        outputView.printFinalStatusOfDealer(participants.getDealerScore(),
                participants.getDealerCards());
        outputView.printFinalStatusOfPlayers(participants.getPlayersCards(),
                participants.getPlayersScores());

        final Map<String, Integer> playerMoney = new LinkedHashMap<>();
        for (final Player player : participants.getPlayers()) {
            final int resultMoney = new BlackJackRule().calculatePlayerProfit(player, participants.getDealer());
            playerMoney.put(player.getName(), resultMoney);
        }
        outputView.printFinalMoney(playerMoney);
    }

    private static List<String> inputPlayerNames(final InputView inputView) {
        return repeatUntilNoException(() -> {
                    final List<String> names = inputView.inputPlayerNames();
                    Participants.validatePlayerNames(names);
                    return names;
                },
                inputView::printInputError);
    }

    private static int inputPlayerMoney(final InputView inputView,
            final String playerName) {
        return repeatUntilNoException(() -> {
                    final int amount = inputView.inputPlayerMoney(playerName);
                    Participants.validateBettingMoney(amount);
                    return amount;
                },
                inputView::printInputError);
    }

    private static DrawCommand inputPlayerChoice(final String playerName, final InputView inputView) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), inputView::printInputError);
    }

    private static <T> T repeatUntilNoException(final Supplier<T> supplier,
            final Consumer<Exception> exceptionHandler) {
        while (true) {
            try {
                return supplier.get();
            } catch (final IllegalArgumentException e) {
                exceptionHandler.accept(e);
            }
        }
    }
}
