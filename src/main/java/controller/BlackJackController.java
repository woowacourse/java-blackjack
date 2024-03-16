package controller;

import card.CardDeck;
import gameResult.GameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import participant.dealer.Dealer;
import participant.player.Name;
import participant.player.Player;
import participant.player.Players;
import referee.Referee;
import view.InputView;
import view.OutputView;

// Todo: 클래식하게 짜기
public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.firstCardSettings());
        Players players = playersBetting(cardDeck);

        runBlackJackGame(players, cardDeck, dealer);
        showResult(players, dealer);
    }

    private Players playersBetting(CardDeck cardDeck) {
        List<Player> players = startBetting(inputView.inputPlayerNames(), cardDeck);
        return new Players(players);
    }

    private List<Player> startBetting(List<String> names, CardDeck cardDeck) {
        return names.stream()
                .map(name -> Player.joinGame(name, cardDeck.firstCardSettings(), inputView.inputBettingMoney(name)))
                .toList();
    }

    private void runBlackJackGame(Players players, CardDeck cardDeck, Dealer dealer) {
        outputView.printInitCardStatus(players, dealer.getFirstCard(), dealer.getName());

        for (Player player : players.getPlayers()) {
            playGame(player, cardDeck);
        }

        dealer.playGame(cardDeck);

        outputView.printExtraCardInfo(dealer);
        outputView.printDealerHand(dealer);
        outputView.printPlayerHand(players);
    }

    private void playGame(Player player, CardDeck cardDeck) {
        while (isHit(player)) {
            player.hit(cardDeck.pickCard());
            outputView.printPlayerCardStatus(player);
        }
    }

    private void showResult(Players players, Dealer dealer) {
        Map<Name, Integer> playerResult = new LinkedHashMap<>();
        Referee referee = new Referee();

        for (Player player : players.getPlayers()) {
            GameResult gameResult = referee.judge(player, dealer);
            playerResult.put(player.getName(), player.betMoneyResult(gameResult.returnRate()));
        }

        outputView.printBlackJackResult(playerResult, dealer.getName());
    }

    private boolean isHit(Player player) {
        return !player.isBust() && inputView.inputPlayerCommand(
                player.getName());
    }
}
