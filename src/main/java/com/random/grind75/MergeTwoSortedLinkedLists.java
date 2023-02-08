package com.random.grind75;

/**
 * Copyright (c) Jumia.
 */
public class MergeTwoSortedLinkedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {

            return list2;
        }
        if (list2 == null) {

            return list1;
        }

        ListNode first = new ListNode(0), tracker = first;

        while (list1 != null && list2 != null) {

            if (list1.val > list2.val) {

                tracker.next = new ListNode(list2.val);
                list2 = list2.next;
            } else {

                tracker.next = new ListNode(list1.val);
                list1 = list1.next;
            }
            tracker = tracker.next;
        }

        tracker.next = list1 != null ? list1 : list2;

        return first.next;

    }

}

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}