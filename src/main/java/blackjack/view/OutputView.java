package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultCount;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.dto.DealerMatchDto;
import blackjack.domain.game.dto.PlayerMatchDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String JOIN_DELIMITER = ", ";
    private static final String COLON_SEPARATOR = ": ";
    private static final String INITIAL_CARD_DISTRIBUTION_MESSAGE = NEW_LINE + "딜러와 %s에게 2장의 카드를 나누었습니다." + NEW_LINE;
    private static final String DEALER_INITIAL_CARD_FORMAT = "딜러: %s" + NEW_LINE;
    private static final String PLAYER_CARDS_FORMAT = "%s 카드: %s";
    private static final String PARTICIPANT_CARDS_AND_SCORE_FORMAT = NEW_LINE + "%s 카드: %s - 결과: %d";
    private static final String PLAYER_BUST_MESSAGE = "버스트! 21을 초과하였습니다!";
    private static final String DEALER_EXTRA_CARD_MESSAGE = NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_MESSAGE = NEW_LINE + "## 최종 승패";

    // TODO: DTO 로 변경
    public static void printInitialParticipantsCards(BlackjackGame blackjackGame) {
        StringBuilder builder = new StringBuilder();

        builder.append(getParticipantsCardCountInfo(blackjackGame))
                .append(getDealerCardInfo(blackjackGame.getDealer()));

        List<Player> players = blackjackGame.getParticipants();
        for (Player player : players) {
            builder.append(getParticipantCardsInfo(player))
                    .append(NEW_LINE);
        }

        print(builder.toString());
    }

    public static void printPlayerCardsInfo(Player player) {
        print(getParticipantCardsInfo(player));
    }

    public static void printPlayerBustInfo() {
        print(PLAYER_BUST_MESSAGE);
    }

    public static void printDealerExtraCardInfo() {
        print(DEALER_EXTRA_CARD_MESSAGE);
    }

    // TODO: DTO 로 변경
    public static void printAllCardsAndScore(BlackjackGame blackjackGame) {
        List<Participant> participants = new ArrayList<>(List.of(blackjackGame.getDealer()));
        participants.addAll(blackjackGame.getParticipants());

        StringBuilder builder = new StringBuilder();
        for (Participant participant : participants) {
            builder.append(getParticipantCardsAndScore(participant));
        }

        print(builder.toString());
    }

    public static void printDealerMatchResult(DealerMatchDto dealerMatchDto) {
        System.out.println(FINAL_RESULT_MESSAGE);
        System.out.print(dealerMatchDto.getName() + COLON_SEPARATOR);

        dealerMatchDto.getMatchResult()
                .entrySet()
                .forEach(OutputView::printSingleDealerMatchResult);

        System.out.println();
    }

    private static void printSingleDealerMatchResult(Entry<ResultType, ResultCount> entrySet) {
        int resultCount = entrySet.getValue().toInt();
        String resultTypeName = entrySet.getKey().getDisplayName();

        if (resultCount > 0) {
            System.out.print(resultCount + resultTypeName + " ");
        }
    }

    public static void printPlayerMatchResults(Collection<PlayerMatchDto> playerMatchDtos) {
        playerMatchDtos.forEach(
                OutputView::printSinglePlayerMatchResult
        );
    }

    private static void printSinglePlayerMatchResult(PlayerMatchDto playerMatchDto) {
        String name = playerMatchDto.getName();
        String matchResult = playerMatchDto.getMatchResult().getDisplayName();

        System.out.println(name + COLON_SEPARATOR + matchResult);
    }

    private static String getParticipantsCardCountInfo(BlackjackGame blackjackGame) {
        String playerNames = mapAndJoinString(blackjackGame.getParticipants(), Player::getName);
        return String.format(INITIAL_CARD_DISTRIBUTION_MESSAGE, playerNames);
    }

    private static String getDealerCardInfo(Dealer dealer) {
        Card dealerCard = dealer.getOpenCard();
        return String.format(DEALER_INITIAL_CARD_FORMAT, dealerCard.getName());
    }

    private static String getParticipantCardsInfo(Player player) {
        String playerCards = getCardsInfo(player.getCardBundle().getCards());
        return String.format(PLAYER_CARDS_FORMAT, player.getName(), playerCards);
    }

    private static String getParticipantCardsAndScore(Participant participant) {
        String participantName = participant.getName();
        String cards = getCardsInfo(participant.getCardBundle().getCards());
        int score = participant.getCurrentScore().toInt();

        return String.format(PARTICIPANT_CARDS_AND_SCORE_FORMAT, participantName, cards, score);
    }

    private static String getCardsInfo(Set<Card> cards) {
        return mapAndJoinString(cards, Card::getName);
    }

    private static <T> String mapAndJoinString(Collection<T> collection, Function<T, String> function) {
        return collection.stream()
                .map(function)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
