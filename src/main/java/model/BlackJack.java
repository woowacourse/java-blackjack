package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.participant.Participant;
import util.Randoms;

public final class BlackJack {
    private static final int STARTING_CARDS = 2;

    private final Participants participants;
    private final List<Card> pickedCards = new ArrayList<>();
    private final Map<Participant, Integer> betAmount;
    private boolean firstTurn = Boolean.TRUE;

    private BlackJack(Participants participants, Map<Participant, Integer> betAmount) {
        this.participants = participants;
        this.betAmount = betAmount;
    }

    public static BlackJack from(Participants participants, Map<Participant, Integer> betAmount) {
        return new BlackJack(participants, betAmount);
    }

    public Map<String, Integer> calculateDealerResult() {
        Map<String, Integer> resultMap = new HashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {
            if (dealer.calculateScore() > player.calculateScore() || player.isBust()) {
                resultMap.merge("승", 1, Integer::sum);
                continue;
            }

            resultMap.merge("패", 1, Integer::sum);
        }

        return resultMap;
    }

    public Map<String, Boolean> calculatePlayerResult() {
        Map<String, Boolean> resultMap = new LinkedHashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {
            if (dealer.calculateScore() > player.calculateScore() || player.isBust()) {
                resultMap.put(player.getName(), Boolean.FALSE);
                continue;
            }

            resultMap.put(player.getName(), Boolean.TRUE);
        }

        return resultMap;
    }

    public void dealOut() {
        for (Participant participant : participants) {
            for (int i = 0; i < STARTING_CARDS; i++) {
                Card pick = Randoms.pick();
                while (pickedCards.contains(pick)) {
                    pick = Randoms.pick();
                }
                pickedCards.add(pick);
                participant.draw(pick);
            }
        }
    }

    public void setFirstTurn() {
        this.firstTurn = Boolean.FALSE;
    }

    public Map<String, Integer> calculateRevenue() {
//        Map<String, Boolean> calculatedResult = new LinkedHashMap<>();
        // calculatePlayerResult에서 Map<String, Boolean>으로 플레이어 결과를 담아준다.
        Map<String, Boolean> calculatedResult = calculatePlayerResult();

        // 플레이어 기준
        // 처음 두 장의 카드(dealOut 시) 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5배를 딜러에게 받는다.
        // 승리 시 최종 수익 = +배팅한 금액
        // 패배 시 최종 수익 = -배팅한 금액
        for (Entry<String, Boolean> entry : calculatedResult.entrySet()) {
            System.out.println(entry.getKey() + " " +  entry.getValue());
        } // 수익을 계산하고 이름과 수익을 모두 가져와야하는데 현재는 이름과 승패만 가져오고 있음.
        // 이름과 수익을 가져오려면 현재 BlackJack 객체에서 getter를 통해 참가자 정보를 가져와야하나?
        // 참가자들 Participants를 가져오면 참가자들 마다의 승패가 결정되니, 각 참가자별로 Participants 객체를
        // 순회하면서 확인되는 승/패를 통해 최종 수익을 계산할 수 있다. 단, Participant는 배팅한 금액을 알고 있게 해야한다.
        // 따라서 현재 Participant가 갖고 있는 Money라는 현재 보유금액은 지금으로서는 보유금액이 아닌 배팅한 금액을 갖고
        // 있도록 수정하는 것이 적절한 설계라는 생각이 들었다.


        // * 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 배팅한 금액을 돌려받는다.


        // 결과를 리턴. 리턴 받는 곳에서 순회하면 최종 수익자의 이름과 수익이 계산된다.
        return null;
    }
}
