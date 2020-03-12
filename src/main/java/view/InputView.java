package view;

import dto.RequestPlayerNameDTO;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static RequestPlayerNameDTO inputPlayerName(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerName = SCANNER.nextLine();
        if( playerName == null || playerName.isEmpty())
        return new RequestPlayerNameDTO(SCANNER.nextLine());
    }

}

