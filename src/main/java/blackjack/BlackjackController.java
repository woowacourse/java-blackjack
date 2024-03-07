package blackjack;

import blackjack.domain.*;
import blackjack.domain.card.CardPicker;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class BlackjackController {

    public void run() {
        Players players = requestPlayers();
        Dealer dealer = new Dealer();
        CardPicker cardPicker = new CardPicker();
        OutputView.printDealAnnounce(players.getNames());
        requestDeal(players, dealer, cardPicker);

        OutputView.printDealCard(dealer.getName(), dealer.getFirstCard());
        players.forEach(player ->
                OutputView.printDealCards(player.getName(), player.getCards()));

        players.forEach(player -> requestHitOrStand(player, cardPicker));

        while (dealer.isHitUnderBound()) {
            dealer.hit(cardPicker);
            OutputView.printDealerHitAnnounce();
        }

        OutputView.printGamerCards(dealer.getName(), dealer.getCards(), dealer.getScore());
        players.forEach(player ->
                OutputView.printGamerCards(player.getName(), player.getCards(), player.getScore()));

        Referee referee = new Referee(dealer);
        Map<String, GameResult> gameResults = new HashMap<>();
        players.forEach(player ->
                gameResults.put(player.getName(), referee.judgeGameResult(player))
        );

        Map<GameResult, Integer> dealerResults = new HashMap<>();
        gameResults.values().forEach(gameResult -> {
                    GameResult reversedResult = gameResult.reverse();
                    dealerResults.put(reversedResult, dealerResults.getOrDefault(reversedResult, 0) + 1);
                }
        );
        OutputView.printWinAnnounce();
        OutputView.printDealerWinStatus(dealer.getName(), dealerResults);
        gameResults.forEach(OutputView::printPlayerWinStatus);
    }

    private void requestDeal(Players players, Dealer dealer, CardPicker cardPicker) {
        players.forEach(player -> player.deal(cardPicker));
        dealer.deal(cardPicker);
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
