package blackjack_statepattern;

import blackjack_statepattern.card.CardDeck;
import blackjack_statepattern.participant.Dealer;
import blackjack_statepattern.participant.Participant;
import blackjack_statepattern.participant.Player;
import blackjack_statepattern.participant.Players;
import blackjack_statepattern.view.InputView;
import blackjack_statepattern.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public final class BlackjackGame {
    private BlackjackBoard blackjackBoard;
    private CardDeck cardDeck;

    public void run() {
        initializeGame();
        playGame();
    }

    private void initializeGame() {
        blackjackBoard = new BlackjackBoard(new Dealer(), initializePlayers());
        cardDeck = CardDeck.createNewCardDeck();
    }

    private Players initializePlayers() {
        return new Players(InputView.askPlayerNames().stream()
                .map(String::trim)
                .map(name -> new Player(name, askBetMoney(name)))
                .collect(Collectors.toList()));
    }

    private int askBetMoney(String name) {
        try {
            return InputView.askBetMoney(name);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askBetMoney(name);
        }
    }

    private void playGame() {
        distributeCards();
        playPlayersTurn();
    }

    private void distributeCards() {
        Dealer dealer = blackjackBoard.getDealer();
        List<Player> players = blackjackBoard.getPlayers();
        distributeCard(dealer);
        for (Player player : players) {
            distributeCard(player);
        }
        OutputView.printDistributedCards(blackjackBoard.getCardsDto());
    }

    private void distributeCard(Participant participant) {
        while (participant.isReady()) {
            participant.receiveCard(cardDeck.draw());
        }
    }

    private void playPlayersTurn() {
        List<Player> players = blackjackBoard.getPlayers();
        Dealer dealer = blackjackBoard.getDealer();
        for (Player player : players) {
            playPlayerTurn(player);
        }
        playDealerTurn(dealer);
    }

    private void playPlayerTurn(Player player) {
        if (player.isFinished()) {
            return;
        }
        if (askPlayerResponse(player).isStay()) {
            player.stay();
            return;
        }
        player.receiveCard(cardDeck.draw());
        OutputView.printPlayerCards(player);
        playPlayerTurn(player);
    }

    private DrawCommand askPlayerResponse(Player player) {
        try {
            return DrawCommand.from(InputView.askDrawCommand(player));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askPlayerResponse(player);
        }
    }

    private void playDealerTurn(Dealer dealer) {
        while (!dealer.isFinished() && dealer.isUnder17()) {
            dealer.receiveCard(cardDeck.draw());
        }
        if (dealer.isUnder17()) {
            dealer.stay();
        }
    }

}
