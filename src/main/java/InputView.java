import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = scanner.nextLine();
        return Arrays.asList(rawNames.split(","));
    }

    public String readHitOrNot(Player player) {
        System.out.println(player.getPlayerName() + "은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
