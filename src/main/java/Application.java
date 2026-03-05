public class Application {

    public static void main(String[] args) {

        GameController controller = new GameController(new GameManager(), new InputView(), new OutputView());
        controller.run();
    }
}
