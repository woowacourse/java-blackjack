package view;

import java.util.stream.Collectors;

import domain.card.Card;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;

public class OutputView {
    private static final String NEWLINE = System.getProperty("line.separator" );

    public static void printPlayerNamesGuide() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)" );
    }

    public static void printInitCardGuide(Gamers gamers) {
        StringBuffer initCardGuide = new StringBuffer();
        initCardGuide.append(NEWLINE);
        initCardGuide.append("딜러와 " );
        initCardGuide.append(gamers.stream()
                .map(Gamer::getName)
                .collect(Collectors.joining("," )));
        initCardGuide.append(" 에게 카드 2장을 나누었습니다." );

        System.out.println(initCardGuide.toString());
    }

    public static void printGamersCard(Gamers gamers) {
        printGamerCard(gamers.getDealer());
        gamers.stream().forEach(OutputView::printGamerCard);
        System.out.println();
    }

    public static void printGamerCard(Gamer gamer) {
        System.out.println(printCards(gamer));
    }

    private static String printCards(Gamer gamer) {
        StringBuilder cardsToString = new StringBuilder();
        cardsToString.append(gamer.getName());
        cardsToString.append(" : " );
        cardsToString.append(gamer.getCards()
                .stream()
                .map(OutputView::printCard)
                .collect(Collectors.joining(", " )));
        return cardsToString.toString();
    }

    public static String printCard(Card card) {
        StringBuilder cardToString = new StringBuilder();
        cardToString.append(card.getCardNumber().getCardInitial());
        cardToString.append(card.getCardSuit().getSuit());
        return  cardToString.toString();
    }

    public static void printAddCardAtDealer() {
        System.out.println("딜러는 16이하라 카드 한장 더 받았습니다." );
    }

    public static void printAddCardGuide(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName());
    }

    public static void printCardsResultAndScore(Gamers gamers) {
        System.out.println();
        System.out.printf("%s - 결과 : %s" + NEWLINE, printCards(gamers.getDealer()), gamers.getDealer().calculateWithAce());
        gamers.stream().forEach(player -> System.out.printf("%s - 결과 : %s" + NEWLINE, printCards(player), player.calculateWithAce()));
    }
}
