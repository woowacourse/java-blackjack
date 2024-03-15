package blackjack;

import java.util.List;
import blackjack.domain.card.CardDeck;
import blackjack.domain.common.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.profit.Profit;
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
        CardDeck cardDeck = CardDeck.createShuffledDeck();

        initialDeal(participants, cardDeck);
        playersTurn(participants.getPlayers(), cardDeck);
        dealerTurn(participants.getDealer(), cardDeck);
        showCard(participants);
        showProfit(participants);
    }

    private Participants createParticipants() {
        List<String> playerNames = inputView.readPlayerNames();
        List<Money> playersMoney = playerNames.stream()
                .map(this::createPlayerMoney)
                .toList();

        return new Participants(playerNames, playersMoney);
    }

    private Money createPlayerMoney(String playerName) {
        double playerMoney = inputView.readPlayerMoney(playerName);

        return new Money(playerMoney);
    }

    private void initialDeal(Participants participants, CardDeck cardDeck) {
        for (Participant participant : participants.getParticipants()) {
            participant.hit(cardDeck.draw());
            participant.hit(cardDeck.draw());
        }

        outputView.printInitialDeal(participants.getDealer(), participants.getPlayers());
    }

    private void playersTurn(List<Player> players, CardDeck cardDeck) {
        players.forEach(player -> playerTurn(player, cardDeck));
    }

    private void playerTurn(Player player, CardDeck cardDeck) {
        if (!player.isPlayable()) {
            return;
        }

        boolean wannaHit = inputView.readCommand(player.getName());
        if (wannaHit) {
            player.hit(cardDeck.draw());
            outputView.printPlayerCards(player);
            playerTurn(player, cardDeck);
            return;
        }

        outputView.printPlayerCards(player);
    }

    private void dealerTurn(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isPlayable()) {
            dealer.hit(cardDeck.draw());
            outputView.printDealerHitMessage();
        }
    }

    private void showCard(Participants participants) {
        outputView.printAllCardsWithScore(participants.getParticipants());
    }

    private void showProfit(Participants participants) {
        Profit profit = new Profit(participants);

        outputView.printProfit(profit.createAllProfit());
    }
}
