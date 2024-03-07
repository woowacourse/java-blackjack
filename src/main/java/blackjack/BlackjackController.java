package blackjack;

import blackjack.domain.card.CardPicker;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.Referee;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerCommand;

import java.util.Map;
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

        printAllGameResult(dealer, referee.getDealerResult(), referee.getPlayersResults());
    }

    // TODO: 파라미터 일급컬렉션으로 변경
    private void printAllGameResult(Dealer dealer, Map<GameResult, Integer> dealerResults, Map<String, GameResult> playersResults) {
        OutputView.printWinAnnounce();
        OutputView.printDealerWinStatus(dealer.getName(), dealerResults);
        playersResults.forEach(OutputView::printPlayerWinStatus);
    }

    private void processHitOrStand(Players players, Dealer dealer, CardPicker cardPicker) {
        players.forEach(player -> requestHitOrStand(player, cardPicker));

        dealerHitUntilBound(dealer, cardPicker);

        OutputView.printGamerCards(dealer.getName(), dealer.getCards(), dealer.getScore());
        players.forEach(player ->
                OutputView.printGamerCards(player.getName(), player.getCards(), player.getScore()));
    }

    private void requestHitOrStand(Player player, CardPicker cardPicker) {
        if (player.isBlackjack()) {
            return;
        }
        while (true) {
            PlayerCommand playerCommand = requestUntilValid(() ->
                    PlayerCommand.from(InputView.readMoreCard(player.getName())));
            if (playerCommand.equals(PlayerCommand.HIT)) {
                player.hit(cardPicker);
                OutputView.printDealCards(player.getName(), player.getCards());
                if (player.isBurst() || player.isMaxScore()) {
                    break;
                }
                continue;
            }
            if (player.getCards().size() == 2) {
                OutputView.printDealCards(player.getName(), player.getCards());
            }
            break;
        }
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
