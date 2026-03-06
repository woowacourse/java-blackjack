import java.util.Random;

public class Application {

    public static void main(String[] args) {
        Random random = new Random();
        CardDistributor cardDistributor = new CardDistributor(new RandomCardPicker(random));

        BlackjackController blackjackController = new BlackjackController(cardDistributor);

        blackjackController.startGame();
    }
}
