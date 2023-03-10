package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Bet;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Name;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.dto.ParticipantStatusResponse;
import blackjack.dto.ParticipantTotalStatusResponse;
import blackjack.dto.PlayerNamesResponse;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class BlackJackController {
    private static final int START_DRAW_COUNT = 2;

    public void run(DeckGenerator deckGenerator) {
        BlackJackGame blackJackGame = setupGame(deckGenerator);
        playGame(blackJackGame);
        finishGame(blackJackGame);
    }

    private BlackJackGame setupGame(DeckGenerator deckGenerator) {
        BlackJackGame blackJackGame = repeat(() -> createBlackJackGame(deckGenerator));
        OutputView.printStartDrawCardMessage(PlayerNamesResponse.of(blackJackGame.getPlayerNames()));
        printAllParticipantStatues(blackJackGame.getStartStatusResponse());
        return blackJackGame;
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return repeat(supplier);
        }
    }

    private BlackJackGame createBlackJackGame(DeckGenerator deckGenerator) {
        Deck deck = deckGenerator.generate();
        List<String> names = InputView.readPlayerNames();
        List<Player> players = names.stream()
                .map(name -> new Player(name, deck.drawCards(START_DRAW_COUNT), readPlayerBet(name)))
                .collect(toList());
        Dealer dealer = new Dealer(deck.drawCards(START_DRAW_COUNT));
        Participants participants = new Participants(dealer, players);
        return new BlackJackGame(participants, deck);
    }

    private Bet readPlayerBet(String playerName) {
        int bet = InputView.readPlayerBet(playerName);
        return Bet.of(bet);
    }

    private void printAllParticipantStatues(List<ParticipantStatusResponse> participantStatusResponse) {
        for (ParticipantStatusResponse response : participantStatusResponse) {
            OutputView.printParticipantStatus(response);
        }
    }

    private void playGame(BlackJackGame blackJackGame) {
        List<Name> playerNames = blackJackGame.getPlayerNames();
        for (Name playerName : playerNames) {
            repeat(() -> drawMoreCard(playerName, blackJackGame));
            OutputView.printParticipantStatus(getPlayerStatusByName(blackJackGame, playerName));
        }
        OutputView.printDealerDrawCardMessage(blackJackGame.drawMoreCardForDealer());
    }

    private void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            repeat(runnable);
        }
    }

    private void drawMoreCard(Name playerName, BlackJackGame blackJackGame) {
        while (decideDraw(playerName.getName())) {
            blackJackGame.drawMoreCardForPlayer(playerName);
            OutputView.printParticipantStatus(getPlayerStatusByName(blackJackGame, playerName));
        }
    }

    private boolean decideDraw(String playerName) {
        return InputView.readDrawCardDecision(playerName);
    }

    private ParticipantStatusResponse getPlayerStatusByName(BlackJackGame blackJackGame, Name playerName) {
        return blackJackGame.getPlayerStatusByName(playerName);
    }

    private void finishGame(BlackJackGame blackJackGame) {
        printAllTotalStatues(blackJackGame.getAllParticipantTotalResponse());
        OutputView.printTotalGameResult(blackJackGame.getTotalGameResult());
    }

    private void printAllTotalStatues(List<ParticipantTotalStatusResponse> responses) {
        for (ParticipantTotalStatusResponse response : responses) {
            OutputView.printParticipantTotalStatus(response);
        }
    }
}
