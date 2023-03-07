package controller;

import domain.BlackjackGame;
import domain.Decision;
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
        BlackjackGame blackjackGame = initializeGame();
        handOutCardsToPlayers(blackjackGame);
        handOutCardsToDealer(blackjackGame);
        outputView.printParticipantsScore(toParticipantDtosWithScore(blackjackGame.getParticipants()));
        outputView.printGameOutcomes(blackjackGame.getPlayersOutcome());
    }

    private BlackjackGame initializeGame() {
        List<String> playerNames = inputView.readNames();
        BlackjackGame blackjackGame = BlackjackGame.createWithPlayerNames(playerNames);
        blackjackGame.handOutInitialCards(new RandomShuffleStrategy());

        outputView.printParticipantsInitialCards(toParticipantDtos(blackjackGame.getParticipants()));
        return blackjackGame;
    }

    private void handOutCardsToPlayers(BlackjackGame blackjackGame) {
        while (blackjackGame.hasDrawablePlayer()) {
            Player currentDrawablePlayer = blackjackGame.getCurrentDrawablePlayer();
            Decision decision = Decision.from(inputView.readDecision(currentDrawablePlayer.name()));
            blackjackGame.hitOrStand(decision);
            outputView.printAllCards(new ParticipantDto(currentDrawablePlayer));
        }
    }

    private void handOutCardsToDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.isDealerDrawable()) {
            blackjackGame.handOutCardToDealer();
            outputView.printDealerHandOutInfo();
        }
    }

    private List<ParticipantDto> toParticipantDtos(List<Participant> participants) {
        return participants.stream()
                .map(ParticipantDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<ParticipantDtoWithScore> toParticipantDtosWithScore(List<Participant> participants) {
        return participants.stream()
                .map(ParticipantDtoWithScore::new)
                .collect(Collectors.toUnmodifiableList());
    }
}

