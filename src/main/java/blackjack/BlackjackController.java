package blackjack;

import blackjack.domain.BettingBox;
import blackjack.domain.BettingMoney;
import blackjack.domain.BlackjackResult;
import blackjack.domain.HitFlag;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.Map;


public class BlackjackController {

    public void run() {
        Deck deck = new Deck();
        Players players = Players.fromNamesAndGuestHitStrategy(
                InputView.inputPlayerName(),
                (player) -> HitFlag.fromCommand(InputView.inputHitOrStand(player.getName()))
        );
        players.initHit(deck, Players.INIT_CARD_SIZE);
        OutputView.printInitCard(getCardStatus(players));
        BettingBox bettingBox = new BettingBox();
        players.bet(bettingBox, (player) -> new BettingMoney(InputView.inputBettingMoney(player.getName())));
        players.playersHit(deck, OutputView::printPresentStatus);
        OutputView.printCardResults(getCardResults(players));
        BlackjackResult result = BlackjackResult.match(players.findDealer(), players.getGuests());
        OutputView.printResult(result.getPrizeResult(players.findDealer(), bettingBox));
    }

    private Map<String, Cards> getCardStatus(Players players) {
        Map<String, Cards> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getShowCards());
        }
        return result;
    }

    private Map<String, Cards> getCardResults(Players players) {
        Map<String, Cards> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getCards());
        }
        return result;
    }
}
