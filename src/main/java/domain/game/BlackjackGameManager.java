package domain.game;

import domain.card.Card;
import domain.card.CardMachine;
import domain.participant.BetAmount;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.PlayerName;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import dto.PlayerStatisticDto;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGameManager {

    private final CardMachine cardMachine;
    private final BlackjackJudge blackjackJudge;
    private Participants participants;

    public BlackjackGameManager() {
        this.cardMachine = new CardMachine();
        this.blackjackJudge = new BlackjackJudge();
    }

    public void createParticipants(List<PlayerName> playerNames, List<BetAmount> betAmounts) {
        participants = Participants.of(playerNames, betAmounts);
    }

    public void drawInitialCards() {
        participants.drawInitialCards(this::drawCard);
    }

    public boolean drawDealerCard() {
        if (participants.dealerShouldHit()) {
            participants.drawCardsByDealer(this::drawCard);
            return true;
        }

        return false;
    }

    public Card drawCard() {
        return cardMachine.drawCard();
    }

    public ParticipantDto updatePlayer(String name) {
        Player player = participants.drawCardsByPlayer(name, this::drawCard);
        return ParticipantDto.from(player);
    }

    public ParticipantDto generateInitialDealerDto() {
        return ParticipantDto.from(participants.dealer(), true);
    }

    public ParticipantDto generateDealerDto() {
        return ParticipantDto.from(participants.dealer());
    }

    public List<ParticipantDto> generatePlayerDtoList() {
        return participants.players().getPlayers().stream()
                .map(ParticipantDto::from)
                .toList();
    }

    public BlackjackResultDto getBlackjackResult() {
        ParticipantDto dealerResultDto = generateDealerDto();
        List<ParticipantDto> playerResultDtoList = generatePlayerDtoList();

        return BlackjackResultDto.of(dealerResultDto, playerResultDtoList);
    }

    public BlackjackStatisticsDto getBlackjackStatistics() {
        List<PlayerStatisticDto> playerStatisticDtoList = new ArrayList<>();
        int dealerProfit = 0;
        for (Player player : participants.players().getPlayers()) {
            Result result = blackjackJudge.judgePlayerResult(participants.dealer(), player);
            int playerProfit = blackjackJudge.calculatePlayerProfit(player, result);
            dealerProfit += playerProfit * -1;
            playerStatisticDtoList.add(PlayerStatisticDto.of(player, playerProfit));
        }

        return BlackjackStatisticsDto.of(dealerProfit, playerStatisticDtoList);
    }

    public boolean isHit(HitOrStand hitOrStand) {
        return hitOrStand.isHit();
    }

    public boolean isStand(HitOrStand hitOrStand) {
        return hitOrStand.isStand();
    }
}
