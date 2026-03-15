package view;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardSuit;
import domain.card.CardValue;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import dto.ParticipantDto;
import java.util.List;

public class OutputView {

    public void printStartCardMessage(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public void printDealerStartCard(CardValue cardValue, CardSuit cardSuit) {
        System.out.println("딜러카드: " + cardValue.getName() + getCardSuit(cardSuit));
    }

    public void printStartCard(List<ParticipantDto> playerDtos) {
        for (ParticipantDto participant : playerDtos) {
            printCurrentHoldCard(participant);
        }
        System.out.println();
    }

    public void printCurrentHoldCard(ParticipantDto dto) {
        System.out.println(dto.name() + "카드: " + cardsToString(dto.cards()));
    }

    public void printDealerReceiveCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(ParticipantDto dealerDto, List<ParticipantDto> playerDtos) {
        System.out.println();
        printParticipantScore(dealerDto);
        for (ParticipantDto participant : playerDtos) {
            printParticipantScore(participant);
        }
    }

    public void printDealerFinalProfit(int dealerFinalProfit) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealerFinalProfit);
    }

    public void printPlayerFinalProfit(List<Player> players, Participant participant) {
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.finalProfit(participant));
        }
    }

    private void printParticipantScore(ParticipantDto participant) {
        System.out.println(participant.name() + "카드: " + cardsToString(participant.cards()) + " - 결과: " + participant.score());
    }

    private String cardsToString(List<Card> hand) {
        return String.join(", ", hand.stream().map(card -> card.getCardValue().getName() + getCardSuit(card.getCardSuit())).toList());
    }

    private String getCardSuit(CardSuit cardSuit) {
        return switch (cardSuit) {
            case HEART -> "하트";
            case SPADE -> "스페이드";
            case CLUB -> "클로버";
            case DIAMOND -> "다이아몬드";
        };
    }

    private String getGameResult(GameResult gameResult) {
        return switch (gameResult) {
            case WIN -> "승";
            case DRAW -> "무";
            case LOSE -> "패";
        };
    }
}
