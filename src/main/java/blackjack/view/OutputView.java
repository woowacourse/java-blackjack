package blackjack.view;

import java.sql.SQLOutput;
import java.util.List;

public class OutputView {
    public void outputSplitMessage(String dealer, List<String> players){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer+"와 ");
        stringBuilder.append(String.join(",", players)+"에게 두장을 나누었습니다.");
        System.out.println(stringBuilder);
    }
    public void outputPlayerCard(String name, List<String> cards){
        System.out.print(name+" : "+String.join(", ",cards));
    }

    public void outputDealerDrawCard(String name){
        System.out.println(name+"는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void outputScore(int score){
        System.out.println("- 결과 : "+ score);
    }

    public void outputResult(){
        System.out.println("## 최종 승패");
    }

    public void outputDealerResult(String name,int win, int tie,int lose){
        System.out.println(name+" : "+win+"승 "+tie+"무 "+lose+"vo");
    }
    public void outputPlayerResult(String name,String result){
        System.out.println(name+" : "+result);
    }
}