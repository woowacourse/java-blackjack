package controller;

import domain.BlackjackGame;
import domain.HitOrStand;
import domain.Participant;
import domain.Player;
import domain.RandomShuffleStrategy;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

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
        outputView.printWinLossInfo(blackjackGame.getPlayersOutcome());
    }

    private BlackjackGame startGame() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.setParticipants(inputView.readPlayerNames());
        blackjackGame.dealFirstHands(new RandomShuffleStrategy());

        outputView.printInitialHands(toHandDtos(blackjackGame.getParticipants()));
        return blackjackGame;
    }

    private void dealCardsToPlayers(BlackjackGame blackjackGame) {
        while (blackjackGame.hasAnyPlayerToDeal()) {
            Player playerToDeal = blackjackGame.getPlayerToDeal();
            HitOrStand decision = HitOrStand.from(inputView.readHitOrStand(playerToDeal.name()));
            blackjackGame.hitOrStand(decision);
            outputView.printCards(new HandDto(playerToDeal));
        }
    }

    private void dealCardsToDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.isDealerDrawable()) {
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

