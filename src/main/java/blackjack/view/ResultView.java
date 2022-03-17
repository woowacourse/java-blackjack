package blackjack.view;

import blackjack.dto.DealerDto;
import blackjack.dto.HandDto;
import blackjack.dto.EntryDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import blackjack.dto.ProfitsDto;
import blackjack.dto.TrumpCardDto;
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

    public void printHandInitialized(PlayersDto players) {
        DealerDto dealer = players.getDealer();
        System.out.printf(FORMAT_MESSAGE_HAND_INITIALIZED, dealer.getName(), joinStrings(players.getEntryNames()));
    }

    public void printInitializedHands(PlayersDto players) {
        for (PlayerDto player : players.getPlayers()) {
            printFirstHand(player);
        }
    }

    private void printFirstHand(PlayerDto player) {
        if (player instanceof DealerDto) {
            printOnlyFirstCard((DealerDto) player);
            return;
        }
        printHand(player);
    }

    private void printOnlyFirstCard(DealerDto dealer) {
        System.out.printf(FORMAT_HAND_INITIALIZED, dealer.getName(), getFirstCardToString(dealer.getHand()));
    }

    private String getFirstCardToString(HandDto hand) {
        TrumpCardDto firstCard = hand.getCards().get(0);
        return firstCard.getNumber() + firstCard.getSymbol();
    }

    public void printHand(PlayerDto player) {
        System.out.printf(FORMAT_HAND_INITIALIZED, player.getName(), joinHand(player.getHand()));
    }

    public void printBustMessage(EntryDto entry) {
        System.out.printf(FORMAT_MESSAGE_BUST, entry.getName());
    }

    public void printDealerHitCount(DealerDto dealer) {
        System.out.printf(FORMAT_MESSAGE_DEALER_HIT, dealer.getName(), dealer.getAddedCount());
    }

    public void printScores(PlayersDto players) {
        for (PlayerDto player : players.getPlayers()) {
            printScore(player);
        }
    }

    private void printScore(PlayerDto player) {
        System.out.printf(FORMAT_SCORE, player.getName(), joinHand(player.getHand()), player.getScore());
    }

    public void printProfits(ProfitsDto profits) {
        System.out.println(TITLE_PROFIT);
        printProfit(profits.getDealer(), profits.getDealerProfit());
        printEntryProfits(profits);
    }

    private void printEntryProfits(ProfitsDto profits) {
        Map<EntryDto, Integer> entryProfits = profits.getEntryProfits();
        for (EntryDto entry : entryProfits.keySet()) {
            printProfit(entry, entryProfits.get(entry));
        }
    }

    private void printProfit(PlayerDto player, int profit) {
        System.out.printf(FORMAT_PROFIT, player.getName(), profit);
    }

    private String joinHand(HandDto hand) {
        return joinCards(hand.getCards());
    }

    private String joinCards(List<TrumpCardDto> cards) {
        return joinStrings(cards.stream()
                .map(card -> card.getNumber() + card.getSymbol())
                .collect(Collectors.toList()));
    }

    private String joinStrings(List<String> strings) {
        return String.join(DELIMITER_JOIN, strings);
    }
}
