import domain.*;
import factory.CardFactory;
import factory.PlayerFactory;
import util.ResultCalculator;
import view.InputView;

public class Application {
    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck(CardFactory.create());
        Dealer dealer = new Dealer();
        Players players = new Players(PlayerFactory.create(InputView.inputNames()));

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
