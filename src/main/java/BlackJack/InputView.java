package BlackJack;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {

	static Scanner sc = new Scanner(System.in);
	
	  public static String getPlayerNames() {
	        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
	        return sc.next();
	  }
	    
	  public String getPlayerChoice(String playerName) {
		  String choice;
	        while (true) {
	            System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
	            choice = sc.next();
	            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")) {
	                break;
	            } 
	            if (!choice.equalsIgnoreCase("y")  && !choice.equalsIgnoreCase("n")) {
	                System.out.println("잘못된 입력입니다. 예는 y, 아니오는 n 중 하나를 입력하세요.");
	            }
	        }
	        return choice;
	  }
	  
	  public int getMoney(Player player) {
		  int money = 0;
	        while (true) {
	            System.out.println(player.getName() + "의 배팅 금액은?");
	            try {
	                money = sc.nextInt();
	                if (money > 0) {
	                    break;
	                } else {
	                    System.out.println("배팅 금액은 0보다 커야 합니다. 다시 입력하세요.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
	                sc.next();
	            }
	        }
	        return money;
	  }
}
