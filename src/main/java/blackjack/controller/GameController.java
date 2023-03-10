package blackjack.controller;

import blackjack.dto.ParticipantDto;
import blackjack.dto.ResultDto;
import blackjack.model.BetAmount;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.*;
import blackjack.model.result.Result;
import blackjack.model.state.DealerInitialState;
import blackjack.model.state.PlayerInitialState;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Participants participants = initializeParticipants();

        start(cardDeck, participants);
        hitOrStand(cardDeck, participants);
        finish(participants);
    }

    private Participants initializeParticipants() {
        List<String> playerNames = inputView.readNames();
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Name name = new Name(playerName);
            BetAmount betAmount = new BetAmount(inputView.readBetAmount(playerName));
            players.add(new Player(name, betAmount, new PlayerInitialState(new Hand())));
        }
        Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
        return new Participants(dealer, players);
    }

    private void start(CardDeck cardDeck, Participants participants) {
        participants.distributeTwoCardsToEach(cardDeck);
        printInitialCardStatus(participants);
    }

    private void printInitialCardStatus(Participants participants) {
        List<String> playerNames = participants.getPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
        outputView.printDistributionMessage(playerNames);

        Dealer dealer = participants.getDealer();
        outputView.printNameAndHand(ParticipantDto.of(dealer, dealer.getFirstCard()));

        List<Player> players = participants.getPlayers();
        players.forEach(player -> outputView.printNameAndHand(ParticipantDto.from(player)));
    }

    private void hitOrStand(CardDeck cardDeck, Participants participants) {
        while (participants.hasNextPlayer()) {
            Player player = participants.getNextPlayer();
            boolean isHit = inputView.readHitOrStand(player.getName());
            participants.hitOrStandByPlayer(cardDeck, player, isHit);
            outputView.printNameAndHand(ParticipantDto.from(player));
        }
        outputView.printDealerHitMessage(participants.hitOrStandByDealer(cardDeck));
    }

    private void finish(Participants participants) {
        printFinalCardStatus(participants);
        printWinningResult(participants.getPlayerResult());
    }

    private void printFinalCardStatus(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            int score = participant.getScore();
            boolean isBlackjack = participant.isBlackjack();
            outputView.printScoreResult(ParticipantDto.from(participant), score, isBlackjack);
        }
    }

    private void printWinningResult(Map<Player, Result> playerResult) {
        outputView.printWinningResultMessage();

        long dealerWin = playerResult.values().stream().filter(value -> value.equals(Result.LOSE)).count();
        long dealerTie = playerResult.values().stream().filter(value -> value.equals(Result.TIE)).count();
        long dealerLose = playerResult.values().stream().filter(value -> value.equals(Result.WIN)).count();
        outputView.printDealerWinningResult(dealerWin, dealerTie, dealerLose);

        for (Map.Entry<Player, Result> entry : playerResult.entrySet()) {
            outputView.printPlayerWinningResult(ResultDto.of(entry.getKey(), entry.getValue()));
        }
    }

}
