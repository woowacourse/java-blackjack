package blackjack.domain;

import static blackjack.view.PlayCommand.YES;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomCardsGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.PlayerResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;

public class BlackjackRunner {

    private static final int REVERSE_MONEY = -1;

    public void run() {
        Deck deck = new Deck(new RandomCardsGenerator());
        Dealer dealer = new Dealer(deck.getInitCards());
        Players players = new Players(deck, InputView.getNames());

        OutputView.printStart(dealer, players.getValue());

        players.forEach(player -> playing(deck, player, YES));
        drawDealer(deck, dealer);

        List<Player> playersValue = players.getValue();
        OutputView.printResult(dealer, playersValue);
        OutputView.printProfit(getDealerProfit(dealer, playersValue), createPlayerResults(dealer, playersValue));
    }

    private void playing(Deck deck, Player player, PlayCommand playCommand) {
        if (isPlaying(player, playCommand)) {
            playCommand = InputView.getPlayCommand(player);
            drawCard(deck, player, playCommand);
            playing(deck, player, playCommand);
        }
    }

    private boolean isPlaying(Player player, PlayCommand playCommand) {
        return player.canHit() && playCommand.isYes();
    }

    private void drawCard(Deck deck, Player player, PlayCommand playCommand) {
        if (playCommand.isYes()) {
            player.append(deck.draw());
            OutputView.printPlayerCard(player);
        }
    }

    private void drawDealer(Deck deck, Dealer dealer) {
        if (dealer.canHit()) {
            dealer.append(deck.draw());
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
