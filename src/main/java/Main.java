import controller.BlackJackController;
import domain.result.ResultJudge;

public class Main {
    public static void main(String[] args) {
        ResultJudge resultJudge = new ResultJudge();
        BlackJackController blackJackController = new BlackJackController(resultJudge);
        blackJackController.run();
    }
}
