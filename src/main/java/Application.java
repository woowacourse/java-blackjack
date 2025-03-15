import domain.player.Users;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();

        List<String> userNames = InputView.inputUserNames();
        Map<String, Integer> betByName = new LinkedHashMap<>();
        for (String name : userNames) {
            betByName.put(name, InputView.inputBet(name));
        }
        Users users = Users.from(betByName);

        blackjackGame.start(users);
    }
}
