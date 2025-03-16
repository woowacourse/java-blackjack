package blackjack;

import blackjack.card.CardDeck;
import blackjack.card.Hand;
import blackjack.card.initializer.StandardCardsInitializer;
import blackjack.gamer.Betting;
import blackjack.gamer.Dealer;
import blackjack.gamer.Nickname;
import blackjack.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackGame {

    private static final String YES_SIGN = "y";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        CardDeck deck = new CardDeck(new StandardCardsInitializer());

        Dealer dealer = new Dealer(new Hand(deck));
        List<Player> players = setPlayers(deck);

        outputView.printInitialCards(dealer, players);

        processPlayersTurn(players, deck);

        processDealerTurn(dealer, deck);

        outputView.printCardResult(dealer, players);

        outputView.printProfitResult(dealer.calculateProfits(players));
    }

    private List<Player> setPlayers(CardDeck deck) {
        List<Player> players = new ArrayList<>();

        List<Nickname> nicknames = inputView.readNames().stream()
                .map(Nickname::new)
                .toList();

        for (Nickname nickname : nicknames) {
            Betting betting = new Betting(inputView.readBettingAmount(nickname));
            players.add(new Player(new Hand(deck), nickname, betting));
        }

        return players;
    }

    private void processPlayersTurn(List<Player> players, CardDeck deck) {
        for (Player player : players) {
            processPlayerAction(player, deck);
        }
    }

    private void processPlayerAction(Player player, CardDeck deck) {
        while (!player.isFinished()) {
            selectChoice(player, deck);
        }
    }

    private void selectChoice(Player player, CardDeck deck) {
        if (wantHit(player)) {
            player.hit(deck);
            outputView.printCards(player);
            return;
        }
        player.stay();
    }

    private boolean wantHit(Player player) {
        return YES_SIGN.equals(inputView.readYesOrNo(player));
    }

    private void processDealerTurn(Dealer dealer, CardDeck deck) {
        if (!dealer.isFinished()) {
            dealer.hit(deck);
            outputView.printDealerHitSuccess();
        }
    }
}
