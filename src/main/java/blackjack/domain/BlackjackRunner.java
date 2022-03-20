package blackjack.domain;

import static blackjack.view.PlayCommand.YES;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomCardsGenerator;
import blackjack.domain.participant.BettingMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.PlayerResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;

public class BlackjackRunner {

    private static final double REVERSE_MONEY = -1;

    public void run() {
        Deck deck = new Deck(new RandomCardsGenerator());
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.getNames());
        ready(deck, dealer, players.getValue());
        OutputView.printStart(dealer, players.getValue());

        players.forEach(player -> playing(deck, player, YES));
        drawDealer(deck, dealer);

        List<Player> playersValue = players.getValue();
        OutputView.printResult(dealer, playersValue);
        OutputView.printProfit(getDealerProfit(dealer, playersValue), createPlayerResults(dealer, playersValue));
    }

    private void ready(Deck deck, Dealer dealer, List<Player> players) {
        readyParticipant(deck, dealer);
        players.forEach(player -> readyParticipant(deck, player));
    }

    private void readyParticipant(Deck deck, Participant participant) {
        if (!participant.isReady()) {
            participant.hit(deck.pick());
            readyParticipant(deck, participant);
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
        return player.isDrawable() && playCommand.isYes();
    }

    private void pickCard(Deck deck, Player player, PlayCommand playCommand) {
        if (playCommand.isYes()) {
            player.hit(deck.pick());
            OutputView.printPlayerCard(player);
        }

        if (playCommand.isNo()) {
            player.stay();
        }
    }

    private void drawDealer(Deck deck, Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.hit(deck.pick());
            OutputView.printDealerDrawable();
            drawDealer(deck, dealer);
        }
    }

    private String getDealerProfit(Dealer dealer, List<Player> players) {
        BettingMoney totalProfit = BettingMoney.ZERO;
        for (Player player : players) {
            BettingMoney profit = player.calculateProfit(dealer);
            totalProfit = totalProfit.add(profit.times(REVERSE_MONEY));
        }

        return totalProfit.getAmount();
    }

    private List<PlayerResult> createPlayerResults(Dealer dealer, List<Player> players) {
        return players.stream()
                .map(player -> new PlayerResult(player.getName(), player.calculateProfit(dealer).getAmount()))
                .collect(toList());
    }
}
