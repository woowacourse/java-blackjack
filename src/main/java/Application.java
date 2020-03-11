import domain.*;
import factory.CardFactory;
import factory.PlayerFactory;
import util.ResultCalculator;

public class Application {
    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck(CardFactory.create());
        Dealer dealer = new Dealer();
        Players players = new Players(PlayerFactory.create("player1,player2"));

        cardDeck.shuffle();
        dealer.addCard(cardDeck.drawOne());
        dealer.addCard(cardDeck.drawOne());

        for(Player player : players){
            dealer.giveOneCard(cardDeck,player);
            dealer.giveOneCard(cardDeck,player);
        }

        if(dealer.shouldAddCard()){
            dealer.addCard(cardDeck.drawOne());
        }

        Results results = ResultCalculator.getResults(dealer,players);
        results.printAll();
    }
}
