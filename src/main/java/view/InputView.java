package view;

import domain.BlackJackGame;
import domain.Money;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return List.of(input.split(","));
    }

    public Map<Participant, Money> askBettingMoney(BlackJackGame blackJackGame) {
        Participants participants = blackJackGame.getParticipants().findPlayers();
        Map<Participant, Money> gamblingStatement = new HashMap<>();
        for (Participant onlyPlayer : participants.getParticipants()) {
            System.out.printf("%s의 배팅 금액은?%n", onlyPlayer.getNickname());
            gamblingStatement.put(onlyPlayer, new Money(scanner.nextLine()));
        }

        return gamblingStatement;
    }

    public boolean askDrawOneMore(Participant participant) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", participant.getNickname());
        return Answer.from(scanner.nextLine());
    }

    public void closeScanner() {
        scanner.close();
    }

}
