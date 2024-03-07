package blackjack;

import blackjack.domain.CardGame;
import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    public static void main(String[] args) {
        List<String> names = InputView.readPlayerNames();

        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name), new Hand()))
                .toList();

        CardGame cardGame = new CardGame();
        cardGame.giveTwoCardsEachPlayer(players);

        Dealer dealer = new Dealer(new Name("딜러"), new Hand());
        cardGame.giveCard(dealer);
        cardGame.giveCard(dealer);

        OutputView.printInitialHandOfEachPlayer(dealer, players);
    }
}
