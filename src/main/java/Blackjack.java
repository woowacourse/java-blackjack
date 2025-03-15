import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.Users;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;

public class Blackjack {

    private static Users createUsers() {
        List<String> userNames = InputView.inputUserNames();
        Map<String, Integer> betByName = new LinkedHashMap<>();
        for (String name : userNames) {
            betByName.put(name, InputView.inputBet(name));
        }
        return Users.from(betByName);
    }

    public void start() {
        Users users = createUsers();
        Dealer dealer = Dealer.createDealer();
        Deck deck = DeckGenerator.generateDeck();

        distributeInitialCards(dealer, users, deck);
        UsersHitOrStayPhase(users, deck);
        DealerHitWhileUnder16(dealer, deck);
        printGameResult();
    }

    private void distributeInitialCards(Dealer dealer, Users users, Deck deck) {
    }

    private void UsersHitOrStayPhase(Users users, Deck deck) {
    }

    private void DealerHitWhileUnder16(Dealer dealer, Deck deck) {
    }

    private void printGameResult() {
    }
}
