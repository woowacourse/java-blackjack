package blackjack.controller;

import blackjack.dto.FinishedParticipantDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitResultDto;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.*;
import blackjack.model.state.DealerInitialState;
import blackjack.model.state.PlayerInitialState;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
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
        outputView.printSingleCardStatus(ParticipantDto.of(dealer, dealer.getFirstCard()));
        outputView.printAllCardStatus(ParticipantDto.of(participants.getPlayers()));
    }

    private void hitOrStand(CardDeck cardDeck, Participants participants) {
        while (participants.hasNextPlayer()) {
            Player player = participants.getNextPlayer();
            boolean isHit = inputView.readHitOrStand(player.getName());
            participants.hitOrStandByPlayer(cardDeck, player, isHit);
            outputView.printSingleCardStatus(ParticipantDto.from(player));
        }
        outputView.printDealerHitMessage(participants.hitOrStandByDealer(cardDeck));
    }

    private void finish(Participants participants) {
        outputView.printAllFinalCardStatus(FinishedParticipantDto.of(participants.getParticipants()));

        outputView.printProfitResultMessage();
        outputView.printAllProfitResult(ProfitResultDto.of(participants.getProfitResult()));
    }
}
