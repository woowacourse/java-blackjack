package util;

public class BettingMoneyParser {
    public static int parse(String money){
        try{
            return Integer.parseInt(money);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("배팅 금액은 숫자만 입력해주세요.");
        }
    }
}
