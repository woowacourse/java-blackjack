package blackjack.view;

import blackjack.domain.participant.Betting;
import blackjack.domain.participant.Name;
import blackjack.dto.NamesInput;
import blackjack.dto.PlayerInfos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String PLAYER_NAME_INPUT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String MORE_CARD_CHOICE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEW_LINE;
    private static final String NAME_SEPARATOR = ",";
    private static final String IOEXCEPTION_ERROR = "입력 과정 도중 에러가 발생했습니다.";
    public static final String BETTING_AMOUNT_INPUT = "%s의 배팅 금액은?" + NEW_LINE;

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final InputView instance = new InputView();


    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    private NamesInput readPlayersName() {
        System.out.println(PLAYER_NAME_INPUT);
        try {
            return NamesInput.from(Arrays.stream(bufferedReader.readLine().split(NAME_SEPARATOR))
                    .map(String::trim)
                    .toList());
        } catch (IOException exception) {
            throw new IllegalArgumentException(IOEXCEPTION_ERROR);
        }
    }

    public String readMoreCardChoice(final String name) {
        System.out.printf(MORE_CARD_CHOICE, name);
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            throw new IllegalArgumentException(IOEXCEPTION_ERROR);
        }
    }

    public PlayerInfos readPlayerInfos() {
        NamesInput names = readPlayersName();
        System.out.println();
        List<Betting> bettings = names.names().stream()
                .map(this::readBettingAmount)
                .toList();

        return PlayerInfos.of(names, bettings);
    }

    private Betting readBettingAmount(final Name name) {
        System.out.printf(BETTING_AMOUNT_INPUT, name);
        try {
            String input = bufferedReader.readLine();
            System.out.println();
            return new Betting(input);
        } catch (IOException exception) {
            throw new IllegalArgumentException(IOEXCEPTION_ERROR);
        }
    }
}
