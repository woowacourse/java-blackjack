package view;

import domain.Command;
import domain.user.Name;
import domain.user.Player;
import domain.user.Users;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Users inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> names = List.of(scanner.nextLine().split(",", -1));
        List<Player> collect = names.stream()
                .map((name) -> new Player(new Name(name)))
                .collect(Collectors.toList());
        return new Users(collect);
    }

    public static Command inputAddCommand(Name name) {
        System.out.println(name.value() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return Command.get(scanner.nextLine());
    }
}
