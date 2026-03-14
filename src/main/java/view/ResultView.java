package view;

import static domain.BlackjackGame.DEALER_HIT_STAND_BOUNDARY;

import domain.BlackjackGame;
import domain.Score;
import domain.card.Card;
import domain.dto.RoundResult;
import domain.dto.TotalResult;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final String DELIMITER = ", ";

    public void printParticipantsCards(List<Player> players, Dealer dealer) {
        printEmptyLine();
        System.out.println(
                dealer.getName().getValue() + "와 " + joinPlayersNameByDelimiter(players) + "에게 "
                        + BlackjackGame.DEFAULT_HAND_NUMBER
                        + "장을 나누었습니다.");
        Card dealerCard = dealer.getFirstCard();
        System.out.println("딜러카드: " + dealerCard.getRank().getDisplayValue() + dealerCard.getSuit().getValue());
        printParticipantsCard(players);
        printEmptyLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private String joinPlayersNameByDelimiter(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .map(Name::getValue)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printParticipantsCard(List<Player> players) {
        for (Player player : players) {
            printCards(player);
        }
    }

    public void printCards(Player player) {
        System.out.println(
                player.getName().getValue() + "카드: " + cardsToString(player.getCards().getCards()));
    }

    private String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getRank().getDisplayValue() + card.getSuit().getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    public void printDealerHitStand(boolean value) {
        printEmptyLine();
        if (value) {
            System.out.println("딜러는 " + DEALER_HIT_STAND_BOUNDARY.value() + "이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("딜러는 " + (DEALER_HIT_STAND_BOUNDARY.add(new Score(1)).value()) + "이상이라 카드를 받지 않았습니다.");
    }

    public void printResult(TotalResult results) {
        printEmptyLine();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + results.dealerProfit().getValue().setScale(0));

        for (RoundResult result : results.results()) {
            System.out.println(result.player().getName().getValue() + ": " + result.betMoney().getValue().setScale(0));
        }
    }

    public void printCardsWithResult(List<Player> players, Dealer dealer) {
        printEmptyLine();
        printCardWithResult(dealer);

        for (Player player : players) {
            printCardWithResult(player);
        }
    }

    private void printCardWithResult(Participant participant) {
        System.out.println(
                participant.getName().getValue() + "카드: " + cardsToString(
                        participant.getCards().getCards()) + " - 결과: "
                        + participant.getTotalSum().value());
    }
}
