package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INIT_FORMAT = LINE_SEPARATOR
            + "딜러와 %s에게 2장을 나누었습니다." + LINE_SEPARATOR;
    private static final String DEARER_CARDS_FORMAT = "%s: %s" + LINE_SEPARATOR;
    private static final String PLAYER_CARDS_FORMAT = "%s카드: %s" + LINE_SEPARATOR;
    private static final String CARDS_RESULT_FORMAT = "%s카드: %s - 결과: %d" + LINE_SEPARATOR;
    private static final String DEALER_HIT_MESSAGE = LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %d승 %d패" + LINE_SEPARATOR;
    private static final String PLAYER_RESULT_FORMAT = "%s: %s" + LINE_SEPARATOR;

    public void printInitCards(Participants participants) {
        System.out.printf(INIT_FORMAT, String.join(", ", participants.getPlayerNames()));
        printDealerInitCard(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    private void printDealerInitCard(Dealer dealer) {
        System.out.printf(DEARER_CARDS_FORMAT, dealer.getName(),
                convertCard(dealer.getCards()
                                  .get(0)));
    }

    private String convertCard(Card card) {
        return card.getNumber()
                   .getValue() + card.getSuit()
                                     .getValue();
    }

    public void printPlayerCards(Player player) {
        String cardNames = getParticipantCardsView(player);
        System.out.printf(PLAYER_CARDS_FORMAT, player.getName(), cardNames);
    }

    public void printDealerState() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public void printCardResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        String dealerCards = getParticipantCardsView(dealer);
        printCardResultFormat(dealer, dealerCards);

        for (Player player : participants.getPlayers()) {
            String cards = getParticipantCardsView(player);
            printCardResultFormat(player, cards);
        }
    }

    private void printCardResultFormat(Participant participant, String cards) {
        System.out.printf(CARDS_RESULT_FORMAT, participant.getName(), cards, participant.getScore());
    }

    private String getParticipantCardsView(Participant participant) {
        return participant.getCards()
                          .stream()
                          .map(this::convertCard)
                          .collect(Collectors.joining(", "));
    }

    public void printGameResult(Map<Participant, GameResult> result) {
        System.out.println(RESULT_MESSAGE);
        long loseCount = getPlayerWinCount(result);
        System.out.printf(DEALER_RESULT_FORMAT, (result.size() - loseCount), loseCount);
        for (Participant participant : result.keySet()) {
            System.out.printf(PLAYER_RESULT_FORMAT,
                    participant.getName(), result.get(participant)
                                                 .getValue());
        }
    }

    private long getPlayerWinCount(Map<Participant, GameResult> result) {
        return result.values()
                     .stream()
                     .filter(GameResult::isWin)
                     .count();
    }
}
