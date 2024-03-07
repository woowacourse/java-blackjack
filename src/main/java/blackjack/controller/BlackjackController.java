package blackjack.controller;

import blackjack.domain.card.CardPicker;
import blackjack.domain.game.Referee;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerCommand;

import java.util.Optional;
import java.util.function.Supplier;

public class BlackjackController {

    public void run() {
        Players players = requestPlayers();
        Dealer dealer = new Dealer();
        CardPicker cardPicker = new CardPicker();

        processDeal(players, dealer, cardPicker);
        processHitOrStand(players, dealer, cardPicker);

        Referee referee = new Referee();
        referee.calculatePlayersResults(players, dealer);

        printAllGameResult(dealer, referee);
    }

    private void printAllGameResult(Dealer dealer, Referee referee) {
        OutputView.printWinAnnounce();
        OutputView.printDealerWinStatus(dealer.getName(), referee.getDealerResult());
        referee.getPlayersResults().forEach(OutputView::printPlayerWinStatus);
    }

    private void processHitOrStand(Players players, Dealer dealer, CardPicker cardPicker) {
        players.forEach(player -> requestHitOrStand(player, cardPicker));
        OutputView.printNewLine();
        dealerHitUntilBound(dealer, cardPicker);

        OutputView.printGamerCards(dealer.getName(), dealer.getCards(), dealer.getScore());
        players.forEach(player ->
                OutputView.printGamerCards(player.getName(), player.getCards(), player.getScore()));
    }

    private void requestHitOrStand(Player player, CardPicker cardPicker) {
        if (player.isBlackjack()) {
            return;
        }
        CommandController commandController = new CommandController();
        commandController.put(PlayerCommand.HIT, () -> hitAndPrint(player, cardPicker));
        commandController.put(PlayerCommand.STAND, () -> checkPlayerStandAfterDeal(player));

        while (commandController.run(InputView.readPlayerCommand(player.getName()))) ;
    }

    private boolean hitAndPrint(Player player, CardPicker cardPicker) {
        player.hit(cardPicker);
        OutputView.printDealCards(player.getName(), player.getCards());
        return !(player.isBurst() || player.isMaxScore());
    }

    private boolean checkPlayerStandAfterDeal(Player player) {
        if (player.getCards().size() == 2) {
            OutputView.printDealCards(player.getName(), player.getCards());
        }
        return false;
    }

    private void dealerHitUntilBound(Dealer dealer, CardPicker cardPicker) {
        while (dealer.isHitUnderBound()) {
            dealer.hit(cardPicker);
            OutputView.printDealerHitAnnounce();
        }
    }


    private void processDeal(Players players, Dealer dealer, CardPicker cardPicker) {
        OutputView.printDealAnnounce(players.getNames());
        players.forEach(player -> player.deal(cardPicker));
        dealer.deal(cardPicker);

        OutputView.printDealCard(dealer.getName(), dealer.getFirstCard());
        players.forEach(player ->
                OutputView.printDealCards(player.getName(), player.getCards()));
        OutputView.printNewLine();
    }

    private Players requestPlayers() {
        return requestUntilValid(() -> Players.from(InputView.readPlayersName()));
    }

    private <T> T requestUntilValid(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryGet(supplier);
        }
        return result.get();
    }

    private <T> Optional<T> tryGet(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
