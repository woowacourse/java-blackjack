import controller.GameHostController;
import controller.GameRuleController;

public class Application {
    public static void main(String[] args) {
        GameHostController gameHostController = new GameHostController();
        GameRuleController gameRuleController = new GameRuleController(gameHostController.getBetAmounts(),
                gameHostController.startGame());
        gameRuleController.printResult();
    }
}
