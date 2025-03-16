package controller;

import domain.card.CardDeck;
import domain.card.Hand;
import domain.card.StandardCardsInitializer;
import domain.gamer.Betting;
import domain.gamer.Dealer;
import domain.gamer.Nickname;
import domain.gamer.Player;
import domain.rule.BlackjackMatchResult;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final String YES_SIGN = "y";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck deck = new CardDeck(new StandardCardsInitializer());

        Dealer dealer = new Dealer(new Hand(deck));
        List<Player> players = setPlayers(deck);

        outputView.printInitialCards(dealer, players);

        processPlayersTurn(players, deck);

        processDealerTurn(dealer, deck);

        outputView.printCardResult(dealer, players);
        showProfitResult(players, dealer);
    }

    private List<Player> setPlayers(CardDeck deck) {
        List<Player> players = new ArrayList<>();

        List<Nickname> nicknames = inputView.readNames().stream()
                .map(Nickname::new)
                .toList();

        for (Nickname nickname : nicknames) {
            Betting betting = new Betting(inputView.readBettingAmount(nickname));
            players.add(new Player(new Hand(deck), nickname, betting));
        }

        return players;
    }

    private void processPlayersTurn(List<Player> players, CardDeck deck) {
        for (Player player : players) {
            processPlayerAction(player, deck);
        }
    }

    private void processPlayerAction(Player player, CardDeck deck) {
        while (!player.isFinished()) {
            selectChoice(player, deck);
        }
    }

    private void selectChoice(Player player, CardDeck deck) {
        if (wantHit(player)) {
            player.hit(deck);
            outputView.printCards(player);
            return;
        }
        player.stay();
    }

    private boolean wantHit(Player player) {
        return YES_SIGN.equals(inputView.readYesOrNo(player));
    }

    private void processDealerTurn(Dealer dealer, CardDeck deck) {
        if (!dealer.isFinished()) {
            dealer.hit(deck);
            outputView.printDealerHitSuccess();
        }
    }

    private void showProfitResult(List<Player> players, Dealer dealer) {
        int dealerProfit = 0;
        List<Integer> playerProfit = new ArrayList<>();

        for (Player player : players) {
            BlackjackMatchResult playerResult = dealer.determineMatchResultFor(player);
            dealerProfit -= (int) player.getProfit(playerResult);
            playerProfit.add((int) player.getProfit(playerResult));
        }

        outputView.printProfitResult(dealer, dealerProfit, players, playerProfit);
    }

}
