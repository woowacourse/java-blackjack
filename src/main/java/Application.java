import controller.Controller;
import view.ResultView;
import view.UserView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new UserView(), new ResultView());
        controller.play();
    }
}
