package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = startGame();
        dealCardsToPlayers(blackjackGame);
        dealCardsToDealer(blackjackGame);

        outputView.printHandsWithScore(toHandScoreDtos(blackjackGame.getParticipants()));
        outputView.printEarningsInfo(blackjackGame.getDealerEarning(), blackjackGame.getPlayersEarnings());
    }

    private BlackjackGame startGame() {
        final BlackjackGame blackjackGame = new BlackjackGame();
        setParticipants(blackjackGame);
        blackjackGame.dealFirstHands(new RandomShuffleStrategy());

        outputView.printInitialHands(toHandDtos(blackjackGame.getParticipants()));
        return blackjackGame;
    }

    private void setParticipants(final BlackjackGame blackjackGame) {
        final List<String> playerNames = inputView.readPlayerNames();
        final Map<String, Integer> bettingAmounts = new LinkedHashMap<>();
        for (final String name : playerNames) {
            bettingAmounts.put(name, inputView.readBettingAmount(name));
        }
        blackjackGame.setParticipants(bettingAmounts);
    }

    private void dealCardsToPlayers(final BlackjackGame blackjackGame) {
        while (blackjackGame.hasAnyPlayerToDeal()) {
            final Player playerToDecide = blackjackGame.getPlayerToDecide();
            final HitOrStand decision = HitOrStand.from(inputView.readHitOrStand(playerToDecide.name()));
            blackjackGame.hitOrStand(decision);
            outputView.printCards(new HandDto(playerToDecide));
        }
    }

    private void dealCardsToDealer(final BlackjackGame blackjackGame) {
        while (blackjackGame.shouldDealerDrawCard()) {
            blackjackGame.dealCardToDealer();
            outputView.printIfDealerReceivedCard();
        }
    }

    private List<HandDto> toHandDtos(final List<Participant> participants) {
        return participants.stream()
                           .map(HandDto::new)
                           .collect(Collectors.toUnmodifiableList());
    }

    private List<HandScoreDto> toHandScoreDtos(final List<Participant> participants) {
        return participants.stream()
                           .map(HandScoreDto::new)
                           .collect(Collectors.toUnmodifiableList());
    }
}

