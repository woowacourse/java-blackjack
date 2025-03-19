package console;

import card.CardHand;
import card.Deck;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import participant.Bet;
import participant.Dealer;
import participant.Name;
import participant.Player;
import view.GameSetupView;

public final class GameSetupConsole extends Console {
    private final GameSetupView gameSetupView = new GameSetupView();

    public List<Player> registerPlayers(final Deck deck) {
        List<Player> players = new ArrayList<>();
        PlayerNames playerNames = readPlayerNames();
        for (Name playerName : playerNames.playerNames()) {
            Bet bet = readBet(playerName);
            players.add(new Player(playerName, bet, CardHand.drawInitialHand(deck)));
        }
        return players;
    }

    public void displaySetupResult(final Dealer dealer, final List<Player> players) {
        List<Name> playerNames = players.stream().map(Player::getName).toList();
        display(gameSetupView.getSetupHeader(playerNames));
        display(gameSetupView.getDealerSetupResult(dealer));
        for (Player player : players) {
            display(gameSetupView.getSetUpResult(player.getName(), player.openInitialCard()));
        }
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
