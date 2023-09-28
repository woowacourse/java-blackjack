import controller.GameController;
import model.Deck;
import view.GameView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CardApplication {

    public static void main(String[] args) {
        Deck cardDeck = new Deck();
        GameView view = new GameView(new BufferedReader(new InputStreamReader(System.in)));

        GameController controller = new GameController(cardDeck, view);
    }
}
