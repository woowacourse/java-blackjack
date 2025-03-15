package console;

import controller.Controller;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Name;
import model.Players;
import view.PlayerNames;

public final class PlayerRegisterConsole extends Console {
    private final Controller controller = new Controller();

    public Players registerPlayers() {
        Map<String, Integer> registerInput = new LinkedHashMap<>();
        PlayerNames playerNames = readPlayerNames();
//        for (String playerName : playerNames) {
//            String guideToBettingAmountView = controller.guideToBettingAmount(playerName);
//            display(guideToBettingAmountView);
//            int bettingAmount = readBettingAmount();
//            registerInput.put(playerName, bettingAmount);
//        }
//        return controller.registerPlayers(registerInput);
        return null;
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

//    private int readBettingAmount() {
//        String input = readLine(); // 검증 필요
//        return Integer.parseInt(input);
//    }
}
