package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int NOTES_COUNT = KEYBOARD.length();
    private static GuitarString[] guitarStrings;

    public static void main(String[] args) {
        // 初始化37个吉他弦，对应不同的频率
        initializeGuitarStrings();

        // 主循环：实时检测按键并播放声音
        while (true) {
            // 检查是否有按键按下
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                pluckCorrespondingString(key);
            }

            // 计算并播放所有弦的合成样本
            double sample = computeSuperposition();
            StdAudio.play(sample);

            // 推进所有弦的状态
            advanceAllStrings();
        }
    }

    /** 初始化37个不同频率的吉他弦 */
    private static void initializeGuitarStrings() {
        guitarStrings = new GuitarString[NOTES_COUNT];

        for (int i = 0; i < NOTES_COUNT; i++) {
            double frequency = computeFrequency(i);
            guitarStrings[i] = new GuitarString(frequency);
        }
    }

    /** 根据索引计算频率：440 * 2^((i-24)/12) */
    private static double computeFrequency(int index) {
        double exponent = (index - 24) / 12.0;
        return 440.0 * Math.pow(2.0, exponent);
    }

    /** 拨动对应按键的吉他弦 */
    private static void pluckCorrespondingString(char key) {
        int index = KEYBOARD.indexOf(key);
        if (index != -1) {
            guitarStrings[index].pluck();
        }
        // 如果按键不在37个音符中，忽略（不崩溃）
    }

    /** 计算所有弦的叠加样本（合成声音） */
    private static double computeSuperposition() {
        double total = 0.0;
        for (GuitarString string : guitarStrings) {
            total += string.sample();
        }
        return total;
    }

    /** 推进所有弦的状态 */
    private static void advanceAllStrings() {
        for (GuitarString string : guitarStrings) {
            string.tic();
        }
    }
}