package console;

import controller.Controller;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Bet;
import model.Name;
import model.Players;
import view.PlayerNames;

public final class PlayerRegisterConsole extends Console {
    private final Controller controller;

    public PlayerRegisterConsole(final Controller controller) {
        this.controller = controller;
    }

    public Players registerPlayers() {
        Map<Name, Bet> registerInput = new LinkedHashMap<>();
        PlayerNames playerNames = readPlayerNames();
        for (Name playerName : playerNames.playerNames()) {
            Bet bet = readBet(playerName);
            registerInput.put(playerName, bet);
        }
        return new Players(registerInput);
    }

    private PlayerNames readPlayerNames() {
        display(controller.guideToInputName());
        List<Name> playerNames = Arrays.stream(readLine().split(","))
                .map(String::trim)
                .filter(name -> !name.isBlank() && !name.isEmpty())
                .map(Name::new)
                .toList();
        return new PlayerNames(playerNames);
    }

    private Bet readBet(final Name playerName) {
        display(controller.guideToBet(playerName));
        String input = readLine();
        try {
            return new Bet(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 입력할 수 있습니다.");
        }
    }
}
