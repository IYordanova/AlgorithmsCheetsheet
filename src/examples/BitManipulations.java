package examples;

public class BitManipulations {

    static boolean isEvenNum(int num) {
        return (num & 1) == 0;
    }

    static boolean isNthBitSet(int num, int n) {
        return (num & (1 << n)) != 0;
    }

    static int setNthBit(int num, int n) {
        if ((num & (1 << n)) != 0) {
            return num; // already set
        }
        return num | (1 << n);
    }

    static int unsetNthBit(int num, int n) {
        if ((num & (1 << n)) != 0) {
            return num & ~(1 << n);
        }
        return num; // already unset
    }

    static int toggleNthBit(int num, int n) {
        return num ^ (1 << n);
    }

    static int turnOffRightMostBit(int num) {
        return num & (num - 1);
    }

    static int isolateRightMostBit(int num) {
        return num & (-num);
    }

    static int isolateZerothBit(int num) {
        return ~num & (num + 1);
    }

    public static void main(String[] args) {
        System.out.println(isEvenNum(1));
        System.out.println(isEvenNum(2));
        System.out.println(isEvenNum(206));
        System.out.println(isEvenNum(3457));

        System.out.println(isNthBitSet(3, 0));
        System.out.println(isNthBitSet(3, 1));
        System.out.println(isNthBitSet(3, 2));

        System.out.println(setNthBit(2, 0)); // 3
        System.out.println(unsetNthBit(3, 0)); // 2

        System.out.println(isolateRightMostBit(3));
    }
}
