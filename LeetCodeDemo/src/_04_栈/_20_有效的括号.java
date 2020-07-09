package _04_栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class _20_有效的括号 {

    /***
     * 思路:
     * 遇见左字符,就入栈, 遇到右字符
     * 如果栈为空, 即没有相应的左字符与之对应, 就说明括号无效
     * 如果栈不为空, 将栈顶字符出栈与之匹配
     * 如果匹配成功, 继续扫描下一个字符
     * 如果匹配不成功, 说明括号无效
     * 直到所有字符扫描完毕
     * 栈不为空, 括号有效
     * 栈为空, 括号无效
     */

    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        char[] sArray = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        for (char character : sArray) {
            // 左字符
            if (map.containsKey(character)) {
                stack.push(character);
            } else {  // 右字符
                if (stack.isEmpty()) return false;
                if ( character != map.get(stack.pop())) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
