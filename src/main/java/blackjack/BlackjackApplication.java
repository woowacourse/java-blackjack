package blackjack;

import blackjack.model.AceScoreCalculator;
import java.util.Scanner;

public class BlackjackApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AceScoreCalculator aceScoreCalculator = new AceScoreCalculator();

        System.out.print("현재 점수를 입력하세요: ");
        int currentScore = scanner.nextInt();

        int aceScore = aceScoreCalculator.calculateAceScore(currentScore);
        System.out.printf("ACE는 [%d]점으로 계산됩니다.\n", aceScore);
    }
}
