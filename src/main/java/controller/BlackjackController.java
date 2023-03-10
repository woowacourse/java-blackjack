package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = startGame();
        dealCardsToPlayers(blackjackGame);
        dealCardsToDealer(blackjackGame);

        outputView.printHandsWithScore(toHandScoreDtos(blackjackGame.getParticipants()));
        outputView.printEarningsInfo(blackjackGame.getPlayersEarnings());
    }

    private BlackjackGame startGame() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.setParticipants(inputView.readPlayerNames());
        setBettingAmounts(blackjackGame);
        blackjackGame.dealFirstHands(new RandomShuffleStrategy());

        outputView.printInitialHands(toHandDtos(blackjackGame.getParticipants()));
        return blackjackGame;
    }

    private void setBettingAmounts(final BlackjackGame blackjackGame) {
        Map<String, Integer> bettingAmounts = new HashMap<>();
        List<String> playerNames = blackjackGame.getPlayerNames();
        for (String name : playerNames) {
            bettingAmounts.put(name, inputView.readBettingAmount(name));
        }
        blackjackGame.makeBet(bettingAmounts);
    }

    private void dealCardsToPlayers(BlackjackGame blackjackGame) {
        while (blackjackGame.hasAnyPlayerToDeal()) {
            Player playerToDecide = blackjackGame.getPlayerToDecide();
            HitOrStand decision = HitOrStand.from(inputView.readHitOrStand(playerToDecide.name()));
            blackjackGame.hitOrStand(decision);
            outputView.printCards(new HandDto(playerToDecide));
        }
    }

    private void dealCardsToDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.shouldDealerDrawCard()) {
            blackjackGame.dealCardToDealer();
            outputView.printIfDealerReceivedCard();
        }
    }

    private List<HandDto> toHandDtos(List<Participant> participants) {
        return participants.stream()
                           .map(HandDto::new)
                           .collect(Collectors.toUnmodifiableList());
    }

    private List<HandScoreDto> toHandScoreDtos(List<Participant> participants) {
        return participants.stream()
                           .map(HandScoreDto::new)
                           .collect(Collectors.toUnmodifiableList());
    }
}

