/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
package _02_排序;

public class _7_整数反转 {

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return (int) res == res ? (int) res : 0;
    }

    /***
     *  思路
     *  1234
     *  第一步  0 * 10 + 4 = 4
     *  第二步  4 * 10 + 3 = 43
     *  第三步  43 * 10 + 2 = 432
     *  第四步  432 * 10 + 1 = 4321
     */
    public static int reverse1(int x) {
        int result = 0;
        while (x != 0) {
            int temp = x % 10;
            int newResult = result * 10 + temp;
            // 判断是否越界,如果越界前后两个值不会相等
            if ( ((newResult - temp) / 10 ) != result ) return 0;
            result = newResult;
            x = x /10;
        }
        return result;
    }
}
