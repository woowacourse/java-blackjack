package controller;

import domain.HitCommand;
import domain.Deck;
import domain.GameManager;
import domain.dto.GameResultResponse;
import domain.dto.ParticipantCardsResponse;
import domain.dto.ParticipantResultResponse;
import domain.Referee;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.PlayerParser;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = new Participants(getPlayers(), new Dealer());
        GameManager gameManager = getManager();

        initializeGame(gameManager, participants);
        playGame(gameManager, participants);
        determineGame(participants);
    }

    private void initializeGame(GameManager gameManager, Participants participants) {
        gameManager.dealInitialCards(participants);
        outputView.printGameInitResult(createInitialResponses(participants));
    }

    private void playGame(GameManager gameManager, Participants participants) {
        playPlayersTurn(gameManager, participants);
        playDealerTurn(gameManager, participants);
    }

    private void playPlayersTurn(GameManager gameManager, Participants participants) {
        for (Player player : participants.getPlayers()) {
            playSinglePlayerTurn(gameManager, player);
        }
        outputView.printNewLine();
    }

    private void playSinglePlayerTurn(GameManager gameManager, Player player) {
        while (player.canReceive() && wantsToHit(player)) {
            gameManager.dealCard(player);
            outputView.printParticipantCard(new ParticipantCardsResponse(player.getName(), player.getCards()));
        }
    }

    private boolean wantsToHit(Player player) {
        HitCommand command = HitCommand.from(inputView.askPlayHit(player.getName()));
        return command.isYes();
    }

    private void playDealerTurn(GameManager gameManager, Participants participants) {
        Dealer dealer = participants.getDealer();
        Players players = participants.getPlayers();

        while (dealer.canReceive()) {
            gameManager.dealCard(dealer);
            outputView.printCompleteDealerTurn();
        }

        List<ParticipantResultResponse> dealerTurnResponse = createResultResponses(dealer, players);
        outputView.printDealerTurn(dealerTurnResponse);
    }

    private void determineGame(Participants participants) {
        Referee referee = new Referee();
        GameResultResponse response = referee.evaluateMatch(participants);
        outputView.printGameResult(response);
    }

    private List<ParticipantCardsResponse> createInitialResponses(Participants participants) {
        List<ParticipantCardsResponse> responses = new ArrayList<>();
        Dealer dealer = participants.getDealer();

        responses.add(new ParticipantCardsResponse(dealer.getName(), List.of(dealer.getInitialCard())));
        for (Player player : participants.getPlayers()) {
            responses.add(new ParticipantCardsResponse(player.getName(), player.getCards()));
        }
        return responses;
    }

    private List<ParticipantResultResponse> createResultResponses(Dealer dealer, Players players) {
        List<ParticipantResultResponse> responses = new ArrayList<>();
        responses.add(new ParticipantResultResponse(dealer.getName(), dealer.getCards(), dealer.getScore()));
        for (Player player : players) {
            responses.add(new ParticipantResultResponse(player.getName(), player.getCards(), player.getScore()));
        }
        return responses;
    }

    private GameManager getManager() {
        Deck deck = Deck.create();
        deck.shuffle();
        return new GameManager(deck);
    }

    private Players getPlayers() {
        String rawPlayerName = inputView.readPlayerName();
        outputView.printNewLine();
        return PlayerParser.parseToPlayers(rawPlayerName);
    }
}
