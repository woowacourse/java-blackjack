package view;

import java.util.Scanner;
import view.requestdto.AgreementRequestDto;
import view.requestdto.BettingAmountRequestDto;
import view.requestdto.NameRequestDto;

public class InputView {

    public static final String HIT = "y";
    public static final String STAND = "n";
    private Scanner sc = new Scanner(System.in);

    public NameRequestDto askGamblerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return NameRequestDto.from(sc.nextLine());
    }

    public AgreementRequestDto askHitOrStand(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 " + HIT + " 아니오는 " + STAND + ")");
        return new AgreementRequestDto(sc.nextLine());
    }

    public BettingAmountRequestDto askBettingAmount(String name) {
        System.out.println(name + "의 배팅 금액은?");
        return new BettingAmountRequestDto(sc.nextLine());
    }
}
