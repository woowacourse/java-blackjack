public class Application {

    public static void main(String[] args) {

        GameManager manager = new GameManager();

        GameController controller = new GameController(manager);
        controller.run();
    }
}
