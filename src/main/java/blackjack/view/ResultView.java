package blackjack.view;

import blackjack.domain.card.Hand;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Participant;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class ResultView {

    private static final Map<Suit, String> SUIT_KOREAN = Map.of(
            Suit.SPADE, "스페이드",
            Suit.DIAMOND, "다이아몬드",
            Suit.HEART, "하트",
            Suit.CLOB, "클로버"
    );
    private static final String LINE = System.lineSeparator();
    private static final String NAME_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String COMMA = ", ";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String TITLE_DEALER_EXTRA_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_SCORE_FORMAT = "%s카드: %s - 결과: %d";

    private static final String TITLE_PROFITS = "## 최종 수익";
    private static final String PROFITS_FORMAT = "%s: %d";

    public void dealInitialCards(final List<String> playerNames, final Entry<String, Hand> dealerCards,
                                 final Map<String, Hand> playersCards) {
        showNames(playerNames);
        showDealerCards(dealerCards);
        showPlayersCards(playersCards);
    }

    public void showParticipantTotalCards(final String nickname, final Hand hand) {
        showLine(makeCardMessage(nickname, hand));
    }

    public void showDealerExtraCard() {
        showLine(LINE + TITLE_DEALER_EXTRA_CARD);
    }

    private void showNames(final List<String> names) {
        final String joinedNames = String.join(COMMA, names);
        showFormat(LINE + NAME_FORMAT + LINE, joinedNames);
    }

    public void showDealerCards(final Entry<String, Hand> dealerCards) {
        final String dealerMessage = makeCardMessage(dealerCards.getKey(), dealerCards.getValue());
        showLine(dealerMessage);
    }

    public void showPlayersCards(final Map<String, Hand> playersCards) {
        playersCards.entrySet().stream()
                .map(entry -> makeCardMessage(entry.getKey(), entry.getValue()))
                .forEach(System.out::println);
        showBlank();
    }

    private String makeCardMessage(final String nickname, final Hand hand) {
        return String.format(CARD_FORMAT, nickname, makeCardMessage(hand));
    }

    private String makeCardMessage(final Hand hand) {
        return hand.getHand().stream()
                .map(card -> card.getDenominationName() + getSuitName(card.getSuit()))
                .collect(Collectors.joining(COMMA));
    }

    public void showProfit(final Map<Participant, Integer> profits) {
        showLine(LINE + TITLE_PROFITS);
        profits.entrySet().stream()
                .map(entry -> String.format(PROFITS_FORMAT, entry.getKey().getNickname(), entry.getValue()))
                .forEach(this::showLine);
    }

    public void showParticipantsScore(final Entry<String, Hand> dealer,
                                      final Map<String, Hand> participants) {
        showLine(LINE + showParticipantsScore(dealer));
        participants.entrySet().stream()
                .map(this::showParticipantsScore)
                .forEach(this::showLine);
    }

    private String showParticipantsScore(final Entry<String, Hand> entry) {
        return String.format(CARD_SCORE_FORMAT, entry.getKey(),
                makeCardMessage(entry.getValue()), entry.getValue().calculateResult());
    }

    private String getSuitName(final Suit suit) {
        return SUIT_KOREAN.get(suit);
    }

    private void showLine(final String line) {
        System.out.println(line);
    }

    private void showFormat(final String formattedTest, final Object... args) {
        System.out.printf(formattedTest, args);
    }

    private void showBlank() {
        System.out.println();
    }
}
