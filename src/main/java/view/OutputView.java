package view;

import java.util.stream.Collectors;

import domain.gamer.Gamer;
import domain.gamer.Gamers;

public class OutputView {
    public static void printPlayerNamesGuide(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printInitCardGuide(Gamers gamers) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("딜러와 ");
        stringBuffer.append(gamers.stream()
            .map(Gamer::getName)
            .collect(Collectors.joining(",")));
        stringBuffer.append(" 에게 카드 2장을 나누었습니다.");

        System.out.println(stringBuffer.toString());
    }

    public static void printGamersCard(Gamers gamers) {
        printGamerCard(gamers.getDealer());
        gamers.stream().forEach(OutputView::printGamerCard);
    }

    public static void printGamerCard(Gamer gamer) {
        System.out.println(gamer);
    }

    public static void printAddCardAtDealer() {
        System.out.println("딜러는 16이하라 카드 한장 더 받았습니다.");
    }

    public static void printCardsResultAndScore(Gamers gamers) {
        System.out.println(gamers.getDealer() + "-결과 : " + gamers.getDealer().getScore());
        gamers.stream().forEach(x -> System.out.println(x + "- 결과 : " + x.getScore()));
    }
}
