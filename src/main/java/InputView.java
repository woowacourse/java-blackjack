import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public List<String> readPlayerName() {
        Scanner scanner = new Scanner(System.in);
        List<String> playerNames = new ArrayList<>();
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String s = scanner.nextLine();
        System.out.println();

        if (s == null || s.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(s.split(","))
                .map(String::trim) // split된 항목 앞 뒤 공백 제거
                .filter(c -> !c.isEmpty()) // 콤마가 두번 겹치는 경우 필터링
                .collect(Collectors.toList());
    }
}
