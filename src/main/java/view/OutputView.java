package view;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.participant.Dealer;
import domain.participant.Player;
import view.message.NumberMessage;
import view.message.PatternMessage;

import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String BUST_MESSAGE = "카드의 합이 21을 초과했습니다.";
    private static final String BLACKJACK_MESSAGE = "축하드립니다! 블랙잭입니다!";
    private static final String DEALER_DRAW_MESSAGE_FORMAT = "%s는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DRAW_MESSAGE_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_MESSAGE_FORMAT = "%s: %s";
    private static final String PARTICIPANT_CARD_RESULT_FORMAT = "%s 카드: %s - 결과: %d";
    private static final String FINAL_GAME_RESULT = "## 최종 수익";
    private static final String PLAYER_GAME_RESULT_FORMAT = "%s: %s";

    public static void print(final String message) {
        System.out.println(message);
    }

    public void printDealingMessage(final Dealer dealer, final List<Player> players) {
        final String namesMessage = makeParticipantNamesMessage(players);
        final String drawMessage = String.format(System.lineSeparator() +
                DRAW_MESSAGE_FORMAT, dealer.getName(), namesMessage);
        print(drawMessage);
        printCardInfo(dealer, players);
    }

    private void printCardInfo(final Dealer dealer, final List<Player> players) {
        printDealerCard(dealer.getName(), dealer.getFirstCard());
        players.forEach(player -> printParticipantCard(player.getName(), player.getHand()));
    }

    public void printDealerCard(final String name, final Card firstCard) {
        final String dealerCardMessage = String.format(CARD_MESSAGE_FORMAT, name, getCardMessage(firstCard));
        print(dealerCardMessage);
    }

    public void printParticipantCard(final String playerName, final List<Card> playerCards) {
        final String cardsMessage = getCardsMessage(playerCards);
        final String playerCardMessage = String.format(CARD_MESSAGE_FORMAT, playerName, cardsMessage);
        print(playerCardMessage);
    }

    public void printCardResult(final String participantName,
                                final List<Card> participantCards,
                                final int participantScore) {
        final String cardsResultMessage = String.format(PARTICIPANT_CARD_RESULT_FORMAT,
                participantName, getCardsMessage(participantCards), participantScore);
        print(cardsResultMessage);
    }

    public void printFinalGameResult(final Dealer dealer, final List<Player> players) {
        print(System.lineSeparator() + FINAL_GAME_RESULT);
        printPlayerGameResult(dealer.getName(), dealer.getMoney());
        players.forEach(player -> printPlayerGameResult(player.getName(), player.getMoney()));
    }

    public void printBustMessage() {
        System.out.println(BUST_MESSAGE);
    }

    public void printBlackJackMessage() {
        System.out.println(BLACKJACK_MESSAGE);
    }

    public void printDealerDrawMessage(final String dealerName) {
        System.out.println(String.format(DEALER_DRAW_MESSAGE_FORMAT, dealerName) + System.lineSeparator());
    }

    private String makeParticipantNamesMessage(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private String getCardMessage(final Card participantCard) {
        final CardNumber cardNumber = participantCard.getCardNumber();
        final CardPattern cardPattern = participantCard.getCardPattern();
        return NumberMessage.findMessage(cardNumber) + PatternMessage.findMessage(cardPattern);
    }

    private String getCardsMessage(final List<Card> playerCards) {
        return playerCards.stream().map(this::getCardMessage)
                .collect(Collectors.joining(", "));
    }

    private void printPlayerGameResult(final String name, final int money) {
        final String playerGameResultMessage = String.format(PLAYER_GAME_RESULT_FORMAT, name, money);
        print(playerGameResultMessage);
    }
}
