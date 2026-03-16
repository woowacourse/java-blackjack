package view;

import domain.card.Card;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantDto;
import dto.PlayerProfitDto;
import java.util.List;
import view.formatter.CardSuitFormatter;
import view.formatter.CardValueFormatter;

public class OutputView {

    public void printStartCardMessage(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public void printDealerStartCard(Card dealerFirstCard) {
        System.out.println("딜러카드: " + CardValueFormatter.from(dealerFirstCard.getCardValue()) + CardSuitFormatter.from(dealerFirstCard.getCardSuit()));
    }

    public void printStartCard(Players players) {
        for (Player player : players.getPlayers()) {
            printCurrentHoldCard(player);
        }
        System.out.println();
    }

    public void printCurrentHoldCard(Player player) {
        System.out.println(player.getName() + "카드: " + cardsToString(player.getHand()));
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

    public void printPlayerFinalProfit(List<PlayerProfitDto> playerProfitDtos) {
        for (PlayerProfitDto playerProfitDto : playerProfitDtos) {
            System.out.println(playerProfitDto.name() + ": " + playerProfitDto.profit());
        }
    }

    private void printParticipantScore(ParticipantDto participantDto) {
        System.out.println(participantDto.name() + "카드: " + cardsToString(participantDto.cards()) + " - 결과: " + participantDto.score());
    }

    private String cardsToString(List<Card> hand) {
        return String.join(", ", hand.stream().map(card -> CardValueFormatter.from(card.getCardValue()) + CardSuitFormatter.from(card.getCardSuit())).toList());
    }
}
