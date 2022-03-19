package blackjack.domain;

import static blackjack.view.PlayCommand.YES;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomCardsGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.PlayerResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;
import java.util.Map;

public class BlackjackRunner {

    private static final int REVERSE_MONEY = -1;

    public void run() {
        Deck deck = new Deck(new RandomCardsGenerator());
        Dealer dealer = new Dealer();

        Map<String, Integer> names = InputView.getNames();
        List<Player> players = names.keySet()
                .stream()
                .map(name -> new Player(name, names.get(name)))
                .collect(toList());

        ready(deck, dealer, players);

        OutputView.printStart(dealer, players);

        players.forEach(player -> playing(deck, player, YES));
        drawDealer(deck, dealer);

        OutputView.printResult(dealer, players);
        OutputView.printProfit(getDealerProfit(dealer, players), createPlayerResults(dealer, players));
    }

    private void ready(Deck deck, Dealer dealer, List<Player> players) {
        readyDealer(deck, dealer);
        players.forEach(player -> readyPlayer(deck, player));
    }

    private void readyDealer(Deck deck, Dealer dealer) {
        if (!dealer.isReady()) {
            dealer.hit(deck.pick());
            readyDealer(deck, dealer);
        }
    }

    private void readyPlayer(Deck deck, Player player) {
        if (!player.isReady()) {
            player.hit(deck.pick());
            readyPlayer(deck, player);
        }
    }

    private void playing(Deck deck, Player player, PlayCommand playCommand) {
        if (isPlaying(player, playCommand)) {
            playCommand = InputView.getPlayCommand(player);
            pickCard(deck, player, playCommand);
            playing(deck, player, playCommand);
        }
    }

    private boolean isPlaying(Player player, PlayCommand playCommand) {
        return !player.isFinished() && playCommand.isYes();
    }

    private void pickCard(Deck deck, Player player, PlayCommand playCommand) {
        if (playCommand.isYes()) {
            player.hit(deck.pick());
            OutputView.printPlayerCard(player);
        }
        player.stay();
    }

    private void drawDealer(Deck deck, Dealer dealer) {
        if (!dealer.isFinished()) {
            dealer.hit(deck.pick());
            OutputView.printDealerDrawable();
            drawDealer(deck, dealer);
        }
    }

    private int getDealerProfit(Dealer dealer, List<Player> players) {
        return players.stream()
                .mapToInt(player -> player.calculateProfit(dealer) * REVERSE_MONEY)
                .sum();
    }

    private List<PlayerResult> createPlayerResults(Dealer dealer, List<Player> players) {
        return players.stream()
                .map(player -> new PlayerResult(player.getName(), player.calculateProfit(dealer)))
                .collect(toList());
    }
}
