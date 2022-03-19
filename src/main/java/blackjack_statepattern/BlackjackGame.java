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
                .map(name -> new Player(name, getBetMoney(name)))
                .collect(Collectors.toList()));
    }

    private int getBetMoney(String name) {
        try {
            return InputView.askBetMoney(name);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getBetMoney(name);
        }
    }

    private void playGame() {
        distributeCards();
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

}
