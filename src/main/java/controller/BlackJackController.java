package controller;

import card.Card;
import card.CardDeck;
import card.Hand;
import java.util.List;
import java.util.Map;
import participant.dealer.Dealer;
import participant.player.BetMoney;
import participant.player.Name;
import participant.player.Player;
import participant.player.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.firstCards());
        Players players = readyPlayers(cardDeck);

        runBlackJackGame(players, cardDeck, dealer);
        showResult(players, dealer);
    }

    private Players readyPlayers(CardDeck cardDeck) {
        List<Player> players = startBetting(inputView.inputPlayerNames(), cardDeck);
        return new Players(players);
    }

    private List<Player> startBetting(List<String> names, CardDeck cardDeck) {
        return names.stream()
                .map(name -> makePlayer(cardDeck, name))
                .toList();
    }

    private Player makePlayer(CardDeck cardDeck, String name) {
        List<Card> firstCards = cardDeck.firstCards();
        BetMoney betMoney = new BetMoney(inputView.inputBettingMoney(name));

        return new Player(firstCards, new Name(name), betMoney);
    }

    private void runBlackJackGame(Players players, CardDeck cardDeck, Dealer dealer) {
        outputView.printInitCardStatus(players, dealer.getFirstCard());

        players.playBlackJackGame(this::isHit, cardDeck, this::showStatus);
        dealer.playGame(cardDeck);

        outputView.printExtraCardInfo(dealer.getCards());
        outputView.printDealerHand(dealer);
        outputView.printPlayerHand(players);
    }

    private boolean isHit(Name name) {
        return inputView.isHit(name);
    }

    private void showStatus(Name name, Hand hand) {
        outputView.printPlayerCardStatus(name, hand);
    }

    private void showResult(Players players, Dealer dealer) {
        Map<Name, Integer> playerResults = players.getPlayerResults(dealer);
        outputView.printBlackJackResult(playerResults);
    }
}
