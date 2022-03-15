package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.card.InitCards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.InputView;
import view.OutputView;

public class Controller {

    public void run() {
        List<Name> names = InputView.inputNames();
        Deck deck = Deck.createDeckForBlackJack();
        Dealer dealer = new Dealer(new InitCards(deck).getInitCards());
        Players players = new Players(names, InitCards.generateInitCardsForPlayers(deck, names.size()));

        OutputView.printParticipantInitHands(dealer, players);

        if (dealer.isBlackJack()) {
            OutputView.printDealerBlackJackMessage();
            OutputView.printResult(players.getNames(), players.getResultAtDealerBlackJack(dealer));
            return;
        }
        OutputView.printPlayerIsBlackJackMessage(players);

        drawForPlayers(deck, players);
        drawForDealer(deck, dealer, players);
        OutputView.printStatuses(dealer, players);
        OutputView.printResult(players.getNames(), players.getResultAtFinal(dealer));
    }

    private void drawForPlayers(Deck deck, Players players) {
        for (Name name : players.getNames()) {
            drawForPlayer(name, deck, players);
        }
    }

    private void drawForPlayer(Name name, Deck deck, Players players) {
        while (players.isNeedToDrawByName(name) && InputView.inputAskDraw(name.getName())) {
            players.addCardByName(name, deck.draw());
            OutputView.printPlayerHand(name, players);
            OutputView.printIfMaxScoreOrBust(name, players);
        }
    }

    private void drawForDealer(Deck deck, Dealer dealer, Players players) {
        while (players.isNotAllBust() && dealer.isNeedToDraw()) {
            OutputView.printDealerDrawMessage();
            dealer.addCard(deck.draw());
        }
    }
}
