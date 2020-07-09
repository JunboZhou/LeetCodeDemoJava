package _04_栈;

import java.util.Stack;

/***
 * 基本计算器
 * https://leetcode-cn.com/problems/basic-calculator/
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 思路:
 * 从左往右遍历
 * 如果是数字(考虑多多位数)
 * 例如 678 --> 0 * 10 + 6  = 6 ---> 6 * 10 + 7 = 67  ---> 67 * 10 + 8 = 678
 * 即  operand = operand * 10 + (int) (charStr - '0');
 * 如果遇到 ' ( ' 就 把之前计算结果和正负号 push 进栈 ,  遇到 ')' 取出之前结果, 出栈累加
 *
 */
public class _224_基本计算器 {

    public static void main(String[] args) {
        String tet = "1-(5)";
        System.out.println(calculate(tet));
    }

    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;  // 记录多位数
        int result = 0; //  结果
        int sign = 1;  // 记录正负号
        for (int i = 0; i < s.length(); i++) {
            char charStr = s.charAt(i);
            if (Character.isDigit(charStr)) { // 如果是数字
                operand = operand * 10 + (int) (charStr - '0');
            } else if (charStr == '+') {
                result += sign * operand;
                sign = 1;
                operand = 0;
            } else if (charStr == '-') {
                result += sign * operand;
                sign = -1;
                operand = 0;
            } else if (charStr == '(') {
                // 把之前的结果和正负符号 存入栈
                stack.push(result);
                stack.push(sign);

                result = 0;
                sign = 1;

            } else if (charStr == ')') {
                // 计算括号内的结果
                result += sign * operand;

                // 取出小括号之前的符号
                int tempSign = stack.pop();
                // 取出之前的结果 * 正负号
                result = stack.pop() + result * tempSign;
                //复位
                operand = 0;
            }
        }

        return result + sign * operand;
    }

}
