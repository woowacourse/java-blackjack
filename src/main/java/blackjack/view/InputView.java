package blackjack.view;

import java.util.Scanner;

import blackjack.dto.NamesRequestDto;
import blackjack.dto.SelectionRequestDto;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    // LAST TODO 메시지 상수화
    public static NamesRequestDto readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return NamesRequestDto.from(scanner.nextLine());
    }

    public static SelectionRequestDto readAdditionalCardSelection(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        return SelectionRequestDto.from(scanner.nextLine());
    }
}
