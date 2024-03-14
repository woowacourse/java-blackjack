package blackjack;

import java.util.ArrayList;
import java.util.List;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Money;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = createParticipants();
        Deck deck = Deck.createShuffledDeck();

        initialDeal(participants, deck);
        playersTurn(participants.getPlayers(), deck);
        dealerTurn(participants.getDealer(), deck);
        printResult(participants);
    }

    private Participants createParticipants() {
        List<String> playerNames = inputView.readPlayerNames();
        List<Money> playersMoney = new ArrayList<>();

        for (String playerName : playerNames) {
            playersMoney.add(new Money(inputView.readPlayerMoney(playerName)));
        }

        return new Participants(playerNames, playersMoney);
    }


    private void initialDeal(Participants participants, Deck deck) {
        for (Participant participant : participants.getParticipants()) {
            participant.hit(deck.draw());
            participant.hit(deck.draw());
        }

        outputView.printInitialDeal(participants.getDealer(), participants.getPlayers());
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
            outputView.printCards(player);
            playerTurn(player, deck);
            return;
        }

        outputView.printCards(player);
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isPlayable()) {
            dealer.hit(deck.draw());
            outputView.printDealerHitMessage();
        }
    }

    private void printResult(Participants participants) {
        outputView.printAllCardsWithScore(participants.getParticipants());

        Dealer dealer = participants.getDealer();
        //

    }
}
