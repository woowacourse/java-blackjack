package util;

import java.util.List;
import java.util.regex.Pattern;
import vo.Money;

public class Parser {
    public List<String> parseParticipantsName(String participantsName) {
        return List.of(participantsName.split(","));
    }

    public Money parseBettingMoney(String bettingMoney) {
        try {
            return new Money(Integer.parseInt(bettingMoney));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자 외의 문자를 포함할 수 없습니다.");
        }
    }
}
