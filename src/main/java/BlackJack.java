import domain.deck.CardDeck;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Gambler;
import java.util.ArrayList;
import java.util.List;
import parser.PlayNameParser;
import view.InputView;

public class BlackJack {
    private static final int DEALER_CAN_STAND_SCORE = 17;
    private static final int BLACKJACK_MAX_SCORE = 21;
    private final CardDeck cardDeck;

    public BlackJack() {
        this.cardDeck = new Deck();
    }

    public void start(){
        Dealer dealer = new Dealer();
        List<Gambler>  gamblers = getGamblers();




    }

    private List<Gambler> getGamblers(){
        List<String> gamblerNames = PlayNameParser.splitNames(InputView.readLine());
        return gamblerNames.stream().map(Gambler::new);
    }
}
