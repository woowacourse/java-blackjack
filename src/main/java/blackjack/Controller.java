package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import blackjack.domain.Card;
import blackjack.domain.CardRank;
import blackjack.domain.CardShape;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
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
        Deck deck = new Deck(createCards());

        initialDeal(participants, deck);
        playersTurn(participants.getPlayers(), deck);
        dealerTurn(participants.getDealer(), deck);
        printResult(participants);
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

    private void playersTurn(List<Player> players, Deck deck) {
        for (Player player : players) {
            playerTurn(player, deck);
        }
    }

    private void playerTurn(Player player, Deck deck) {
        while (player.isPlayable()) {
            GameCommand command = createGameCommand(player);
            if (command.isNo()) {
                outputView.printCards(player);
                break;
            }

            player.hit(deck.draw());
            outputView.printCards(player);
        }
    }

    private GameCommand createGameCommand(Player player) {
        String command = inputView.readDecision(player.getName());

        return GameCommand.from(command);
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isPlayable()) {
            dealer.hit(deck.draw());
            outputView.printDealerHitMessage();
        }
    }

    private void printResult(Participants participants) {
        outputView.printAllCardsWithScore(participants.getParticipants());
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (CardRank rank : CardRank.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(rank, shape));
            }
        }
        Collections.shuffle(cards);

        return cards;
    }
}
