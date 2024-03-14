package blackjack.view;

import static blackjack.view.CardDescription.NUMBER_NAME;
import static blackjack.view.CardDescription.SHAPE_NAME;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hand;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.BetResult;
import blackjack.domain.result.BetResults;
import java.util.stream.Collectors;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEPARATOR = ", ";

    public String resolveHandOutEventMessage(Players players, int handOutCount) {
        String namesMessage = resolveNamesMessage(players);
        String message = String.format("딜러와 %s에게 %d장을 나누었습니다.", namesMessage, handOutCount);
        return String.join("", LINE_SEPARATOR, message);
    }

    private String resolveNamesMessage(Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(SEPARATOR));
    }

    public String resolvePlayerHandMessage(Participant participant) {
        return String.format("%s 카드: %s", participant.getName(), resolveHandMessage(participant.getHand()));
    }

    private String resolveHandMessage(Hand hand) {
        return hand.getCards().stream()
                .map(this::resolveCardMessage)
                .collect(Collectors.joining(SEPARATOR));
    }

    private String resolveCardMessage(Card card) {
        CardNumber cardNumber = card.getCardNumber();
        CardShape cardShape = card.getCardShape();
        return String.format("%s%s", NUMBER_NAME.get(cardNumber), SHAPE_NAME.get(cardShape));
    }

    public String resolveDealerHandMessage(Dealer dealer) {
        Card card = dealer.getHand().getCards().get(0);
        return String.format("%s: %s", dealer.getName(), resolveCardMessage(card));
    }

    public String resolveDealerPopCountMessage(int dealerDrawThreshold, int popCount) {
        if (popCount > 0) {
            String message = String.format("딜러는 %d이하라 %d장의 카드를 더 받았습니다.", dealerDrawThreshold, popCount);
            return String.join("", LINE_SEPARATOR, message, LINE_SEPARATOR);
        }
        return "";
    }

    public String resolveParticipantScoreMessage(Participant participant, Score score) {
        String handMessage = resolvePlayerHandMessage(participant);
        return String.format("%s - 결과: %d", handMessage, score.getValue());
    }

    public String resolveBetResultMessage(BetResult betResult) {
        return String.format("%s: %d", betResult.getName(), betResult.getProfit());
    }

    public String resolveBetResultsMessage(int dealerEarned, BetResults betResults) {
        return new StringBuilder(LINE_SEPARATOR)
                .append("##최종 수익")
                .append(LINE_SEPARATOR)
                .append(resolveDealerProfitMessage(dealerEarned))
                .append(LINE_SEPARATOR)
                .append(betResults.getBetResults().stream()
                        .map(this::resolveBetResultMessage)
                        .collect(Collectors.joining(LINE_SEPARATOR))).toString();
    }

    public String resolveDealerProfitMessage(int dealerEarned) {
        return String.format("딜러: %d", dealerEarned);
    }
}
