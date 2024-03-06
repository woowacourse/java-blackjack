package blackjack;

import blackjack.domain.CardPicker;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

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
        for (Player player : players.getValues()) {
            OutputView.printDealCards(player.getName(), player.getCards());
        }
    }

    private void requestDeal(Players players, Dealer dealer, CardPicker cardPicker) {
        players.getValues()
                .forEach(player -> player.deal(cardPicker));
        dealer.deal(cardPicker);
    }


    private void requestHitOrStand() {

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
            // TODO: OutputView 생성 후 예외 메세지 출력
            return Optional.empty();
        }
    }
}
