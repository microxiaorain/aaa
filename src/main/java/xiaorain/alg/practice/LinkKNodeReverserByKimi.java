package xiaorain.alg.practice;

public class LinkKNodeReverserByKimi {

    /* 单链表节点 */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    /* 主函数：每 K 个一组反转 */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(0); // 方便头插
        dummy.next = head;
        ListNode groupPrev = dummy;       // 上一组的尾巴

        while (true) {
            /* 1. 探路：看剩余是否够 K 个 */
            ListNode kth = groupPrev;
            for (int i = 0; i < k; i++) {
                kth = kth.next;
                if (kth == null) return dummy.next; // 不够 K，结束
            }
            ListNode groupNext = kth.next; // 记下下一组头

            /* 2. 翻转当前组 [groupPrev.next ... kth] */
            ListNode prev = groupNext;     // 翻转后尾要指向下组头
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {    // 头插法
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            /* 3. 把翻转后的子链重新接回主干 */
            ListNode tail = groupPrev.next; // 原头变尾
            groupPrev.next = kth;           // 前驱指向新头
            groupPrev = tail;               // 移动上一组尾巴
        }
    }

    /* ---------- 自测 ---------- */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        int k = 3;

        ListNode res = reverseKGroup(head, k);
        while (res != null) {
            System.out.print(res.val + (res.next == null ? "\n" : " -> "));
            res = res.next;
        }
        // 输出：3 -> 2 -> 1 -> 4 -> 5
    }
}