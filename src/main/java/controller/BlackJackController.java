package controller;

import domain.AnswerCommand;
import domain.GameManager;
import domain.GameResult;
import domain.card.CardGroup;
import domain.card.Deck;
import domain.card.RandomCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.PlayerGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.LoopTemplate;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void gameStart() {
        final List<String> playerNames = LoopTemplate.tryCatchLoop(inputView::readPlayers);
        final PlayerGroup playerGroup = LoopTemplate.tryCatchLoop(() -> PlayerGroup.of(playerNames));
        final Dealer dealer = new Dealer(new CardGroup(new ArrayList<>()));
        final Deck deck = Deck.of(new RandomCardGenerator());
        final GameManager gameManager = new GameManager(dealer, playerGroup, deck);
        gameManager.shuffleCards();
        gameManager.giveCardsToDealer();
        gameManager.giveCardsToPlayers(playerNames);
        outputView.printDealerAndPlayersCards(dealer, playerGroup.getPlayers());
        requestHit(playerNames, playerGroup, gameManager);
        printDealerReceiveCardCount(gameManager);
        responseGameResult(dealer, playerGroup);
    }

    private void responseGameResult(final Dealer dealer, final PlayerGroup playerGroup) {
        final Map<String, GameResult> playersGameResult = playerGroup.calculatePlayersGameResult(dealer);
        outputView.printGamerCardsAndScore(dealer, playerGroup.getPlayers());
        outputView.printGameResult(dealer.calculateDealerGameResult(playersGameResult),
                playerGroup.calculatePlayersGameResult(dealer));
    }

    private void requestHit(final List<String> playerNames, final PlayerGroup playerGroup,
                            final GameManager gameManager) {
        for (String playerName : playerNames) {
            giveCardToPlayer(playerName, playerGroup, gameManager);
        }
    }

    private void giveCardToPlayer(final String playerName, final PlayerGroup playerGroup,
                                  final GameManager gameManager) {
        if (!playerGroup.isBustByPlayerName(playerName)) {
            receiveCardIsAbleToGetCard(playerName, playerGroup, gameManager);
        }
    }

    private void receiveCardIsAbleToGetCard(final String playerName, final PlayerGroup playerGroup,
                                            final GameManager gameManager) {
        if (requestAnswerCommand(playerName) == AnswerCommand.NO) {
            outputView.printPlayerCards(playerName, playerGroup.getCardsByName(playerName));
            return;
        }
        do {
            gameManager.giveOneCardToPlayerByName(playerName);
            outputView.printPlayerCards(playerName, playerGroup.getCardsByName(playerName));
        } while (playerGroup.isBustByPlayerName(playerName) && requestAnswerCommand(playerName) == AnswerCommand.YES);
    }

    private AnswerCommand requestAnswerCommand(final String playerName) {
        return LoopTemplate.tryCatchLoop(() -> inputView.readAnswer(playerName));
    }

    private void printDealerReceiveCardCount(final GameManager gameManager) {
        final int count = gameManager.giveCardsToDealer();
        if (count > 0) {
            outputView.printDealerReceivedCardCount(count);
        }
    }
}
