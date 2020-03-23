package ui;

import common.PlayerDto;
import domain.UserInterface;
import domain.user.Player;
import utils.StringUtils;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Scanner;

public class Console implements UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    @Override
    public String inputWantToHit(String playerName) {
        InputView.printRequestWantToHit(playerName);
        return scanner.nextLine();
    }

    @Override
    public List<String> inputPlayerNames() {
        InputView.printPlayerNamesRequlest();
        String input = scanner.nextLine();
        return StringUtils.parseWithDelimeter(input, DELIMITER);
    }

    @Override
    public int inputBettingMoney(String playerName) {
        InputView.printRequestBettingMoney(playerName);
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }

    @Override
    public void showCurrentStateOfPlayer(Player player) {
        OutputView.printCurrentStateOfPlayer(PlayerDto.of(player.getName(),
                player.getBettingBoney(),
                player.getCards().serialize(),
                player.getScore()));
    }
}
