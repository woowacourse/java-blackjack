import domain.User;


import java.util.List;

public class GameController {

    private final GameService gameService;
    private final InputView inputView;

    public GameController(InputView inputView, GameService gameService) {
        this.inputView = inputView;
        this.gameService = gameService;
    }

    public void run() {
        List<User> users = setUpUsers();
    }

    public List<User> setUpUsers(){
        String input = inputView.readUsers();
        return InputParser.parseUsers(input);
    }
}
