package controller;

import card.CardDeck;
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
                .map(name -> new Player(cardDeck.firstCards(), new Name(name),
                        new BetMoney(inputView.inputBettingMoney(name))))
                .toList();
    }

    private void runBlackJackGame(Players players, CardDeck cardDeck, Dealer dealer) {
        outputView.printInitCardStatus(players, dealer.getFirstCard());

        for (Player player : players.getPlayers()) {
            playGame(player, cardDeck);
        }

        dealer.playGame(cardDeck);

        outputView.printExtraCardInfo(dealer.getCards());
        outputView.printDealerHand(dealer);
        outputView.printPlayerHand(players);
    }

    private void playGame(Player player, CardDeck cardDeck) {
        while (isHit(player)) {
            player.hit(cardDeck.pickCard());
            outputView.printPlayerCardStatus(player.getName(), player.getCards());
        }
    }

    private void showResult(Players players, Dealer dealer) {
        Map<Name, Integer> playerResults = players.getPlayerResults(dealer);
        outputView.printBlackJackResult(playerResults);
    }

    private boolean isHit(Player player) {
        return !player.isBust() && inputView.isHit(player.getName());
    }
}
