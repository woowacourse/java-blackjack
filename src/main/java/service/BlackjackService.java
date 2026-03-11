package service;

import constant.HitOrStand;
import constant.PolicyConstant;
import constant.Result;
import domain.Card;
import domain.CardMachine;
import domain.Dealer;
import domain.Participants;
import domain.Player;
import domain.Players;
import dto.BlackjackStatisticsDto;
import dto.DealerResultDto;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.ArrayList;
import java.util.List;

public class BlackjackService {

    public static final String INVALID_YES_NO_INPUT =
            String.format("%s 또는 %s을 입력해야 합니다.", HitOrStand.HIT.getHitOrStand(), HitOrStand.STAND.getHitOrStand());

    private final CardMachine cardMachine;
    private Participants participants;

    public BlackjackService() {
        this.cardMachine = new CardMachine();
    }

    public void createPlayers(List<String> names) {
        Dealer dealer = new Dealer();
        Players players = new Players(names);
        participants = new Participants(dealer, players);
    }

    public void dealInitialCards() {
        participants.dealer().addCard(drawCard());
        participants.dealer().addCard(drawCard());
        for (Player player : participants.players().getPlayers()) {
            player.addCard(drawCard());
            player.addCard(drawCard());
        }
    }

    public List<ParticipantDto> generateInitialParticipantDtoList() {
        List<ParticipantDto> participantDtoList = new ArrayList<>();

        ParticipantDto dealerDto = generateInitialDealerDto();
        participantDtoList.add(dealerDto);

        List<ParticipantDto> playersDto = generatePlayersDto();
        participantDtoList.addAll(playersDto);

        return participantDtoList;
    }

    public void validateHitOrStand(String hitOrStand) {
        if (!hitOrStand.strip().equals(HitOrStand.HIT.getHitOrStand()) && !hitOrStand.strip()
                .equals(HitOrStand.STAND.getHitOrStand())) {
            throw new IllegalArgumentException(PolicyConstant.ERROR_PREFIX + INVALID_YES_NO_INPUT);
        }
    }

    public boolean drawDealerCard() {
        Dealer dealer = participants.dealer();
        if (dealer.calculateScore() <= PolicyConstant.DEALER_HIT_MAX_SCORE) {
            dealer.addCard(drawCard());
            return true;
        }

        return false;
    }

    public Card drawCard() {
        return cardMachine.drawCard();
    }

    public List<ParticipantDto> getBlackjackResult() {
        List<ParticipantDto> participantDtoList = new ArrayList<>();

        ParticipantDto dealerDto = generateDealerDto();
        participantDtoList.add(dealerDto);

        List<ParticipantDto> playersDto = generatePlayersDto();
        participantDtoList.addAll(playersDto);

        return participantDtoList;
    }

    public ParticipantDto generateInitialDealerDto() {
        Dealer dealer = participants.dealer();
        List<String> hand = convertHandString(dealer.getOnlyFirstHand());
        return new ParticipantDto(dealer.getName(), hand, dealer.calculateScore());
    }

    public ParticipantDto generateDealerDto() {
        Dealer dealer = participants.dealer();
        List<String> hand = convertHandString(dealer.getHand());
        return new ParticipantDto(dealer.getName(), hand, dealer.calculateScore());
    }

    public List<ParticipantDto> generatePlayersDto() {
        List<ParticipantDto> playersDto = new ArrayList<>();
        for (Player player : participants.players().getPlayers()) {
            List<String> hand = convertHandString(player.getHand());
            playersDto.add(new ParticipantDto(player.getName(), hand, player.calculateScore()));
        }
        return playersDto;
    }

    private List<String> convertHandString(List<Card> cards) {
        return cards.stream()
                .map(card -> card.rank().getRank() + card.suit().getSuit())
                .toList();
    }

    public BlackjackStatisticsDto getBlackjackStatistics() {
        List<PlayerResultDto> playerResultDtoList = calculatePlayerResults();
        int win = 0, draw = 0, lose = 0;
        for (PlayerResultDto playerResultDto : playerResultDtoList) {
            Result result = playerResultDto.result();
            win += judgeResult(result, Result.LOSE);
            draw += judgeResult(result, Result.DRAW);
            lose += judgeResult(result, Result.WIN);
        }
        return new BlackjackStatisticsDto(new DealerResultDto(win, draw, lose), playerResultDtoList);
    }

    public List<PlayerResultDto> calculatePlayerResults() {
        Dealer dealer = participants.dealer();
        List<PlayerResultDto> playerResultDtoList = new ArrayList<>();
        for (Player player : participants.players().getPlayers()) {
            Result result = calculatePlayerResult(dealer, player);
            playerResultDtoList.add(new PlayerResultDto(player.getName(), result));
        }
        return playerResultDtoList;
    }

    private Result calculatePlayerResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        if (player.calculateScore() > dealer.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private int judgeResult(Result result, Result playerResult) {
        if (result.equals(playerResult)) {
            return 1;
        }
        return 0;
    }

    public ParticipantDto updatePlayer(String name) {
        Player player = participants.getPlayer(name);
        player.addCard(drawCard());
        List<String> hand = convertHandString(player.getHand());
        return new ParticipantDto(name, hand, player.calculateScore());
    }

    public boolean isHit(String hitOrStand) {
        return hitOrStand.strip().equals(HitOrStand.HIT.getHitOrStand());
    }

    public boolean isStand(String hitOrStand) {
        return hitOrStand.strip().equals(HitOrStand.STAND.getHitOrStand());
    }
}
