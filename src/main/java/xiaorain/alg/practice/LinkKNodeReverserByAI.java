package xiaorain.alg.practice;

public class LinkKNodeReverserByAI {

    public static void main(String[] args) {

        LinkKNodeReverserByAI ai = new LinkKNodeReverserByAI();
        ListNode link = ai.initLink();
        link = ai.reverseKGroup(link, 3);
        System.out.println(link);
    }

    public ListNode initLink() {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        a.next.next.next = new ListNode(4);
        a.next.next.next.next = new ListNode(5);
        a.next.next.next.next.next = new ListNode(6);
        a.next.next.next.next.next.next = new ListNode(7);
        //    a.next.next.next.next.next.next.next = new Node(8);
        //     a.next.next.next.next.next.next.next.next = new Node(9);
        return a;
    }

    public static class ListNode {

        ListNode next;

        Integer val;

        public ListNode(Integer val) {
            this.val = val;
        }

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) return head;

        // 创建虚拟头节点，便于处理边界情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            // 寻找第k个节点的位置
            int count = 1;
            ListNode curr = prev.next;
            for (; count < k; count++) {
                curr = curr.next;
                if (curr == null) break;
            }

            // 如果不足k个节点，无法反转，直接跳出循环
            if (count != k) {
                break;
            }

            // 记录当前分组的起始和结束位置
            ListNode start = prev.next;
            ListNode end = curr;

            // 反转链表中的k个节点
            // 使用三指针法：pre, current, nextNode
            ListNode pre = null;
            for (int i = 0; i < k; i++) {
                if (curr == null) break;
                // 暂存下一个节点
                ListNode nextNode = curr.next;
                // 反转当前节点指针
                curr.next = pre;
                // 移动pre和curr到下一个位置
                pre = curr;
                curr = nextNode;
            }

            // 将前一段的末尾prev连接到反转后的头结点pre（原end）
            prev.next = pre;
            // 将反转后链表的尾部（原start）连接到下一段
            start.next = end.next;

            // 更新prev到反转后的尾部，作为下一轮的起点
            prev = start;
        }

        return dummy.next;
    }

}
