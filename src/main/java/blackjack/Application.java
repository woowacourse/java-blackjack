package blackjack;

import static java.util.stream.Collectors.*;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(Card.VALUES);
        Dealer dealer = new Dealer(deck.getInitCards());

        List<String> names = InputView.getNames();
        List<Player> players = names.stream()
                .map(name -> new Player(name, deck.getInitCards()))
                .collect(toList());

        OutputView.printStartInfo(dealer, players);
        System.out.println();

        for (Player player : players) {
            playing(deck, player);
        }

        playing(deck, dealer);

        OutputView.printResultInfo(dealer, players);
    }

    public static void playing(Deck deck, Player player) {
        while (player.isPlaying()) {
            PlayCommand playCommand = InputView.getPlayCommand(player);

            if (playCommand == PlayCommand.YES) {
                player.combine(deck.draw());
                OutputView.printPlayerCardInfo(player);
            }

            if (playCommand == PlayCommand.NO) {
                OutputView.printPlayerCardInfo(player);
                break;
            }
        }
    }

    public static void playing(Deck deck, Dealer dealer) {
        while (dealer.isDrawable()) {
            OutputView.printDealerDrawableInfo();
            dealer.combine(deck.draw());
        }

        System.out.println("\n딜러는 17이상이라 카드를 더 이상 받지 않습니다.");
    }
}
