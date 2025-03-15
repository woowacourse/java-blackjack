import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.Users;

public class Blackjack {

    public void start(Users users) {
        Dealer dealer = Dealer.createDealer();
        Deck deck = DeckGenerator.generateDeck();

        distributeInitialCards(dealer, users, deck);
        openInitialCards(dealer, users);
        usersHitOrStayPhase(users, deck);
        dealerHitWhileUnder16(dealer, deck);
        printGameResult();
    }

    private void distributeInitialCards(Dealer dealer, Users users, Deck deck) {
        dealer.drawInitialCards(deck);
        users.drawInitialCards(deck);
    }

    private void openInitialCards(Dealer dealer, Users users) {
    }

    private void usersHitOrStayPhase(Users users, Deck deck) {

    }

    private void dealerHitWhileUnder16(Dealer dealer, Deck deck) {
    }

    private void printGameResult() {
    }
}
