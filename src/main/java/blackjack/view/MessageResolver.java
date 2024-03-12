package blackjack.view;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
import blackjack.domain.participant.Players;

import blackjack.domain.rule.Judge;
import blackjack.view.mapper.CardRankMapper;
import blackjack.view.mapper.CardSuitMapper;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEPARATOR = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String PARTICIPANT_HAND_FORMAT = "%s 카드: %s";

    public String resolveDealDescriptionMessage(Players players) {
        String message = String.format("딜러와 %s에게 2장을 나누었습니다.", resolveNamesMessage(players));
        return String.join("", LINE_SEPARATOR, message);
    }

    private String resolveNamesMessage(Players players) {
        return players.getPlayers().stream()
                .map(Player::getPlayerName)
                .map(PlayerName::getValue)
                .collect(Collectors.joining(SEPARATOR));
    }

    public String resolveDealToAllMessage(Participants participants) {
        return participants.getParticipants().stream()
                .map(this::resolveDealToOneMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolveDealToOneMessage(Participant participant) {
        if (participant instanceof Dealer) {
            return resolveDealerFirstCardMessage(participant.getHand());
        }
        if (participant instanceof Player) {
            return resolveParticipantHandMessage(participant);
        }
        throw new IllegalArgumentException();
    }

    private String resolveDealerFirstCardMessage(Hand hand) {
        return String.format(PARTICIPANT_HAND_FORMAT, DEALER_NAME, resolveCardMessage(hand.getCards().get(0)));
    }

    public String resolveParticipantHandMessage(Participant participant) {
        return String.format(PARTICIPANT_HAND_FORMAT, resolveNameMessage(participant),
                resolveHandMessage(participant.getHand()));
    }

    private String resolveNameMessage(Participant participant) {
        if (participant instanceof Dealer) {
            return DEALER_NAME;
        }
        if (participant instanceof Player) {
            return ((Player) participant).getPlayerName().getValue();
        }
        throw new IllegalArgumentException();
    }

    private String resolveHandMessage(Hand hand) {
        return hand.getCards().stream()
                .map(this::resolveCardMessage)
                .collect(Collectors.joining(SEPARATOR));
    }

    private String resolveCardMessage(Card card) {
        String rankSymbol = CardRankMapper.toSymbol(card.getCardRank());
        String suitSymbol = CardSuitMapper.toSymbol(card.getCardSuit());
        return String.format("%s%s", rankSymbol, suitSymbol);
    }

    public String resolveDrawToDealerMessage() {
        return String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", DEALER_NAME);
    }

    public String resolveParticipantsHandScoreMessage(Participants participants) {
        String message = participants.getParticipants().stream()
                .map(this::resolveParticipantHandScoreMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
        return String.join("", LINE_SEPARATOR, message);
    }

    private String resolveParticipantHandScoreMessage(Participant participant) {
        return String.format("%s - 결과: %d", resolveParticipantHandMessage(participant), participant.getHandScore());
    }

    public String resolveResultDescriptionMessage() {
        return String.join("", LINE_SEPARATOR, "## 최종 승패");
    }

    public String resolveDealerResultMessage(Judge judge) {
        return judge.getResults().entrySet().stream()
                .filter(entry -> entry.getKey() instanceof Dealer)
                .findFirst()
                .map(Entry::getValue)
                .map(dealer -> String.format("%s: %d승 %d패", DEALER_NAME, dealer.getWinCount(), dealer.getLoseCount()))
                .orElseThrow(IllegalArgumentException::new);
    }

    public String resolvePlayersResultMessage(Judge judge) {
        return judge.getResults().entrySet().stream()
                .filter(entry -> entry.getKey() instanceof Player)
                .map(entry -> resolvePlayerResult((Player) entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolvePlayerResult(Player player, Result result) {
        if (result.getWinCount() == 1) {
            return String.format("%s: 승", player.getPlayerName().getValue());
        }
        return String.format("%s: 패", player.getPlayerName().getValue());
    }
}
