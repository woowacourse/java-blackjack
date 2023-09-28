import controller.GameController;
import model.Deck;

public class CardApplication {

    public static void main(String[] args) {
        Deck cardDeck = new Deck();
        GameController controller = new GameController(cardDeck);
    }
}
