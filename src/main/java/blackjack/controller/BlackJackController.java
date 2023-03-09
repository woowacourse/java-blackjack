package blackjack.controller;

import static blackjack.controller.DomainConverter.convertCard;
import static blackjack.controller.DomainConverter.convertCards;
import static blackjack.controller.DomainConverter.convertPlayersCards;
import static blackjack.controller.DomainConverter.getPlayerCards;
import static blackjack.util.Repeater.repeatUntilNoException;

import blackjack.domain.BlackJackRuleImpl;
import blackjack.domain.card.DeckFactory;
import blackjack.service.BlackJackGame;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    //이 메서드는 10라인도 훨씬 넘었고, 되게 복잡하게 되어있는데, 이렇게 두는 것이 좋은가요?
    //원래대로 돌렸을 경우에, 훨씬 간단해보였던 것 같아요
//    public void play(final DeckFactory deckFactory) {
//        final BlackJackGame blackJackGame = repeatUntilNoException(
//                () -> BlackJackGame.of(
//                        inputPlayerNames(),
//                        deckFactory,
//                        new BlackJackRuleImpl()),
//                outputView::printError);
//
//        for (final String playerName : blackJackGame.getPlayerNames()) {
//            blackJackGame.addPlayerMoney(playerName, inputPlayerMoney(playerName));
//        }
//
//        blackJackGame.distributeInitialCard();
//
//        outputView.printInitialCards(
//                convertCard(blackJackGame.getDealerFirstCard()),
//                getPlayerCards(blackJackGame.getPlayers()));
//
//        for (final String playerName : blackJackGame.getPlayerNames()) {
//            DrawCommand playerChoice = DrawCommand.DRAW;
//            while (blackJackGame.isPlayerDrawable(playerName) && playerChoice != DrawCommand.STAY) {
//                playerChoice = inputPlayerChoice(playerName);
//                if (playerChoice == DrawCommand.DRAW) {
//                    blackJackGame.drawPlayerCard(playerName);
//                }
//                outputView.printCardStatusOfPlayer(playerName, convertCards(blackJackGame.getPlayerCards(playerName)));
//            }
//        }
//
//        while (blackJackGame.isDealerDrawable()) {
//            blackJackGame.drawDealerCard();
//            outputView.printDealerCardDrawMessage();
//        }
//
//        outputView.printFinalStatusOfDealer(blackJackGame.getDealerScore(),
//                convertCards(blackJackGame.getDealerCards()));
//        outputView.printFinalStatusOfPlayers(convertPlayersCards(blackJackGame.getPlayersCards()),
//                blackJackGame.getPlayersScores());
//
//        outputView.printFinalMoney(blackJackGame.calculatePlayersMoney());
//    }

    //한 메서드가 너무 길어지는 느낌이 드는 것 같은데요 이것은 어떤가요?
    public void play(final DeckFactory deckFactory) {
        final BlackJackGame blackJackGame = repeatUntilNoException(
                () -> BlackJackGame.of(
                        inputPlayerNames(),
                        deckFactory,
                        new BlackJackRuleImpl()),
                outputView::printError);
        for (final String playerName : blackJackGame.getPlayerNames()) {
            blackJackGame.addPlayerMoney(playerName, inputPlayerMoney(playerName));
        }

        blackJackGame.distributeInitialCard();
        outputView.printInitialCards(
                convertCard(blackJackGame.getDealerFirstCard()),
                getPlayerCards(blackJackGame.getPlayers()));

        for (final String playerName : blackJackGame.getPlayerNames()) {
            drawPlayerCard(blackJackGame, playerName);
        }
        while (blackJackGame.isDealerDrawable()) {
            blackJackGame.drawDealerCard();
            outputView.printDealerCardDrawMessage();
        }

        outputView.printFinalStatusOfDealer(blackJackGame.getDealerScore(),
                convertCards(blackJackGame.getDealerCards()));
        outputView.printFinalStatusOfPlayers(convertPlayersCards(blackJackGame.getPlayersCards()),
                blackJackGame.getPlayersScores());
        outputView.printFinalMoney(blackJackGame.calculatePlayersMoney());
    }

    private void drawPlayerCard(final BlackJackGame blackJackGame, final String playerName) {
        DrawCommand playerChoice = DrawCommand.DRAW;
        while (blackJackGame.isPlayerDrawable(playerName) && playerChoice != DrawCommand.STAY) {
            playerChoice = inputPlayerChoice(playerName);
            if (playerChoice == DrawCommand.DRAW) {
                blackJackGame.drawPlayerCard(playerName);
            }
            outputView.printCardStatusOfPlayer(playerName, convertCards(blackJackGame.getPlayerCards(playerName)));
        }
    }

    private int inputPlayerMoney(final String playerName) {
        return repeatUntilNoException(
                () -> inputView.inputPlayerMoney(playerName), outputView::printError);
    }

    private DrawCommand inputPlayerChoice(final String playerName) {
        return repeatUntilNoException(
                () -> inputView.inputCommand(playerName), outputView::printError);
    }

    private List<String> inputPlayerNames() {
        return inputView.inputPlayerNames();
    }
}
