package blackjack.dto;

import blackjack.domain.Participants;
import blackjack.domain.Person;

import java.util.HashMap;
import java.util.Map;

public class BettingMoneyDto {

    private final Map<Person, Integer> bettingMoney;

    private BettingMoneyDto(Map<Person, Integer> bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public static BettingMoneyDto of(Map<String, Integer> bettingMoney, Participants participants) {
        Map<Person, Integer> converted = convertToMapPerson(bettingMoney, participants);
        return new BettingMoneyDto(converted);
    }

    private static Map<Person, Integer> convertToMapPerson(Map<String, Integer> bettingMoney, Participants participants) {
        Map<Person, Integer> converted = new HashMap<>();
        bettingMoney.forEach((name, money) -> {
            Person person = participants.findByName(name);
            converted.put(person, money);
        });
        return converted;
    }

    public Map<Person, Integer> getBettingMoney() {
        return bettingMoney;
    }
}
