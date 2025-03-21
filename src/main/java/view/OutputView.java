package view;

import static domain.BlackJackWinningStatus.BLACK_JACK_LOSE;
import static domain.BlackJackWinningStatus.BLACK_JACK_WIN;
import static domain.BlackJackWinningStatus.DRAW;
import static domain.BlackJackWinningStatus.LOSE;
import static domain.BlackJackWinningStatus.WIN;
import static domain.card.Shape.CLUB;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;

import domain.BlackJackWinningStatus;
import domain.ParticipantsResult;
import domain.betting.Revenue;
import domain.betting.Revenues;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Participant;
import domain.result.DealerResults;
import domain.result.PlayerWinningStatus;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialParticipantHandsMessage(List<String> playerNames) {
        System.out.println();
        String joinedPlayerNames = String.join(", ", playerNames);
        String result = String.format("딜러와 %s에게 2장을 나누었습니다.", joinedPlayerNames);
        System.out.println(result);
    }

    public void printInitialParticipantHand(String playerName, List<Card> cards) {
        String playerNameAndShownCards = formatInitialParticipantHand(playerName, cards);
        System.out.println(playerNameAndShownCards);
    }

    private String formatInitialParticipantHand(String playerName, List<Card> cards) {
        String cardMessage = cards.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", playerName, cardMessage);
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(", "));
    }

    private String formatCard(Card card) {
        return String.format("%s%s", formatCardRank(card.rank()),
                formatCardShape(card.shape()));
    }

    private String formatCardRank(Rank rank) {
        if (rank == Rank.ACE) {
            return "A";
        }
        if (rank == Rank.KING) {
            return "K";
        }
        if (rank == Rank.QUEEN) {
            return "Q";
        }
        if (rank == Rank.JACK) {
            return "J";
        }
        return rank.getValue() + "";
    }

    private String formatCardShape(Shape shape) {
        if (shape == DIAMOND) {
            return "다이아몬드";
        }
        if (shape == HEART) {
            return "하트";
        }
        if (shape == CLUB) {
            return "클로버";
        }
        if (shape == SPADE) {
            return "스페이드";
        }
        return "";
    }

    public void printDealerReceivingCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printParticipantHand(String playerName, List<Card> cards) {
        String playerNameAndCards = formatParticipantHand(playerName, cards);
        System.out.println(playerNameAndCards);
    }

    private String formatParticipantHand(String playerName, List<Card> cards) {
        String cardMessage = formatCards(cards);
        return String.format("%s카드: %s", playerName, cardMessage);
    }

    public void printParticipantsFullInfo(List<Participant> participants) {
        for (Participant participant : participants) {
            printParticipantFullInfo(participant);
        }
        System.out.println();
    }

    public void printParticipantFullInfo(Participant participant) {
        String playerNameAndCardsAndTotalValue = formatParticipantFullInfo(participant);
        System.out.println(playerNameAndCardsAndTotalValue);
    }

    private String formatParticipantFullInfo(Participant participant) {
        String name = participant.getName();
        String cardsMessage = formatCards(participant.getCards());
        int totalValue = participant.getTotalValue();
        return String.format("%s카드: %s - 결과: %d", name, cardsMessage, totalValue);
    }

    public void printGameResult(ParticipantsResult participantsResult) {
        System.out.println("## 최종 승패");
        String dealerResultMessage = formatDealerResultMessage(participantsResult.dealerResults());
        System.out.println(dealerResultMessage);

        for (PlayerWinningStatus playerWinningStatus : participantsResult.playerResults().getPlayerResult()) {
            String playerGameResult = formatPlayerGameResult(playerWinningStatus);
            System.out.println(playerGameResult);
        }
    }

    private String formatDealerResultMessage(DealerResults dealerResults) {
        return String.format("딜러: %s%s%s",
                formatDealerWinMessage(
                        dealerResults.getStatusCount(WIN) + dealerResults.getStatusCount(BLACK_JACK_WIN)),
                formatDealerLoseMessage(
                        dealerResults.getStatusCount(LOSE) + dealerResults.getStatusCount(BLACK_JACK_LOSE)),
                formatDealerDrawMessage(dealerResults.getStatusCount(DRAW)));
    }

    private String formatDealerWinMessage(Integer integer) {
        if (integer == null) {
            return "";
        }
        return String.format("%d승 ", integer);
    }

    private String formatDealerLoseMessage(Integer integer) {
        if (integer == null) {
            return "";
        }
        return String.format("%d패 ", integer);
    }

    private String formatDealerDrawMessage(Integer integer) {
        if (integer == null) {
            return "";
        }
        return String.format("%d무 ", integer);
    }

    private String formatPlayerGameResult(PlayerWinningStatus playerWinningStatus) {
        return String.format("%s: %s", playerWinningStatus.playerName(),
                formatGameResult(playerWinningStatus.status()));
    }

    private String formatGameResult(BlackJackWinningStatus blackJackWinningStatus) {
        if (blackJackWinningStatus == WIN) {
            return "승";
        }
        if (blackJackWinningStatus == DRAW) {
            return "무";
        }
        if (blackJackWinningStatus == LOSE) {
            return "패";
        }
        return "";
    }

    public void printRevenue(Revenues revenues) {
        System.out.println("## 최종 수익");
        String dealerRevenue = formatDealerRevenue(revenues.calculateDealerRevenue());
        System.out.println(dealerRevenue);
        for (Revenue revenue : revenues.getRevenues()) {
            String playerRevenue = formatPlayerRevenue(revenue);
            System.out.println(playerRevenue);
        }
    }

    private String formatDealerRevenue(Revenue dealerRevenue) {
        return String.format("딜러: %d", dealerRevenue.money());
    }

    private String formatPlayerRevenue(Revenue playerRevenue) {
        return String.format("%s: %d", playerRevenue.playerName(), playerRevenue.money());
    }

    public void printBlankLine() {
        System.out.println();
    }
}
