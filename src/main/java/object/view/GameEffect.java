package object.view;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import object.game.Score;
import response.BlackJackResultResponse;

public class GameEffect {
    public static void playSoundEffect(String fileName) {
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

    public static void playSoundEffect(BlackJackResultResponse resultResponse) {
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

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
