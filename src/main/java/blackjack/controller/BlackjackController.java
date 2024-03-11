package blackjack.controller;

import blackjack.domain.Bettings;
import blackjack.domain.Money;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Participants participants = createParticipants();
            Deck deck = Deck.createShuffledDeck();
            Bettings bettings = new Bettings();

            startGame(participants, deck, bettings);
            printResult(participants, bettings);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void startGame(Participants participants, Deck deck, Bettings bettings) {
        initialDeal(participants, deck);
        placeBetsByPlayers(participants, bettings);
        playersTurn(participants.getPlayers(), deck);
        dealerTurn(participants.getDealer(), deck);
    }

    private Participants createParticipants() {
        List<String> playerNames = inputView.readPlayerNames();

        return new Participants(playerNames);
    }

    private void initialDeal(Participants participants, Deck deck) {
        for (Participant participant : participants.getParticipants()) {
            participant.hit(deck.draw());
            participant.hit(deck.draw());
        }

        outputView.printInitialDeal(participants.getDealer(), participants.getPlayers());
    }

    private void placeBetsByPlayers(Participants participants, Bettings bettings) {
        participants.getPlayers().forEach(player -> {
            int bettingMoney = inputView.readBettingMoney(player.getName());
            bettings.placeBet(player, new Money(bettingMoney));
        });
    }

    private void playersTurn(List<Player> players, Deck deck) {
        players.forEach(player -> playerTurn(player, deck));
    }

    private void playerTurn(Player player, Deck deck) {
        if (!player.isPlayable()) {
            return;
        }

        boolean wannaHit = inputView.readCommand(player.getName());
        if (wannaHit) {
            player.hit(deck.draw());
            outputView.printCards(player.getName(), player.getCards());
            playerTurn(player, deck);
            return;
        }

        outputView.printCards(player.getName(), player.getCards());
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isPlayable()) {
            dealer.hit(deck.draw());

            outputView.printDealerHitMessage();
        }
    }

    private void printResult(Participants participants, Bettings bettings) {
        Map<Player, Integer> playerProfit = bettings.calculatePlayerProfits(participants.getDealer());
        int dealerProfit = bettings.calculateDealerProfit(playerProfit);

        outputView.printAllCardsWithScore(participants.getParticipants());
        outputView.printProfits(playerProfit, dealerProfit);
    }
}
