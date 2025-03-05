package blackjack.view;

import java.util.Scanner;

import blackjack.dto.NamesRequestDto;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static NamesRequestDto readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return NamesRequestDto.from(scanner.nextLine());
    }
}
