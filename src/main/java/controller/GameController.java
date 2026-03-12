package controller;

import domain.Bet;
import domain.BettingBoard;
import domain.BettingParser;
import domain.Deck;
import domain.GameManager;
import domain.HitCommand;
import domain.Profits;
import domain.Referee;
import domain.dto.ParticipantCardsResponse;
import domain.dto.ParticipantResultResponse;
import domain.dto.ProfitsResultResponse;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.PlayerParser;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.Collections;
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
        BettingBoard bettingBoard = new BettingBoard();

        for (Player player : participants.getPlayers()) {
            Bet bet = BettingParser.parse(inputView.readBettingAmount(player.getName()));
            bettingBoard.addBetting(player, bet);
            outputView.printNewLine();
        }

        initializeGame(gameManager, participants);
        playGame(gameManager, participants);
        determineGame(participants, bettingBoard);
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

        outputView.printDealerTurn(createResultResponses(dealer, players));
    }

    private void determineGame(Participants participants, BettingBoard bettingBoard) {
        Referee referee = new Referee();
        Profits profits = referee.calculateProfits(participants, bettingBoard);
        outputView.printProfitsResult(ProfitsResultResponse.from(profits));
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
        deck.shuffle(Collections::shuffle);
        return new GameManager(deck);
    }

    private Players getPlayers() {
        String rawPlayerName = inputView.readPlayerName();
        outputView.printNewLine();
        return PlayerParser.parseToPlayers(rawPlayerName);
    }
}
