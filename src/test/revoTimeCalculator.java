package test;

import static test.status.*;

public class revoTimeCalculator {
    public static void main(String[] args) {
        System.out.println("[Sun] Runes: " + RuneSun + ", Time: " + realTime(calcTime(TimeSun)));
        System.out.println("[Moon] Runes: " + RuneMoon + ", Time: " + realTime(calcTime(TimeMoon)));
        System.out.println("[Mineral] Time: " + realTime(MineralTime(costIncrement, costFall)));
        System.out.println("[Special Mineral] Time: " + realTime(specialMineralTime(specialCostFall)));
    }

    public static float calcTime(String input) {
        String[] time = input.split(":");
        int[] times = new int[time.length];
        int seconds = 0;
        for (int i = times.length - 1; i >= 0; i--) {
            times[i] = Integer.parseInt(time[time.length - 1 - i]);
            if (i < 2) {
                seconds = seconds * 60 + times[i];
            } else {
                seconds = seconds * 24 + times[i];
            }
        }

        return (float) seconds / GameSpeed;
    }

    /**
     * 時間から分、秒、フレームに分けてString型で出力する
     * @param input 秒数(float型)
     * @return String
     */
    public static String realTime(float input) {
        int intInput = (int) Math.floor(input);
        float flames = (float) (Math.ceil((input - intInput) * 60 * 100) / 100);
        int minutes = intInput / 60;
        int seconds = intInput - (60 * minutes);
        return minutes + "m " + seconds + "s " + flames + "F";
    }

    /**
     * 通常鉱石のコスト減少時間を計算。
     * @param costIncrement コスト増加/スポーンごと
     * @param costFall コスト低下
     * @return 減少時間(秒)
     */
    public static float MineralTime(double costIncrement, int costFall) {
        double RealFallSpeed = Math.log10(costFall) * GameSpeed;
        return (float) (Math.log10(costIncrement) / RealFallSpeed);
    }

    public static float specialMineralTime(double specialCostFall) {
        return (float) (5 * 60 * 60 / (specialCostFall * GameSpeed));
    }

}