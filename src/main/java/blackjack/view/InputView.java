package blackjack.view;

import blackjack.domain.player.Challenger;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String ASK_MORE_CARDS = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(scanner.nextLine().split(","));
    }

    public static boolean wantMoreCard(final Challenger challenger) {
        try {
            System.out.println(String.format(ASK_MORE_CARDS, challenger.getName()));
            String yesOrNo = scanner.nextLine();
            validateYesOrNo(yesOrNo);
            return "y".equals(yesOrNo);
        } catch (IllegalArgumentException e){
            return wantMoreCard(challenger);
        }
    }

    public static void validateYesOrNo(String yesOrNo){
        yesOrNo = yesOrNo.trim().toLowerCase();
        if(!("y".equals(yesOrNo) || "n".equals(yesOrNo))){
            throw new IllegalArgumentException("y 혹은 n 으로만 입력해주세요.");
        }
    }
}
