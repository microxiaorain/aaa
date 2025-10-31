package xiaorain.alg.practice;

public class LinkKNodeReverser {

    public static void main(String[] args) {
        LinkKNodeReverser reserver = new LinkKNodeReverser();
        Node head = reserver.initLink();
        Integer k = 3;
        reserver.printLink(head);
        head = reserver.reserveKNode(head, k);
        reserver.printLink(head);
    }

    public Node initLink() {
        Node a = new Node(1);
        a.next = new Node(2);
        a.next.next = new Node(3);
        a.next.next.next = new Node(4);
        a.next.next.next.next = new Node(5);
        a.next.next.next.next.next = new Node(6);
        a.next.next.next.next.next.next = new Node(7);
    //    a.next.next.next.next.next.next.next = new Node(8);
   //     a.next.next.next.next.next.next.next.next = new Node(9);
        return a;
    }

    public void printLink(Node node) {
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }

        System.out.print("end \n");
    }

    public Node reserveKNode(Node head, Integer k) {
        Node prev = null;
        LinkSection section = findNextSection(prev, head, k);
        if (section == null) {
            return head;
        }
        head = section.end;
        while (section != null) {
            // reserve
            reserveSectionLink(section);
            // post handle
            if (section.sectionPrev != null) {
                section.sectionPrev.next = section.begin;
            }
            section.end.next = section.sectionNext;
            // find next
            section = findNextSection(section.end, section.sectionNext, k);
        }
        return head;
    }

    public LinkSection findNextSection(Node prev, Node cur, Integer k) {
        if (cur == null) {
            return null;
        }
        LinkSection section = new LinkSection();
        section.sectionPrev = prev;
        section.begin = cur;
        section.end = cur;
        while ( --k > 0 && cur.next != null) {
            cur = cur.next;
        }
        // this part is important.
        // if remove， then means the left part need to reverse.
        // if exist, it means no need to reverse.
        if (k != 0) {
            return null;
        }
        if (cur != null) {
            section.end = cur;
            section.sectionNext = cur.next;
        }
        return section;
    }

    public LinkSection reserveSectionLink(LinkSection section) {
        Node pre = null;
        Node cur = section.begin;
        Node endNext = section.end.next;

        while (cur != null && cur != endNext) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        Node tmp = section.end;
        section.end = section.begin;
        section.begin = tmp;

        return section;
    }


    public static class LinkSection {

        Node sectionPrev;

        Node begin;

        Node end;

        Node sectionNext;

    }

    public static class Node {

        Node(Integer val) {
            this.val = val;
        }

        Integer val;

        Node next;

    }

}
