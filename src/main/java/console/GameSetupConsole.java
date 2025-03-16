package console;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.Deck;
import model.participant.Bet;
import model.participant.Name;
import model.participant.Participants;
import view.GameSetupView;

public final class GameSetupConsole extends Console {
    private final GameSetupView gameSetupView = new GameSetupView();

    public Participants registerParticipants(final Deck deck) {
        Map<Name, Bet> registerInput = new LinkedHashMap<>();
        PlayerNames playerNames = readPlayerNames();
        for (Name playerName : playerNames.playerNames()) {
            Bet bet = readBet(playerName);
            registerInput.put(playerName, bet);
        }
        return Participants.initialize(registerInput, deck);
    }

    public void displaySetupResult(final Participants participants) {
        String setupResult = gameSetupView.getSetupResult(participants);
        display(setupResult);
    }

    private PlayerNames readPlayerNames() {
        display(gameSetupView.getInputNameGuide());
        List<Name> playerNames = Arrays.stream(readLine().split(","))
                .map(String::trim)
                .filter(name -> !name.isBlank() && !name.isEmpty())
                .map(Name::new)
                .toList();
        return new PlayerNames(playerNames);
    }

    private Bet readBet(final Name playerName) {
        display(gameSetupView.getBettingGuide(playerName));
        String input = readLine();
        try {
            return new Bet(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 입력할 수 있습니다.");
        }
    }
}
