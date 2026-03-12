package view;

import dto.AgreementRequestDto;
import dto.NameRequestDto;
import java.util.Scanner;

public class InputView {
    private Scanner sc = new Scanner(System.in);

    public NameRequestDto askGamblerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return new NameRequestDto(sc.nextLine());
    }

    public AgreementRequestDto askHitOrStand(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return new AgreementRequestDto(sc.nextLine());
    }

}
