package domain.controller;

import domain.CardShuffler;
import domain.card.Card;
import domain.card.Deck;
import domain.game.GameManager;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.view.InputView;
import domain.view.OutputView;

import java.util.List;
import java.util.stream.IntStream;

public class GameController {

    private static final int START_GIVEN_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(final CardShuffler cardShuffler) {
        Participants participants = makeParticipants();
        Deck deck = Deck.create(cardShuffler);
        GameManager gameManager = GameManager.create(deck, participants);
        handCards(participants, gameManager);
        printParticipantCards(participants);
    }

    private Participants makeParticipants() {
        return inputView.getInputWithRetry(() -> {
            List<String> participantNames = inputView.getParticipantNames();
            return Participants.create(participantNames);
        });
    }

    private void handCards(final Participants participants, final GameManager gameManager) {
        int size = participants.size();
        IntStream.range(0, size)
                .forEach(participantOrder -> gameManager.giveCards(participantOrder, START_GIVEN_COUNT));
    }

    private void printParticipantCards(final Participants participants) {
        outputView.printParticipantMessage(participants);
        printDealerCard(participants);
        printPlayerCards(participants);
    }

    private void printDealerCard(final Participants participants) {
        Dealer dealer = (Dealer) participants.getDealer();
        Card dealerFirstCard = dealer.getFirstCard();
        outputView.printDealerCard(dealer.getName(), dealerFirstCard);
    }

    private void printPlayerCards(final Participants participants) {
        List<Participant> players = participants.getPlayer();
        for (Participant player : players) {
            List<Card> playerCards = player.getCard();
            outputView.printPlayerCard(player.getName(), playerCards);
        }
    }
}
