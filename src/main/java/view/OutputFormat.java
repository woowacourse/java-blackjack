package view;

import domain.blackjack.BettingResult;
import domain.card.Card;
import domain.dto.ParticipantDto;
import domain.participant.Name;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class OutputFormat {

    private static final String DELIMITER = ", ";

    public String formatParticipantNames(List<Name> names) {
        List<String> playerNames = new ArrayList<>();
        for (Name name : names) {
            playerNames.add(name.getValue());
        }
        return String.format("딜러와 %s에게 2장을 나누었습니다.", String.join(DELIMITER, playerNames));
    }

    public String formatHands(ParticipantDto participantDto) {
        Name name = participantDto.name();
        List<Card> cards = participantDto.cards();
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(formatCard(card));
        }
        return String.format("%s카드: %s", name.getValue(), String.join(DELIMITER, cardNames));
    }

    public String formatCard(Card card) {
        return RankFormat.from(card.getRank()).getName() + ShapeFormat.from(card.getShape()).getName();
    }

    public String formatParticipantResult(ParticipantDto participantDto) {
        return String.format("%s - 결과: %d", formatHands(participantDto), participantDto.score());
    }

    public String formatDealerResult(BettingResult bettingResult) {
        return String.format("딜러: %d", (int) bettingResult.getDealerPayout());
    }

    public String formatPlayerResult(Player player, double payout) {
        return String.format("%s: %d", player.getName().getValue(), (int) payout);
    }
}
