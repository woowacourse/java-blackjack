package view;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import utils.Parser;
import validator.Validator;


public class InputView {

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = readInput(List.of(
                Validator::validateNotBlank
        ));
        return Parser.splitBy(input,",");
    }

    private String readInput(List<Validator> validators) {
        try{
            String input = new Scanner(System.in).nextLine();
            for (Validator v : validators) {
                v.validate(input);
            }
            return input;
        } catch(NoSuchElementException e){
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }

    }
}
