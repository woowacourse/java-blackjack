package blackjack_statepattern.view;

import blackjack_statepattern.GameResult;
import blackjack_statepattern.card.Card;
import blackjack_statepattern.dto.CardsDto;
import blackjack_statepattern.participant.Participant;
import blackjack_statepattern.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COMMA = ", ";

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printDistributedCards(CardsDto cardsDto) {
        Map<Participant, List<Card>> participantCards = cardsDto.getParticipantCards();
        System.out.println(showParticipantsNames(participantCards.keySet()) + "에게 2장을 나누었습니다.");

        for (Participant participant : participantCards.keySet()) {
            System.out.println(participant.getName() + "카드: " + showCards(participantCards.get(participant)));
        }
    }

    private static String showParticipantsNames(Set<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(COMMA));
    }

    private static String showCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::showCard)
                .collect(Collectors.joining(COMMA));
    }

    private static String showCard(Card card) {
        return card.getDenominationName() + card.getSuitName();
    }

    public static void printPlayerCards(Player player) {
        System.out.println(player.getName() + "카드: " + showCards(player.getCardsValue()));
    }

    public static void printDealerReceiveCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalCards(CardsDto cardsDto) {
        Map<Participant, List<Card>> participantCards = cardsDto.getParticipantCards();

        for (Participant participant : participantCards.keySet()) {
            System.out.println(participant.getName() + "카드: " + showCards(participantCards.get(participant)) + " - 결과: "
                    + participant.getScore());
        }
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 수익");
        Map<Participant, Double> participantProfit = gameResult.getGameResult();
        for (Participant participant : participantProfit.keySet()) {
            System.out.println(participant.getName() + ": " + participantProfit.get(participant).intValue());
        }
    }
}
