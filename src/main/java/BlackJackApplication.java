import domain.card.providable.CardDeck;
import domain.gamer.AllGamers;
import domain.gamer.Dealer;
import domain.gamer.Player;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {
    public static void main(String[] args) {
        List<Player> players = inputPlayerNames();
        Dealer dealer = new Dealer();
        AllGamers allGamers = new AllGamers(dealer, players);
        CardDeck cardDeck = new CardDeck();

        allGamers.drawFirstPhase(cardDeck);
        OutputView.printInitialCards(allGamers);
    }

    private static List<Player> inputPlayerNames() {
        return InputView.inputPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }
}
