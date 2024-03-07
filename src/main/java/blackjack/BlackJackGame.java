package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    //TODO 2개로 줄이기 (논의사항)
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Deck deck = Deck.createShuffledDeck();

    public void run() {
        Dealer dealer = new Dealer();
        List<String> names = inputView.inputPlayerNames();
        Players players = Players.from(names);

        dealer.drawStartCards(deck);
        players.drawStartCards(deck);
        outputView.printStartCards(dealer, players);

        players.play(this::playTurn);
        while (dealer.isDrawable()) {
            outputView.printDealerDraw();
            dealer.add(deck.draw());
        }

        outputView.printResultCardAndScore(dealer, players);

        outputView.printDealerMatchResult(dealer.match(players));
        for (Player player : players.getPlayers()) {
            outputView.printPlayerMatchResult(player.getName(), player.isWin(dealer));
        }
    }

    private void playTurn(Player player) {
        while (player.isDrawable() && inputView.isPlayerWantDraw(player.getName())) {
            player.add(deck.draw());
            outputView.printPlayerCards(player);
        }
    }
}
