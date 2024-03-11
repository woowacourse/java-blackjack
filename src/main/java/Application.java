import controller.Controller;
import view.ResultView;
import view.card.CardView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new CardView(), new ResultView());
        controller.play();
    }
}
