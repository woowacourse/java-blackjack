package view;

import domain.Money;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public final class InputView {

  private static final String NAME_DELIMITER = ",";
  private final Scanner sc;

  public InputView() {
    this.sc = new Scanner(System.in);
  }

  public Map<String, Money> readPlayerNames() {
    System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    final String input = sc.nextLine();
    final var names = input.split(NAME_DELIMITER);
    Map<String, Money> participants = new HashMap<>();
    for (String name : names) {
      System.out.printf("%s의 배팅금을 입력해주세요" + System.lineSeparator(), name);
      String money = sc.nextLine();
      participants.put(name, new Money(Integer.parseInt(money)));
    }
    return participants;
  }

  public boolean readPlayerAnswer(final String playerName) {
    System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator(), playerName);
    final String input = sc.nextLine();
    return Objects.equals(input, "y");
  }
}
