package view;

import domain.card.Card;
import domain.player.Participant;

import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String DELIMITER = ", ";

    public void printSetupGame(final List<String> names) {
        final String participants = String.join(DELIMITER, names);
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", participants);
    }

    public void printDrawCards(final Card card, final List<Participant> participants) {
        System.out.printf("딜러: %s%n", convertCardFormat(card));
        participants.forEach(participant -> System.out.printf("%s 카드: %s%n", participant.getName(), convertCardsFormat(participant.getCards())));
    }

    public void printPlayerCard(final Participant participant) {
        System.out.printf("%s 카드: %s%n", participant.getName(), convertCardsFormat(participant.getCards()));
    }

    public void printHitOrStay(final boolean isHit) {
        System.out.println(hitOrStay(isHit));
    }


    public void printDealerResult(final int dealerProfit) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d%n", dealerProfit);
    }

    public void printParticipantResult(final String name, final int participantProfit) {
        System.out.printf("%s: %d%n", name, participantProfit);
    }

    private String hitOrStay(final boolean isHit) {
        if (isHit) {
            return "딜러의 총점은 16 이하라 한장의 카드를 더 받았습니다.";
        }
        return "딜러의 총점은 17 이상입니다. 게임을 종료합니다." + System.lineSeparator();
    }

    private String convertCardsFormat(final List<Card> cards) {
        return cards.stream().map(this::convertCardFormat).collect(Collectors.joining(DELIMITER));
    }

    private String convertCardFormat(Card card) {
        return String.format("%s%s", card.getRank().getSymbol(), card.getSuit());
    }
}
