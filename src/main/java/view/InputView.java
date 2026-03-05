package view;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public List<String> readPlayerNames(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] split = input.split(",");
        return List.of(split);
    }

    public String readHitStand(String name){
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
