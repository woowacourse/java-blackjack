package blackjack.controller;

import blackjack.domain.blackjack.BlackJackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Player;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void play() {
        BlackJackGame blackJackGame = new BlackJackGame(Deck.generate(),
                InputView.inputNames(), new BlackJackScoreRule());
        batAllPlayers(blackJackGame);
        blackJackGame.handInitCards();

        OutputView.printInitialCardStatus(blackJackGame.getParticipants());

        playPlayersTurn(blackJackGame);
        blackJackGame.playDealerTurn();

        OutputView.printAllParticipantsCards(blackJackGame.getParticipants());
        OutputView.printAllEarnings(blackJackGame.getDealerResult(), blackJackGame.getPlayersResults());
    }

    private void batAllPlayers(final BlackJackGame blackJackGame) {
        while (blackJackGame.isExistWaitingBattingPlayer()) {
            Player currentPlayer = blackJackGame.findCurrentBattingPlayer();
            currentPlayer.bet(InputView.inputBattingMoney(currentPlayer));
        }
    }

    private void playPlayersTurn(final BlackJackGame blackJackGame) {
        while (blackJackGame.isExistWaitingPlayer()) {
            Player currentPlayer = blackJackGame.findCurrentTurnPlayer();
            blackJackGame.askMoreCard(InputView.inputAskMoreCard(currentPlayer));
            OutputView.printParticipantCards(currentPlayer);
        }
    }
}
