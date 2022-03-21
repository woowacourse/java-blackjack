package view;

import static java.util.stream.Collectors.toList;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Players;
import domain.service.InitService;
import domain.vo.Wallet;
import java.util.Arrays;
import java.util.List;
import util.Console;

public class ConsoleInitServiceImpl implements InitService {
    private static final int IGNORE_EMPTY_INPUT = -1;
    private static final int MAXIMUM_GAMBLER_NUMBER = 7;
    private static final String COMMA_DELIMITER = ",";
    private static final String INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BETTING_MONEY = "%s%s의 배팅 금액은?%n";
    private static final String ERROR_TOO_MANY_GAMBLERS = "[ERROR] 겜블러는 최대 7명까지 참여 가능합니다 : ";
    private static final String NEWLINE = System.lineSeparator();

    @Override
    public Players initPlayers() {
        Gamblers gamblers = new Gamblers(scanGamblerInfos()
                .stream()
                .map(Gambler::new)
                .collect(toList()));

        return new Players(gamblers, new Dealer());
    }

    private List<Wallet> scanGamblerInfos() {
        System.out.println(INPUT_PLAYER_NAMES);
        List<String> names = scanRawPlayerNames();
        validateMaximumGamblers(names.size());

        return names.stream()
                .map(name -> Wallet.of(name, scanBettingMoney(name)))
                .collect(toList());
    }

    private List<String> scanRawPlayerNames() {
        return Arrays.stream(Console.nextLine()
                        .split(COMMA_DELIMITER, IGNORE_EMPTY_INPUT))
                .map(String::trim)
                .collect(toList());
    }

    private void validateMaximumGamblers(int numberOfGamblers) {
        if (numberOfGamblers > MAXIMUM_GAMBLER_NUMBER) {
            throw new IllegalArgumentException(ERROR_TOO_MANY_GAMBLERS + numberOfGamblers);
        }
    }

    private int scanBettingMoney(String name) {
        System.out.printf(INPUT_BETTING_MONEY, NEWLINE, name);
        return Integer.parseInt(Console.nextLine());
    }

    @Override
    public CardDeck initCardDeck() {
        CardDeck cardDeck = CardDeck.newInstance();
        return cardDeck.shuffle();
    }
}
