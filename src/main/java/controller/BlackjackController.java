package controller;

import domain.BlackjackGame;
import domain.Decision;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Players;
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
        outputView.printRevenues(blackjackGame.getRevenues());
    }

    private BlackjackGame initializeGame() {
        List<Name> playerNames = createNames();
        BlackjackGame blackjackGame = createGame(playerNames);
        blackjackGame.handOutInitialCards(new RandomShuffleStrategy());

        outputView.printParticipantsInitialCards(toParticipantDtos(blackjackGame.getParticipants()));
        return blackjackGame;
    }

    private BlackjackGame createGame(List<Name> playerNames) {
        try {
            List<Player> rawPlayers = playerNames.stream()
                    .map(playerName -> new Player(playerName, inputView.readBettingMoney(playerName.value())))
                    .collect(Collectors.toUnmodifiableList());
            Players players = new Players(rawPlayers);
            return new BlackjackGame(players);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createGame(playerNames);
        }
    }

    private List<Name> createNames() {
        try {
            return inputView.readNames().stream()
                    .map(Name::new)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createNames();
        }
    }

    private void handOutCardsToPlayers(BlackjackGame blackjackGame) {
        while (blackjackGame.hasDrawablePlayer()) {
            Player currentDrawablePlayer = blackjackGame.getCurrentDrawablePlayer();
            Decision decision = getDecision(currentDrawablePlayer);
            blackjackGame.hitOrStand(decision);
            outputView.printAllCards(new ParticipantDto(currentDrawablePlayer));
        }
    }

    private Decision getDecision(Player currentDrawablePlayer) {
        try {
            return Decision.from(inputView.readDecision(currentDrawablePlayer.name()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDecision(currentDrawablePlayer);
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

