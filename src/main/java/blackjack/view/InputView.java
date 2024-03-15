package blackjack.view;

import blackjack.InputMapper;
import blackjack.domain.DrawDecision;
import blackjack.domain.bet.Bet;
import blackjack.domain.bet.Bets;
import blackjack.domain.bet.BettingBank;
import blackjack.domain.bet.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final InputMapper inputMapper;
    private final Scanner scanner = new Scanner(System.in);

    public InputView(InputMapper inputMapper) {
        this.inputMapper = inputMapper;
    }

    public List<PlayerName> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return inputMapper.mapToPlayerNames(scanner.nextLine());
    }

    public DrawDecision readDrawDecision(String name) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        System.out.println(String.join("", LINE_SEPARATOR, message));
        return inputMapper.mapToDrawDecision(scanner.nextLine());
    }

    public Bets readBets(Players players) {
        return new Bets(players.getPlayers().stream()
                .map(Player::getName)
                .map(this::readBet)
                .toList());
    }

    public BettingBank readBetInformation(Players players) {
        return new BettingBank(players.getPlayers().stream()
                .collect(Collectors.toMap(player -> player, this::readBetAmount)));
    }

    public Money readBetAmount(Player player) {
        System.out.println(String.format("%s의 배팅 금액은?", player.getName()));
        return new Money(Integer.parseInt(scanner.nextLine()));
    }

    public Bet readBet(String playerName) {
        System.out.println(String.format("%s의 배팅 금액은?", playerName));
        return inputMapper.mapToBet(playerName, Integer.parseInt(scanner.nextLine()));
    }
}
