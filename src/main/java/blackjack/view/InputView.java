package blackjack.view;

import blackjack.dto.request.BetsRequestDto;
import blackjack.dto.request.NamesRequestDto;
import blackjack.dto.request.SelectionRequestDto;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static NamesRequestDto readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return NamesRequestDto.from(scanner.nextLine());
    }

    public static BetsRequestDto readBets(String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);
        return BetsRequestDto.from(scanner.nextLine());
    }

    public static SelectionRequestDto readAdditionalCardSelection(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        return SelectionRequestDto.from(scanner.nextLine());
    }
}
