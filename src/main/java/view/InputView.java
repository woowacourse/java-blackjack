package view;

import domain.Name;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] input = scanner.nextLine().split(",", -1);
        return Arrays.stream(input).toList();
    }

    public boolean readHitOpinion(Name name) {
        System.out.println();
        System.out.println(name + "는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String opinion = scanner.nextLine();
        try {
            return Opinion.from(opinion).value;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readHitOpinion(name);
        }
    }

    private enum Opinion {
        POSITIVE("y", true),
        NEGATIVE("n", false);

        private String key;
        private boolean value;

        Opinion(String key, boolean value) {
            this.key = key;
            this.value = value;
        }

        private static Opinion from(String opinion) {
            return Arrays.stream(values())
                    .filter(v -> v.key.equals(opinion))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 값입니다."));
        }
    }
}
