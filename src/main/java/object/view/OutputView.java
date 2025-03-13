package object.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import object.game.GameResult;
import object.game.Score;
import response.BattleResultResponse;
import response.BlackJackResultResponse;
import response.CardDeckStatusResponse;
import response.ParticipantResponse;

public class OutputView {
    public void printDrawTwoCardsMessage(ParticipantResponse response) {
        playSoundEffect("cardShuffle.wav");
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n",
                response.dealerName(),
                String.join(", ", response.playerNames())
        );
    }

    public void printCardDeckStatus(CardDeckStatusResponse response) {
        playSoundEffect("cardDraw.wav");
        Map<String, List<String>> cardDeckNamesOfParticipant = response.cardDeckNamesOfParticipant();

        for (Map.Entry<String, List<String>> entry : cardDeckNamesOfParticipant.entrySet()) {
            String participantName = entry.getKey();
            List<String> cardNames = entry.getValue();

            System.out.printf("%s 카드: %s%n",
                    participantName,
                    String.join(", ", cardNames)
            );
        }
        System.out.println();
    }

    public void printDrawSingleCardToDealerMessage(String nickname, int stayThreshold) {
        delay(1000);
        playSoundEffect("cardDraw.wav");
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n",
                nickname,
                stayThreshold
        );
        delay(1000);
    }

    public void printBlackJackResult(List<BlackJackResultResponse> resultResponses) {
        System.out.println();
        for (BlackJackResultResponse response : resultResponses) {
            delay(500);
            playSoundEffect(response);
            System.out.printf("%s 카드: %s - 결과: %d%n",
                    response.nickname(),
                    String.join(", ", response.cardNames()),
                    response.totalScore()
            );
            delay(500);
        }
    }

    public void printBattleResult(List<BattleResultResponse> resultResponses) {
        delay(1000);
        System.out.println("\n## 최종 승패");
        for (BattleResultResponse response : resultResponses) {
            String nickname = response.nickname();
            Map<GameResult, Integer> battleResultCount = response.battleResult();

            System.out.printf("%s: %s%n", nickname, parseBattleResult(battleResultCount));
        }
    }

    private String parseBattleResult(Map<GameResult, Integer> battleResultCount) {
        StringBuilder result = new StringBuilder();
        for (GameResult gameResult : GameResult.values()) {
            if (!battleResultCount.containsKey(gameResult)) {
                continue;
            }

            int count = battleResultCount.get(gameResult);
            result.append(count).append(gameResult.getName()).append(" ");
        }

        return result.toString();
    }

    private void playSoundEffect(String fileName) {
        try (InputStream audioSrc = OutputView.class.getResourceAsStream("/sounds/" + fileName)) {
            if (audioSrc == null) {
                throw new IllegalArgumentException("파일을 찾을 수 없습니다: " + fileName);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    private void playSoundEffect(BlackJackResultResponse resultResponse) {
        int score = resultResponse.totalScore();
        if (score > Score.BUST_THRESHOLD) { // 버스트 라면
            playSoundEffect("scoreShowBust.wav");
            return;
        }
        if (score == Score.BUST_THRESHOLD) { // 블랙잭 (퍼펙트 점수) 라면
            playSoundEffect("scoreShowPerfect.wav");
            return;
        }
        playSoundEffect("scoreShowNormal.wav");
    }

    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
