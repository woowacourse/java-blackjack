import domain.Dealer;
import domain.User;


import java.util.List;

public class GameController {

    private final GameService gameService;
    private final InputView inputView;
    private final OutputView outputView;

    private final Dealer dealer = new Dealer();

    public GameController(InputView inputView, OutputView outputVIew, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputVIew;
        this.gameService = gameService;
    }

    public void run() {
        List<User> users = setUpUsers();
        initDeal(users);
        processUserTurns(users);
    }

    private List<User> setUpUsers(){
        String input = inputView.readUsers();
        return InputParser.parseUsers(input);
    }

    private void initDeal(List<User> users){
        gameService.initDeal(users, dealer);
    }

    private void processUserTurns(List<User> users) {
        for (User user : users) {
            while (inputView.readWillHit(user.getName())) {
                user.receiveCard(gameService.deal());
                outputView.printHand(user);
            }
            if (!hitAtLeastOnce) {
                outputView.printHand(user);
            }
        }
    }

    private void processDealerTurn(Dealer dealer) {
        int sum = dealer.getHand().stream().mapToInt(Card::getValue).sum();
        if(gameService.isHit(sum)) {
            dealer.receiveCard(gameService.deal());
            outputView.printDealerHit();
        }
    }
}
