package io;

import java.util.List;
import java.util.Scanner;

public class ConsoleInput {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readParticipantsNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitNames(scanner.nextLine());
    }

    public String readShouldHit(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
        String response = scanner.nextLine();
        validateYesOrNo(response);
        return response;
    }

    private List<String> splitNames(String names) {
        return List.of(names.replaceAll(" ", "").split(","));
    }

    private void validateYesOrNo(String response) {
        response = response.toLowerCase();
        if (!(response.equals("y") || response.equals("n"))) {
            throw new IllegalArgumentException("[ERROR] y 또는 n 만 입력 가능합니다.");
        }
    }
}
