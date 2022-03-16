package controller;

import domain.card.Cards;
import domain.card.Deck;
import domain.card.InitCards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {

    public void run() {
        Deck deck = new Deck(Cards.getInstance().getCards());
        Dealer dealer = new Dealer(new InitCards(deck).getInitCards());
        Players players = new Players(createPlayers(deck));

        OutputView.printInitHands(dealer, players);

        if (dealer.isBlackJack()) {
            OutputView.printDealerIsBlackJackMessage();
            OutputView.printResult(players.getNames(), players.getResultAtDealerBlackJack(dealer));
            return;
        }
        OutputView.printPlayerIsBlackJackMessage(players);

        drawForPlayers(deck, players);
        drawForDealer(deck, dealer, players);
        OutputView.printStatuses(dealer, players);
        OutputView.printResult(players.getNames(), players.getResultAtFinal(dealer));
    }

    private List<Player> createPlayers(Deck deck) {
        List<Player> players = new ArrayList<>();
        for (Name name : InputView.inputNames()) {
            players.add(new Player(name, new InitCards(deck).getInitCards(), InputView.inputMoney(name)));
        }
        return players;
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
