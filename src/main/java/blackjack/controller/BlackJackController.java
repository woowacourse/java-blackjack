package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.dto.PlayerNameCardsResponse;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    private static final String CARD_RECEIVE_INTENTION = "y";

    private final InputView inputView;
    private final OutputView outputView;
    private BlackJackGame blackJackGame;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        newBlackJackGame();

        passInitialCard();

        passExtraCard();

        finishGame();
    }

    private void newBlackJackGame() {
        try {
            List<String> names = inputView.readNames();
            blackJackGame = BlackJackGame.createByPlayerNames(names);
        } catch (IllegalArgumentException e) {
            outputView.showError(e.getMessage());
            newBlackJackGame();
        }
    }

    private void passInitialCard() {
        blackJackGame.setUp();
        outputView.showInitStatus(blackJackGame.findAllPlayerNames());
        outputView.showDealerFirstCard(blackJackGame.findDealerFirstCard());
        outputView.showAllPlayerNameCards(blackJackGame.findAllPlayerNameAndCards());
    }

    private void passExtraCard() {
        passExtraCardToPlayers();
        passExtraCardToDealer();
    }

    private void passExtraCardToPlayers() {
        int totalPlayerCount = blackJackGame.getTotalPlayerCount();
        for (int playerIndex = 0; playerIndex < totalPlayerCount; playerIndex++) {
            addExtraCard(playerIndex);
        }
    }

    private void addExtraCard(int playerIndex) {
        while (isContinuePassPlayerCard(playerIndex)) {
            blackJackGame.passPlayerCard(playerIndex);
            PlayerNameCardsResponse playerNameCards = blackJackGame.findPlayerNameAndCards(playerIndex);
            outputView.showPlayerNameCards(playerNameCards);
        }
    }

    private boolean isContinuePassPlayerCard(int playerIndex) {
        if (blackJackGame.canPassPlayerCard(playerIndex)) {
            String intention = inputView.readIntention(blackJackGame.findPlayerName(playerIndex));
            return hasIntention(intention);
        }
        return false;
    }

    private boolean hasIntention(String intention) {
        return intention.equals(CARD_RECEIVE_INTENTION);
    }

    private void passExtraCardToDealer() {
        if (blackJackGame.canPassDealerCard()) {
            blackJackGame.passDealerCard();
            outputView.showDealerDrawPossible();
            return;
        }
        outputView.showDealerDrawImpossible();
    }

    private void finishGame() {
        outputView.showTotalScoreDealer(blackJackGame.findDealerCardsScore());
        outputView.showAllPlayerNameCardsScore(blackJackGame.findAllPlayerNameCardsScore());
        outputView.showTotalResult(blackJackGame.findDealerPlayerResult());
    }

}
