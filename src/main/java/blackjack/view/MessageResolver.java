package blackjack.view;

import static blackjack.view.CardDescription.NUMBER_NAME;
import static blackjack.view.CardDescription.SHAPE_NAME;

import blackjack.domain.DealerGameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Hand;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import blackjack.domain.rule.Score;
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
                .map(Participant::getName)
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

    public String resolveDealerHandMessage(Participant dealer) {
        Card card = dealer.getHand().getCards().get(0);
        return String.format("%s: %s", dealer.getName(), resolveCardMessage(card));
    }

    public String resolveDealerPopCountMessage(int dealerDrawThreshold, int popCount) {
        String message = String.format("딜러는 %d이하라 %d장의 카드를 더 받았습니다.", dealerDrawThreshold, popCount);
        return String.join("", LINE_SEPARATOR, message, LINE_SEPARATOR);
    }

    public String resolvePlayerScoreMessage(Participant participant, Score score) {
        String handMessage = resolvePlayerHandMessage(participant);
        return String.format("%s - 결과: %d", handMessage, score.getValue());
    }

    public String resolvePlayerGameResult(Participant participant, boolean win) {
        return String.format("%s: %s", participant.getName(), resolveGameResultMessage(win));
    }

    private String resolveGameResultMessage(boolean win) {
        if (win) {
            return "승";
        }
        return "패";
    }

    public String resolveDealerGameResult(DealerGameResult dealerGameResult) {
        String prefix = String.join("", LINE_SEPARATOR, "## 최종 승패");
        String message = String.format("딜러: %d승 %d패", dealerGameResult.getWinCount(), dealerGameResult.getLoseCount());
        return String.join("", prefix, LINE_SEPARATOR, message);
    }
}
