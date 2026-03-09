import controller.BlackJackController;
import domain.result.ResultJudge;
import service.BlackJackService;

public class Main {
    public static void main(String[] args) {
        ResultJudge resultJudge = new ResultJudge();
        BlackJackService blackJackService = new BlackJackService();
        BlackJackController blackJackController = new BlackJackController(resultJudge, blackJackService);
        blackJackController.run();
    }
}
