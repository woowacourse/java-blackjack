package view;

import domain.Participant;
import domain.Player;
import dto.ParticipantCardsDto;

import java.util.List;

public class OutputView {

    public static void printGameInitialMessage(List<String> playersNames) {
        String playersNamesMessage = String.join(",", playersNames);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", playersNamesMessage);
    }

    public static void printCards(ParticipantCardsDto participantCardsDto) {
        String cardsInfoMessage = String.join(",", participantCardsDto.cardsInfo());
        System.out.printf("%s: %s%n", participantCardsDto.name(), cardsInfoMessage);
    }

    public static void printDealerMessage(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

}
