import domain.BlackJackController;

public class BlackJackApplicaion {
    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.askEachPlayers();
        blackJackController.dealerDistributeOrNot();
        blackJackController.printFinalGameStatus();
        blackJackController.printFinalFightResult();
    }
}
