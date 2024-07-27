package BlackJack;

import java.util.List;
import java.util.Scanner;

public class InputView {

	static Scanner sc = new Scanner(System.in);
	
	  public static String getPlayerNames() {
	        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
	        return sc.next();
	  }
	    
}
