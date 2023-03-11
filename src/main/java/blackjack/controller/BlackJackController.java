package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Bet;
import blackjack.domain.BlackJackGame;
import blackjack.domain.ParticipantProfits;
import blackjack.domain.participant.Dealer;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantProfitResponse;
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
        printAllParticipantStatues(blackJackGame.getParticipants());
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
        return repeat(() -> Bet.of(InputView.readPlayerBet(playerName)));
    }

    private void printAllParticipantStatues(List<Participant> participants) {
        for (Participant participant : participants) {
            OutputView.printParticipantStatus(ParticipantStatusResponse.ofStart(participant));
        }
    }

    private void playGame(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            repeat(() -> drawMoreCard(player, blackJackGame));
            OutputView.printParticipantStatus(ParticipantStatusResponse.of(player));
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

    private void drawMoreCard(Player player, BlackJackGame blackJackGame) {
        while (decideDraw(player.getName())) {
            blackJackGame.drawMoreCardForPlayer(player);
            OutputView.printParticipantStatus(ParticipantStatusResponse.of(player));
        }
    }

    private boolean decideDraw(Name playerName) {
        return InputView.readDrawCardDecision(playerName.getName());
    }

    private void finishGame(BlackJackGame blackJackGame) {
        List<Participant> participants = blackJackGame.getParticipants();
        printAllTotalStatues(participants);
        ParticipantProfits participantProfits = blackJackGame.getParticipantProfits();
        OutputView.printTotalGameResult(getParticipantProfitResponses(participantProfits));
    }

    private void printAllTotalStatues(List<Participant> participants) {
        for (Participant participant : participants) {
            OutputView.printParticipantTotalStatus(ParticipantTotalStatusResponse.of(participant));
        }
    }

    private List<ParticipantProfitResponse> getParticipantProfitResponses(ParticipantProfits participantProfits) {
        return participantProfits.getParticipantProfits().stream()
                .map(participantProfit -> new ParticipantProfitResponse(
                        participantProfit.getParticipantName(), participantProfit.getProfit()))
                .collect(toList());
    }
}
