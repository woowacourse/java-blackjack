package view;

import domain.participant.Bet;
import exception.BlankInputException;
import utils.Parser;
import validator.NumberRangeValidator;
import validator.Validator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class InputView {

    private static final String DELIMITER = ",";

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = readInput(List.of(
                Validator::validateNotBlank
        ));
        return Parser.splitBy(input, DELIMITER);
    }

    public boolean readAdditionalCard(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validateChoice
        ));

        return input.equals("y");
    }

    public Bet readBet(String playerName) {
        System.out.printf("%s의 배팅 금액은?%n", playerName);
        String input = readInput(List.of(
                Validator::validateNotBlank,
                NumberRangeValidator.createPositiveRange()
        ));

        return new Bet(Long.parseLong(input));
    }

    private String readInput(List<Validator> validators) {
        try {
            String input = new Scanner(System.in).nextLine();
            for (Validator validator : validators) {
                validator.validate(input);
            }
            return input;
        } catch (NoSuchElementException e) {
            throw new BlankInputException();
        }
    }
}
