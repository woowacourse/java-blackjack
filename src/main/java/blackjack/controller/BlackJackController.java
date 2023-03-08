package blackjack.controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Name;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.dto.ParticipantStatusResponse;
import blackjack.dto.ParticipantTotalStatusResponse;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.PlayerNamesResponse;
import blackjack.dto.TotalGameResult;
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
        printAllParticipantStatues(getStartStatusResponse(blackJackGame.getParticipants()));
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
                .map(name -> new Player(name, deck.drawCards(START_DRAW_COUNT)))
                .collect(toList());
        Dealer dealer = new Dealer(deck.drawCards(START_DRAW_COUNT));
        Participants participants = new Participants(dealer, players);
        return new BlackJackGame(participants, deck);
    }

    private void printAllParticipantStatues(List<ParticipantStatusResponse> participantStatusResponse) {
        for (ParticipantStatusResponse response : participantStatusResponse) {
            OutputView.printParticipantStatus(response);
        }
    }

    private List<ParticipantStatusResponse> getStartStatusResponse(Participants participants) {
        return participants.getParticipants().stream()
                .map(ParticipantStatusResponse::ofStart)
                .collect(toList());
    }

    private void playGame(BlackJackGame blackJackGame) {
        Participants participants = blackJackGame.getParticipants();
        List<Player> players = participants.getPlayers();
        Deck deck = blackJackGame.getDeck();
        for (Player player : players) {
            repeat(() -> drawMoreCard(player, deck));
        }
        Dealer dealer = participants.getDealer();
        OutputView.printDealerDrawCardMessage(drawMoreCardForDealer(dealer, deck));
    }

    private void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            repeat(runnable);
        }
    }

    private void drawMoreCard(Player player, Deck deck) {
        while (decideDraw(player.getName())) {
            validateOverScore(player);
            player.addCard(deck.drawCard());
            OutputView.printParticipantStatus(ParticipantStatusResponse.of(player));
        }
        OutputView.printParticipantStatus(ParticipantStatusResponse.of(player));
    }

    private boolean decideDraw(Name playerName) {
        return InputView.readDrawCardDecision(playerName.getName());
    }

    private void validateOverScore(Player player) {
        if (!player.canDrawCard()) {
            throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

    private boolean drawMoreCardForDealer(Dealer dealer, Deck deck) {
        if (dealer.canDrawCard()) {
            dealer.addCard(deck.drawCard());
            return true;
        }
        return false;
    }

    private void finishGame(BlackJackGame blackJackGame) {
        Participants participants = blackJackGame.getParticipants();
        printAllTotalStatues(getAllParticipantTotalResponse(participants));
        OutputView.printTotalGameResult(getTotalGameResult(participants));
    }

    private void printAllTotalStatues(List<ParticipantTotalStatusResponse> responses) {
        for (ParticipantTotalStatusResponse response : responses) {
            OutputView.printParticipantTotalStatus(response);
        }
    }

    private List<ParticipantTotalStatusResponse> getAllParticipantTotalResponse(Participants participants) {
        return participants.getParticipants().stream()
                .map(ParticipantTotalStatusResponse::of)
                .collect(toList());
    }

    private TotalGameResult getTotalGameResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        return participants.getPlayers()
                .stream()
                .map(player -> PlayerGameResult.of(player.getName(), player.matchGame(dealer)))
                .collect(collectingAndThen(toList(), TotalGameResult::of));
    }
}
