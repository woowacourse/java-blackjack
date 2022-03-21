package blackjack;

import blackjack.domain.BettingBox;
import blackjack.domain.BettingMoney;
import blackjack.domain.HitFlag;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.Map;


public class BlackjackGame {

    public void run() {
        Deck deck = new Deck();
        Players players = createPlayers(deck);
        BettingBox bettingBox = players.bet(this::inputBettingMoney);
        OutputView.printInitCard(getCardStatus(players));
        players.playersHit(deck, OutputView::printPresentStatus);
        outputGameResult(players, bettingBox);
    }

    private Players createPlayers(Deck deck) {
        try {
            return Players.createPlayers(InputView.inputPlayerNames(), deck, this::inputHitCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createPlayers(deck);
        }
    }

    private HitFlag inputHitCommand(Player player) {
        try {
            return HitFlag.fromCommand(InputView.inputHitOrStand(player.getName()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputHitCommand(player);
        }
    }

    private Map<String, Cards> getCardStatus(Players players) {
        Map<String, Cards> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getShowCards());
        }
        return result;
    }

    private BettingMoney inputBettingMoney(Player player) {
        try {
            return new BettingMoney(InputView.inputBettingMoney(player.getName()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputBettingMoney(player);
        }
    }

    private void outputGameResult(Players players, BettingBox bettingBox) {
        OutputView.printCardResults(getCardResults(players));
        players.judge();
        Map<Player, Integer> prizeResult = players.getPrizeResult(bettingBox);
        OutputView.printResult(prizeResult);
    }

    private Map<String, Cards> getCardResults(Players players) {
        Map<String, Cards> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getCards());
        }
        return result;
    }
}
