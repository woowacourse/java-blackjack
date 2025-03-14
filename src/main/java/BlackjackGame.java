import deck.Deck;
import deck.ShuffledDeckCreator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import participant.Dealer;
import participant.GameResult;
import participant.Player;
import participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    private final Deck deck;

    public BlackjackGame(final Deck deck) {
        this.deck = new Deck(new ShuffledDeckCreator());
    }

    public void run() {
        Players players = new Players(InputView.readNicknames());
        Dealer dealer = new Dealer();

        players.prepareAllPlayers(deck);
        dealer.prepareGame(deck.draw(), deck.draw());
        OutputView.printInitialCards(dealer, players);

        for (Player player : players.getPlayers()) {
            while (player.canReceiveCard() && InputView.readDrawOneMore(player.getNickname())) {
                player.hit(deck.draw());
                OutputView.printPlayerCards(player);
            }
        }

        while (dealer.canReceiveCard()) {
            OutputView.printDealerReceivedCard();
            dealer.hit(deck.draw());
        }

        OutputView.printAllCardAndScore(players, dealer);
        Map<Player, GameResult> playerGameResult = calculatePlayerGameResult(players, dealer);
        Map<GameResult, Integer> dealerGameResult = calculateDealerGameResult(playerGameResult);
        OutputView.printResult(dealerGameResult, playerGameResult);
    }

    public Map<Player, GameResult> calculatePlayerGameResult(Players players, Dealer dealer) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> GameResult.judge(player, dealer),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private Map<GameResult, Integer> initializeDealerResults() {
        Map<GameResult, Integer> dealerResults = new EnumMap<>(GameResult.class);

        for (GameResult gameResult : GameResult.values()) {
            dealerResults.put(gameResult, 0);
        }
        return dealerResults;
    }

    public Map<GameResult, Integer> calculateDealerGameResult(Map<Player, GameResult> playerGameResults) {
        Map<GameResult, Integer> dealerResults = initializeDealerResults();

        for (GameResult gameResult : playerGameResults.values()) {
            if (gameResult.equals(GameResult.WIN)) {
                dealerResults.put(GameResult.LOSE, dealerResults.get(GameResult.LOSE) + 1);
                continue;
            }
            if (gameResult.equals(GameResult.LOSE)) {
                dealerResults.put(GameResult.WIN, dealerResults.get(GameResult.WIN) + 1);
                continue;
            }
            dealerResults.put(GameResult.DRAW, dealerResults.get(GameResult.DRAW) + 1);
        }
        return dealerResults;
    }

}
