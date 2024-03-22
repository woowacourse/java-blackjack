package blackjack.controller;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.card.deck.ShuffledDeckCardGenerator;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GameResults;
import blackjack.domain.result.GamerProfits;
import blackjack.dto.gamer.PlayerInfo;
import blackjack.dto.gamer.PlayerInfos;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(ShuffledDeckCardGenerator.getInstance());
        Players players = createPlayers();

        dealInitCards(deck, dealer, players);
        receiveAdditionalCard(deck, dealer, players);

        printGameResult(dealer, players);
    }

    private Players createPlayers() {
        List<Name> playerNames = inputView.readPlayerNames().stream()
                .map(Name::new)
                .toList();
        List<BettingMoney> bettingMonies = betPlayers(playerNames);
        return new Players(playerNames, bettingMonies);
    }

    private List<BettingMoney> betPlayers(List<Name> playerNames) {
        List<BettingMoney> bettingMonies = new ArrayList<>();
        for (Name playerName : playerNames) {
            int money = inputView.readPlayerBettingMoney(playerName.value());
            bettingMonies.add(new BettingMoney(money));
        }
        return bettingMonies;
    }

    private void dealInitCards(final Deck deck, final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            player.receiveInitCards(deck.drawInitCards());
        }
        dealer.receiveInitCards(deck.drawInitCards());

        outputView.printInitCardStatus(dealer.getFirstCard(), PlayerInfos.from(players));
    }

    private void receiveAdditionalCard(final Deck deck, final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            receivePlayerAdditionalCard(deck, player);
        }
        receiveDealerAdditionalCard(deck, dealer);
    }

    private void receivePlayerAdditionalCard(final Deck deck, final Player player) {
        while (!player.isBust() && inputView.readHitOrStand(player).isYes()) {
            player.receiveCard(deck.drawCard());
            outputView.printCardsStatus(PlayerInfo.from(player));
        }
    }

    private void receiveDealerAdditionalCard(final Deck deck, final Dealer dealer) {
        while (dealer.hasHitScore()) {
            dealer.receiveCard(deck.drawCard());
            outputView.printDealerHitMessage();
        }
    }

    private void printGameResult(final Dealer dealer, final Players players) {
        GameResults gameResults = new GameResults(players, dealer);
        GamerProfits gamerProfits = gameResults.calculateGamerProfits();

        outputView.printTotalCardsStatus(dealer, players);
        outputView.printTotalProfit(gamerProfits.getGamerProfitStates());
    }
}
