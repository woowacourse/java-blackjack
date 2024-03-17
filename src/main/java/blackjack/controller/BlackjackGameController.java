package blackjack.controller;

import blackjack.domain.betting.Bettings;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffledDeckGenerator;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GamerResults;
import blackjack.dto.gamer.PlayerInfo;
import blackjack.dto.gamer.PlayerInfos;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.command.Command;

public class BlackjackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Players players = Players.fromNames(inputView.readPlayerNames());
        Dealer dealer = new Dealer();
        Deck deck = createDeck();

        Bettings bettings = betPlayers(players, dealer);

        dealInitCards(deck, dealer, players);
        receiveAdditionalCard(deck, dealer, players);

        GamerResults gamerResults = judgeGameResult(players, dealer);

        printResult(dealer, players);
    }

    private Bettings betPlayers(Players players, Dealer dealer) {
        Bettings bettings = new Bettings();
        bettings.add(dealer, 0);

        for (Player player : players.getPlayers()) {
            int money = inputView.readPlayerBettingMoney(player.getName());
            bettings.add(player, money);
        }

        return bettings;
    }

    private Deck createDeck() {
        ShuffledDeckGenerator deckGenerator = ShuffledDeckGenerator.getInstance();
        return deckGenerator.generate();
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
        while (!player.isBust() && isPlayerInputHit(player)) {
            player.receiveCard(deck.drawCard());
            outputView.printCardsStatus(PlayerInfo.from(player));
        }
    }

    private boolean isPlayerInputHit(final Player player) {
        return inputView.readHitOrStand(player).equals(Command.YES);
    }

    private void receiveDealerAdditionalCard(final Deck deck, final Dealer dealer) {
        while (dealer.hasHitScore()) {
            dealer.receiveCard(deck.drawCard());
            outputView.printDealerHitMessage();
        }
    }

    private GamerResults judgeGameResult(Players players, Dealer dealer) {
        GamerResults gamerResults = new GamerResults();
        for (final Player player : players.getPlayers()) {
            gamerResults.add(player, GameResult.getGameResult(player, dealer));
        }

        return gamerResults;
    }

    public void printResult(final Dealer dealer, final Players players) {
        printTotalCardStatus(dealer, players);
    }

    private void printTotalCardStatus(final Dealer dealer, final Players players) {
        outputView.printTotalCardsStatus(dealer, players);
    }
}
