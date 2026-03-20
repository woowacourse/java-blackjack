package util;

import java.util.Arrays;
import java.util.List;
import vo.Money;

public class Parser {
    public List<String> parsePlayersName(String playersName) {
        return Arrays.stream(playersName.split(","))
                .map(String::trim)
                .toList();
    }

    public Money parseBettingMoney(String bettingMoney) {
        try {
            return new Money(Integer.parseInt(bettingMoney));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 숫자 외의 문자를 포함할 수 없습니다.");
        }
    }
}
