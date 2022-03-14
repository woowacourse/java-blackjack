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
        Deck deck = new Deck();
        Dealer dealer = new Dealer(new InitCards(deck).getInitCards());
        Players players = new Players(names, generateInitCardsForPlayers(names.size(), deck));

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

    private List<List<Card>> generateInitCardsForPlayers(int playerCount, Deck deck) {
        return IntStream.range(0, playerCount)
                .mapToObj(i -> new InitCards(deck).getInitCards())
                .collect(Collectors.toList());
    }

    private void drawForPlayers(Deck deck, Players players) {
        for (Name name : players.getNames()) {
            drawForPlayer(name, deck, players);
        }
    }

    private void drawForPlayer(Name name, Deck deck, Players players) {
        boolean isMaxScoreOrBust = false;
        while (!players.isBlackJackByName(name) && !isMaxScoreOrBust && InputView.inputAskDraw(name.getName())) {
            players.addCardByName(name, deck.draw());
            OutputView.printPlayerHand(name, players);
            isMaxScoreOrBust = OutputView.printIfMaxScoreOrBust(players, name);
        }
    }

    private void drawForDealer(Deck deck, Dealer dealer, Players players) {
        if (players.isNotAllBust()) {
            checkAndDrawForDealer(deck, dealer);
        }
    }

    private void checkAndDrawForDealer(Deck deck, Dealer dealer) {
        while (dealer.isNeedToDraw()) {
            OutputView.printDealerDrawMessage();
            dealer.addCard(deck.draw());
        }
    }
}
