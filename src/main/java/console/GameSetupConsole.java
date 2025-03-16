package console;

import controller.GameSetupController;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.Deck;
import model.participant.Bet;
import model.participant.Dealer;
import model.participant.Name;
import model.participant.Players;
import view.PlayerNames;

public final class GameSetupConsole extends Console {
    private final GameSetupController gameSetupController = new GameSetupController();

    public Players registerPlayers(final Deck deck) {
        Map<Name, Bet> registerInput = new LinkedHashMap<>();
        PlayerNames playerNames = readPlayerNames();
        for (Name playerName : playerNames.playerNames()) {
            Bet bet = readBet(playerName);
            registerInput.put(playerName, bet);
        }
        return new Players(registerInput, deck);
    }

    public Dealer registerDealer(final Deck deck) {
        return null;
    }

    private PlayerNames readPlayerNames() {
        display(gameSetupController.guideToInputName());
        List<Name> playerNames = Arrays.stream(readLine().split(","))
                .map(String::trim)
                .filter(name -> !name.isBlank() && !name.isEmpty())
                .map(Name::new)
                .toList();
        return new PlayerNames(playerNames);
    }

    private Bet readBet(final Name playerName) {
        display(gameSetupController.guideToBet(playerName));
        String input = readLine();
        try {
            return new Bet(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 입력할 수 있습니다.");
        }
    }
}
