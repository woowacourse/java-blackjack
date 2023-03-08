package controller;

import domain.model.Cards;
import domain.model.Dealer;
import domain.model.Player;
import domain.service.BlackJackResultMaker;
import domain.service.CardDistributor;
import domain.vo.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final CardDistributor cardDistributor;
    private final BlackJackResultMaker blackJackResultMaker;

    public BlackJackController(final CardDistributor cardDistributor, final BlackJackResultMaker blackJackResultMaker) {
        this.cardDistributor = cardDistributor;
        this.blackJackResultMaker = blackJackResultMaker;
    }

    public void play() {
        final List<Player> players = getPlayers();
        final Dealer dealer = new Dealer();
        giveInitialCards(dealer, players);
        getPlayerAdditionalCard(players);
        getDealerAdditionalCard(dealer);
        printTotalCardState(dealer, players);
        printResult(dealer, players);
    }

    private List<Player> getPlayers() {
        final List<String> names = InputView.inputNames();
        return names.stream()
            .map(name -> new Player(Cards.makeEmptyCards(), name))
            .collect(Collectors.toList());
    }

    private void giveInitialCards(final Dealer dealer, final List<Player> players) {
        cardDistributor.giveInitCards(dealer);
        cardDistributor.giveInitCards(players);
        OutputView.printInitialCards(dealer, players);
    }

    private void getPlayerAdditionalCard(final List<Player> players) {
        players.forEach(this::getPlayerAdditionalCard);
    }

    private void getPlayerAdditionalCard(final Player player) {
        while (player.canReceiveCard() && getIntentReceiveCard(player)) {
            cardDistributor.giveCard(player);
            OutputView.printCard(player);
        }
    }

    private boolean getIntentReceiveCard(final Player player) {
        return InputView.inputCardIntent(player.getName());
    }

    private void getDealerAdditionalCard(final Dealer dealer) {
        while (dealer.canReceiveCard()) {
            OutputView.printDealerReceiveNotice();
            cardDistributor.giveCard(dealer);
        }
    }

    private void printTotalCardState(final Dealer dealer, final List<Player> players) {
        OutputView.printTotalCardState(dealer, players);
    }

    private void printResult(final Dealer dealer, final List<Player> players) {
        final Result dealerResult = blackJackResultMaker.makeDealerResult(dealer, players);
        OutputView.printDealerResult(dealerResult);
        final Map<Player, Result> playerResult = blackJackResultMaker.makePlayersResult(dealer, players);
        OutputView.printResult(playerResult);
    }
}
