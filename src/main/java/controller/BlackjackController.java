package controller;

import static java.util.stream.Collectors.toUnmodifiableList;

import domain.BlackjackGame;
import domain.card.RandomShuffleStrategy;
import domain.participant.BettingMoney;
import domain.participant.Decision;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
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
        outputView.printParticipantsScore(toParticipantDtosWithScore(blackjackGame.participants()));
        outputView.printRevenues(blackjackGame.calculateParticipantsRevenues());
    }

    private BlackjackGame initializeGame() {
        List<Name> playerNames = createNames();
        Players players = createPlayers(playerNames);
        BlackjackGame blackjackGame = new BlackjackGame(players);
        blackjackGame.handOutInitialCards(new RandomShuffleStrategy());

        outputView.printCardInfos(toParticipantDtosOfInitialState(blackjackGame.participants()));
        return blackjackGame;
    }

    private List<Name> createNames() {
        try {
            return inputView.readNames().stream()
                    .map(Name::new)
                    .collect(toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return createNames();
        }
    }

    private Players createPlayers(List<Name> playerNames) {
        try {
            return playerNames.stream()
                    .map(playerName -> new Player(playerName, new Hand(), createBettingMoney(playerName.value())))
                    .collect(Collectors.collectingAndThen(toUnmodifiableList(), Players::new));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return createPlayers(playerNames);
        }
    }

    private BettingMoney createBettingMoney(String playerName) {
        try {
            return new BettingMoney(inputView.readBettingMoney(playerName));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return createBettingMoney(playerName);
        }
    }

    private List<ParticipantDto> toParticipantDtosOfInitialState(List<Participant> participants) {
        return participants.stream()
                .map(ParticipantDto::ofInitial)
                .collect(toUnmodifiableList());
    }

    private void handOutCardsToPlayers(BlackjackGame blackjackGame) {
        while (blackjackGame.hasDrawablePlayer()) {
            Player currentDrawablePlayer = blackjackGame.findCurrentDrawablePlayer();
            Decision decision = createDecision(currentDrawablePlayer);
            blackjackGame.hitOrStand(decision);
            outputView.printCardsInfo(ParticipantDto.of(currentDrawablePlayer));
        }
    }

    private Decision createDecision(Player currentDrawablePlayer) {
        try {
            return Decision.from(inputView.readDecision(currentDrawablePlayer.name()));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return createDecision(currentDrawablePlayer);
        }
    }

    private void handOutCardsToDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.isDealerDrawable()) {
            blackjackGame.handOutCardToDealer();
            outputView.printDealerHandOutInfo();
        }
    }

    private List<ParticipantDtoWithScore> toParticipantDtosWithScore(List<Participant> participants) {
        return participants.stream()
                .map(ParticipantDtoWithScore::new)
                .collect(toUnmodifiableList());
    }

}
