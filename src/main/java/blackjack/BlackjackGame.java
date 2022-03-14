package blackjack;

import blackjack.domain.HitFlag;
import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    public static final int INIT_CARD_SIZE = 2;
    public static final int BLACK_JACK_SCORE = 21;
    private static final String WIN_DRAW_LOSE_RESULT_DELIMITER = " ";

    public void run() {
        Deck deck = new Deck();
        Players players = Players.fromNames(InputView.inputPlayerName(),
                (player) -> HitFlag.fromCommand(InputView.inputHitOrStand(player.getName())));
        players.initHit(deck, INIT_CARD_SIZE);
        OutputView.printInitCard(getCardStatus(players));
        players.playersHit(deck, OutputView::printPresentStatus);
        OutputView.printCardResults(getCardResults(players));
        OutputView.printResult(judgeResult(players.findDealer(), players.getGuests()));
    }

    private Map<String, Cards> getCardStatus(Players players) {
        return players.getPlayers()
                .stream()
                .collect(Collectors.toMap(Player::getName, Player::getShowCards));
    }

    private Map<String, Cards> getCardResults(Players players) {
        Map<String, Cards> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getCards());
        }
        return result;
    }

    private Map<String, String> judgeResult(Player dealer, List<Player> guests) {
        Map<String, String> resultStrings = new LinkedHashMap<>();
        Map<WinDrawLose, Integer> dealerResult = new EnumMap<>(WinDrawLose.class);
        judgeAndPutResult(dealer, guests, resultStrings, dealerResult);
        resultStrings.put(dealer.getName(), getWinDrawLoseString(dealerResult));
        return resultStrings;
    }

    private void judgeAndPutResult(Player dealer, List<Player> guests, Map<String, String> resultStrings,
                                   Map<WinDrawLose, Integer> dealerResult) {
        for (Player guest : guests) {
            WinDrawLose result = WinDrawLose.judgeDealerWinDrawLose(dealer, guest);
            dealerResult.merge(result, 1, Integer::sum);
            resultStrings.put(guest.getName(), result.reverse().getName());
        }
    }

    private static String getWinDrawLoseString(Map<WinDrawLose, Integer> winDrawLoseResult) {
        return winDrawLoseResult.entrySet().stream()
                .map(set -> set.getValue() + set.getKey().getName())
                .collect(Collectors.joining(WIN_DRAW_LOSE_RESULT_DELIMITER));
    }
}
