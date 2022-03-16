package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomCardsGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;

public class BlackjackRunner {

    public void run() {
        Deck deck = new Deck(new RandomCardsGenerator());
        Dealer dealer = new Dealer(deck.getInitCards());
        Players players = new Players(deck, InputView.getNames());

        List<Player> primaryPlayers = players.getValue();
        OutputView.printStart(dealer, primaryPlayers);

        primaryPlayers.forEach(player -> playing(deck, player));
        drawDealer(deck, dealer);

        OutputView.printResult(dealer, players.getValue());
    }

    private void playing(Deck deck, Player player) {
        PlayCommand playCommand = InputView.getPlayCommand(player);
        if (isPlaying(player, playCommand)) {
            drawCard(deck, player);
            playing(deck, player);
        }
    }

    private boolean isPlaying(Player player, PlayCommand playCommand) {
        return player.canHit() && playCommand.isYes();
    }

    private void drawCard(Deck deck, Player player) {
        player.append(deck.draw());
        OutputView.printPlayerCard(player);
    }

    private void drawDealer(Deck deck, Dealer dealer) {
        if (dealer.canHit()) {
            dealer.append(deck.draw());
            OutputView.printDealerDrawable();
            drawDealer(deck, dealer);
        }
    }
}
