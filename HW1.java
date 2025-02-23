
/*
 * *** Michael Simmons / COMP272/400C-002 ***
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer values.
     *
     * Methods:
     *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
     *                                   sorted fashion
     *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
     *                                   than 'val'
     *  - void   removeElement(val)    - Removes all values in the linked list of
     *                                   value 'val'
     *  - String toString()            - coverts and returns the linked-lists as a string
     *                                   delimited by brackets []
     *
     */

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d)  {        // Constructor
                data = d;
                next = null;
            }
        }
        Node head;                // head of Linked-list


        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in teh parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         *
         */
        public void sortedInsert ( int data ) {
            Node new_node = new Node(data);

            new_node.next = null;

            // Special case for head node.
            if (this.head == null || head.data >= new_node.data ) {
                new_node.next = head;
                head = new_node;
            } else {
                // locate the node before the point of insertion
                Node current = this.head;

                // Identify where to place the item to insert
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }
        }


        /*
         * Method removeElementsLT() - this method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         *
         * The method will invoke the method removeElements for each element
         * found in the linked-list that is less than thr parameter value passed.
         */
        public void removeElementsLT(int ltValue) {

            // Starting at the head, if the linked-list is not empty and head value is less than ltValue:
            // The current head value should be removed in the result linked-list
            // So, we will move the head to the next node, continuing until the list is empty or a valid head is found
            while (this.head != null && this.head.data < ltValue) {
                this.head = this.head.next;
            }

            // If there aren't any values less than ltValue, the list will just be empty, so there's nothing to iterate. So, return here
            if (this.head == null) {
                return;
            }

            // With a valid head, iterate through the linked-list to remove any nodes with a value less than ltValue
            Node cursor = this.head;
            while (cursor.next != null) {
                // If the next node's value is less than ltValue, remove the next node
                if (cursor.next.data < ltValue) {
                    cursor.next = cursor.next.next;
                } else { // Otherwise, continue iterating
                    cursor = cursor.next;
                }
            }
        }


        /*
         * Method removeElement() - this method removes all nodes that contain a
         * value equal to the value the provided parameter 'value'.
         */

        public void removeElement ( int value ) {

            // Same logic thing as removeElementsLT:
            // If the linked-list is not empty and the head matches the value, then the head should be removed
            // So, move the head to the next node until the list is empty or a valid head is found
            while (this.head != null && this.head.data == value) {
                this.head = this.head.next;
            }

            // If the list is empty, there won't be any values to remove, so there's nothing to iterate. So, return here
            if (this.head == null) {
                return;
            }

            // If there's a valid head, iterate through the linked-list and remove elements that match the value
            Node cursor = this.head;
            while (cursor.next != null) {
                if (cursor.next.data == value) {
                    cursor.next = cursor.next.next; // Remove node if value matches
                } else {
                    cursor = cursor.next; // Continue iterating
                }
            }
        }


        /*
         * Method toString() - this is a helper method for printing / constructing
         * a string object from the linked-list.
         */
        public String toString () // Method to output the LinkedList as a String
        {
            String output = "[";
            Node currNode = this.head;
            while (currNode != null) {
                output += currNode.data + " ";
                currNode = currNode.next;
            }
            return output.trim() + "]";
        }

    } // End class LinkedList




    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * The intent of this class is to write methods which utilize the Java
     * library's Stack class. You need to utilize this library class in
     * your solution.
     *
     * Methods:
     *  - boolean isPalindrome(string)   - method returns true or false if 'string'
     *                                     is a palindrome
     *  - int     findlargestK(stack, k) - method accepts a stack and returns the
     *                                     the largest index in the stack containing
     *                                     value 'k' (stack index starts at 0)
     *
     */

    static class Stacks {

        /*
         * Method isPalindrome() - This method will return the boolean value 'true'
         * or 'false' on if the passed in parameter string is a palindrome or not.
         *
         * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
         * Moreover, spaces are ignore, so both 'race car' and 'racecar' are plaindromes.
         *
         * The method should utilize the provided Stack class.
         */
        public static boolean isPalindrome(String input) {

            Stack<Character> stack = new Stack<>();
            input = input.toLowerCase().replaceAll("\\s+", "");

            /*
            By using the provided stack, we can push every element of the input string into the stack to reverse it (by popping the stack).
            Then, we can just compare the reversed string to the original string, then return false if they don't match.
             */

            // Push the input string onto the stack
            for (int i = 0; i < input.length(); i++) {
                stack.push(input.charAt(i));
            }

            // Pop each element of the stack and compare it to the input string
            // This is basically just comparing the input string to its reverse
            for (int i = 0; i < input.length(); i++) {
                if (stack.pop() != input.charAt(i)) {
                    // Return false since it cannot be a palindrome if this is ever reached
                    return false;
                }
            }

            // If this is reached, then the input string must be a palindrome
            return true;
        }


        /*
         * Method findLargestk() - This method will return the largest index
         * position in the stack for the value specified by the parameter 'k'.
         *
         * Note that the bottom of the stack is index location 0. So it you push
         * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
         * value '4' for the parameter k, then the largest index position is index
         * location 6.
         *
         * The method should utilize the provided Stack class. This method should NOT
         * destroy the passed in stack, meaning when the method returns, the passed in
         * stack should be identical to when this method is passed. One trick as you
         * pop elements off the passed in stack, place them in a temp stack. Then when
         * completed, place them all back in teh original stack.
         */
        public static int findLargestK(Stack<Integer> stack, int k) {
            Stack<Integer> reverseStack = new Stack<>(); // Initialize a new stack to restore the original stack so it's not destroyed
            int index = -1; // Initialize an index to -1, which will be returned by default if the value is not found
            int currentIndex = stack.size() - 1; // Track the current index of the input stack (-1 since 0-indexed)

            // Iterate through the input stack's elements and find the largest index of the value k
            while (!stack.isEmpty()) {
                int element = stack.pop();

                // If the element is equal to k and the index has not been set yet, set the index
                // We don't need to check if the index is already set because the next index will always be larger
                if (element == k && index == -1) {
                    index = currentIndex;
                }
                // Now, push the element onto the reverse stack and decrement the current index
                reverseStack.push(element);
                currentIndex--;
            }

            // Once the entire input stack has been iterated through, restore the original stack from the reverse stack
            while (!reverseStack.isEmpty()) {
                stack.push(reverseStack.pop());
            }

            // Return the result
            return index;
        }

    }  // End class Stacks



    /*******************************
     *
     * Algorithm Analysis
     *
     * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
     * Modify the return statement to return the correct option number.
     *
     *********************************/

    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i=0; i < n; i++)
            a+= Math.random();

        for (int j=0; j < m; j++)
            b+= Math.random();

        /*
         * Select the correct option listed below:
         *   1. O(N * M) time, O(1) space
         *   2. O(N + M) time, O(N + M) space
         *   3. O(N + M) time, O(1) space
         *   4. O(N * M) time, O(N + M) space
         *
         * TODO: return the answer (which option is correct), in the return statement
        */

        // RETURN THE CORRECT OPTION NUMBER LISTED ABOVE
        return 3;
    }


    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n/2; i <= n; i++)
            for ( j = 2; j <= n; j = j*2 )
                k+= n/2;

        /*
         * Select the correct option listed below:
         *   1. O(N) time
         *   2. O(N log N) time
         *   3. O(N^2) time
         *   4. O(N^2Log n) time
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        // RETURN THE CORRECT OPTION LISTED ABOVE
        return 2;
    }

}

