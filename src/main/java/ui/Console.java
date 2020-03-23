package ui;

import common.PlayerDto;
import common.PlayersDto;
import domain.UserInterface;
import domain.user.Player;
import utils.StringUtils;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
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

    //todo: playerNames와 bettingMoney 분리
    @Override
    public PlayersDto inputPlayers() {
        List<PlayerDto> playerDtos = new ArrayList<>();
        InputView.printPlayerNamesRequlest();
        List<String> playerNames = inputPlayerNames();
        for (String playerName : playerNames) {
            InputView.printRequestBettingMoney(playerName);
            int bettingMoney = inputBettingMoney(playerName);
            PlayerDto playerDto = PlayerDto.input(playerName, bettingMoney);
            playerDtos.add(playerDto);
        }

        return PlayersDto.of(playerDtos);
    }

    @Override
    public List<String> inputPlayerNames() {
        String input = scanner.nextLine();
        return StringUtils.parseWithDelimeter(input, DELIMITER);
    }

    @Override
    public int inputBettingMoney(String playerName) {
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }

    @Override
    public void showCurrentStateOfPlayer(Player player) {
        OutputView.printCurrentStateOfPlayer(player.serialize(PlayerDto.init()));
    }
}
