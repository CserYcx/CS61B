public class SLList{
    private class IntNode{
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next){
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x){
        first = new IntNode(x,first);
    }

    //Insert an item in a position
    public void insert(int item,int position){
        IntNode temp = first; // here maybe the segment fault (first maybe null!!!)
        while(temp.next!= null){
            position--;
            if(position == 0){
               IntNode node = new IntNode(item,null);
               node.next = temp.next;
               temp.next = node;
            }
            temp = temp.next;
        }
        if(position > 0){
            IntNode node = new IntNode(item,null);
            node.next = temp.next;
        }

    }

    //Reverse a SLList, Existing IntNodes
    //Iterative
    public void reverse(){
        IntNode head, next = IntNodes;
        while(head.next!=null){
            IntNode temp = head.next.next;
            head.next = next;
            next = head.next;
            head = temp;            //Maybe null pointer will here
        }
    }

    //Recursive
    public void reverse(){
        if(IntNodes.next == null){
            return IntNodes;
        }
        else{

        }

    }
}