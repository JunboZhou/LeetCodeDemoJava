package _01_动态数组;

public class _915_分割数组 {
    public static void main(String[] args) {
        int[] array = {1,1};
        System.out.println(partitionDisjoint(array));
    }

    public static int partitionDisjoint(int[] A) {
        int N = A.length;
        int[] maxleft = new int[N];
        int[] minRight = new int[N];

        int firstLeft = A[0];
        for (int i = 0; i < N; i++) {
            firstLeft = Math.max(firstLeft, A[i]);
            maxleft[i] = firstLeft;
        }

        int firstRight = A[N-1];
        for (int i = N-1; i > 0; i--) {
            firstRight = Math.min(firstRight, A[i]);
            minRight[i] = firstRight;
        }

        for (int i = 0; i < N -1; i++) {
            if (maxleft[i] <= minRight[i + 1]) {
                return i + 1;
            }
        }
       return 0;
    }

}
