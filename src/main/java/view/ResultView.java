package view;


import domain.BetMoney;
import domain.BlackjackGame;
import domain.Score;
import domain.card.Card;
import domain.dto.PlayerResult;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final String DELIMITER = ", ";

    public void printParticipantsCards(List<Player> players, Dealer dealer) {
        printEmptyLine();
        System.out.println(
                "딜러와 " + joinPlayersNameByDelimiter(players) + "에게 "
                        + BlackjackGame.INITIAL_CARD_COUNT
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
            System.out.println("딜러는 " + Score.DEALER_HIT_STAND_BOUNDARY.value() + "이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("딜러는 " + (Score.DEALER_HIT_STAND_BOUNDARY.add(Score.ONE).value()) + "이상이라 카드를 받지 않았습니다.");
    }


    public void printProfits(List<PlayerResult> playerResults, BetMoney dealerProfit) {
        printEmptyLine();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerProfit.getValue().setScale(0));

        for (PlayerResult result : playerResults) {
            System.out.println(result.player().getName().getValue() + ": " + result.betMoney().getValue().setScale(0));
        }
    }

    public void printCardsWithResult(List<Player> players, Dealer dealer) {
        printEmptyLine();
        System.out.println(
                "딜러카드: " + cardsToString(
                        dealer.getCards().getCards()) + " - 결과: "
                        + dealer.getTotalSum().value());

        for (Player player : players) {
            printCardWithResult(player);
        }
    }

    private void printCardWithResult(Player player) {
        System.out.println(
                "카드: " + cardsToString(
                        player.getCards().getCards()) + " - 결과: "
                        + player.getTotalSum().value());
    }
}
