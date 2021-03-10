package blackjack.controller;

import blackjack.domain.blackjack.BlackJackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void play() {
        BlackJackGame blackJackGame = new BlackJackGame(Deck.generate(), writeValueAsParticipants(InputView.inputNames()));
        blackJackGame.handInitCards();

        OutputView.printInitialCardStatus(blackJackGame.getParticipants());

        playPlayersTurn(blackJackGame);
        blackJackGame.playDealerTurn();

        OutputView.printAllParticipantsCards(blackJackGame.getParticipants());
        OutputView.printScoreResults(blackJackGame.getDealerResult(), blackJackGame.getPlayersResults());
    }

    private void playPlayersTurn(final BlackJackGame blackJackGame) {
        while (blackJackGame.isExistWaitingPlayer()) {
            Player currentPlayer = blackJackGame.findCurrentTurnPlayer();
            blackJackGame.askMoreCard(InputView.inputAskMoreCard(currentPlayer));
            OutputView.printParticipantCards(currentPlayer);
        }
    }

    private Participants writeValueAsParticipants(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, new BlackJackScoreRule()))
                .collect(Collectors.toList());

        return new Participants(players, new Dealer(new BlackJackScoreRule()));
    }
}
