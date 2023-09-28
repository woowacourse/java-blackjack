import controller.GameController;
import model.deck.Deck;
import view.GameView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardApplication {

    public static void main(String[] args) throws IOException {
        Deck cardDeck = new Deck();
        GameView view = createGameView();

        GameController controller = new GameController(cardDeck, view);
        controller.play();
    }

    private static GameView createGameView() {
        GameView view = new GameView(new BufferedReader(new InputStreamReader(System.in)));
        view.askPlayerNames();
        return view;
    }
}
