package blackjack.view;

import blackjack.dto.DealerDTO;
import blackjack.dto.HandDTO;
import blackjack.dto.EntryDTO;
import blackjack.dto.PlayerDTO;
import blackjack.dto.PlayersDTO;
import blackjack.dto.ProfitsDTO;
import blackjack.dto.TrumpCardDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {
    private static final String FORMAT_MESSAGE_HAND_INITIALIZED = "%n%s와 %s에게 2장의 카드를 나누었습니다.%n";
    private static final String FORMAT_HAND_INITIALIZED = "%s : %s%n";
    private static final String FORMAT_MESSAGE_BUST = "%n%s의 점수 합이 21을 넘어, 다음 참가자로 넘어갑니다.%n%n";
    private static final String FORMAT_MESSAGE_DEALER_HIT = "%n%s는 16이하라 %d장의 카드를 더 받았습니다.%n";
    private static final String FORMAT_SCORE = "%s 카드: %s - 결과: %d%n";
    private static final String TITLE_PROFIT = "\n## 최종 수익";
    private static final String FORMAT_PROFIT = "%s : %d%n";

    private static final String DELIMITER_JOIN = ", ";

    public void printHandInitialized(PlayersDTO players) {
        DealerDTO dealer = players.getDealer();
        System.out.printf(FORMAT_MESSAGE_HAND_INITIALIZED, dealer.getName(), joinStrings(players.getEntryNames()));
    }

    public void printInitializedHands(PlayersDTO players) {
        for (PlayerDTO player : players.getPlayers()) {
            printFirstHand(player);
        }
    }

    private void printFirstHand(PlayerDTO player) {
        if (player instanceof DealerDTO) {
            printOnlyFirstCard((DealerDTO) player);
            return;
        }
        printHand(player);
    }

    private void printOnlyFirstCard(DealerDTO dealer) {
        System.out.printf(FORMAT_HAND_INITIALIZED, dealer.getName(), getFirstCardToString(dealer.getHand()));
    }

    private String getFirstCardToString(HandDTO hand) {
        TrumpCardDTO firstCard = hand.getCards().get(0);
        return firstCard.getNumber() + firstCard.getSymbol();
    }

    public void printHand(PlayerDTO player) {
        System.out.printf(FORMAT_HAND_INITIALIZED, player.getName(), joinHand(player.getHand()));
    }

    public void printBustMessage(EntryDTO entry) {
        System.out.printf(FORMAT_MESSAGE_BUST, entry.getName());
    }

    public void printDealerHitCount(DealerDTO dealer) {
        System.out.printf(FORMAT_MESSAGE_DEALER_HIT, dealer.getName(), dealer.getAddedCount());
    }

    public void printScores(PlayersDTO players) {
        for (PlayerDTO player : players.getPlayers()) {
            printScore(player);
        }
    }

    private void printScore(PlayerDTO player) {
        System.out.printf(FORMAT_SCORE, player.getName(), joinHand(player.getHand()), player.getScore());
    }

    public void printProfits(ProfitsDTO profits) {
        System.out.println(TITLE_PROFIT);
        printProfit(profits.getDealer(), profits.getDealerProfit());
        printEntryProfits(profits);
    }

    private void printEntryProfits(ProfitsDTO profits) {
        Map<EntryDTO, Integer> entryProfits = profits.getEntryProfits();
        for (EntryDTO entry : entryProfits.keySet()) {
            printProfit(entry, entryProfits.get(entry));
        }
    }

    private void printProfit(PlayerDTO player, int profit) {
        System.out.printf(FORMAT_PROFIT, player.getName(), profit);
    }

    private String joinHand(HandDTO hand) {
        return joinCards(hand.getCards());
    }

    private String joinCards(List<TrumpCardDTO> cards) {
        return joinStrings(cards.stream()
                .map(card -> card.getNumber() + card.getSymbol())
                .collect(Collectors.toList()));
    }

    private String joinStrings(List<String> strings) {
        return String.join(DELIMITER_JOIN, strings);
    }
}
