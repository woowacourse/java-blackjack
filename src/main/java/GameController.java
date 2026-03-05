import domain.User;


import java.util.List;

public class GameController {

    private final GameService gameService;
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputVIew, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputVIew;
        this.gameService = gameService;
    }

    public void run() {
        List<User> users = setUpUsers();
        processUserTurns(users);
    }

    private List<User> setUpUsers(){
        String input = inputView.readUsers();
        return InputParser.parseUsers(input);
    }

    private void processUserTurns(List<User> users) {
        for (User user : users) {
            while (inputView.readWillHit(user.getName())) {
                user.receiveCard(gameService.deal());
                outputView.printHand(user);
            }
        }
    }
}
