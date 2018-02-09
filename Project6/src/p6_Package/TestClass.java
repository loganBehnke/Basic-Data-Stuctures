package p6_Package;

public class TestClass
{
   public static void main(String[] args)
   {

      UtilityClass iterator = new UtilityClass(101);
      iterator.iAdd(1);
      iterator.iAdd(2);
      iterator.iAdd(3);

      System.out.println("Iterator Test: ");
      System.out.println(iterator.iGetAtCurrent());
      iterator.iMoveNext();
      System.out.println(iterator.iGetAtCurrent());
      iterator.iMoveNext();
      System.out.println(iterator.iGetAtCurrent());
      System.out.println();

      iterator.iMovePrevious();
      System.out.println(iterator.iGetAtCurrent());
      iterator.iMovePrevious();
      System.out.println(iterator.iGetAtCurrent());
      System.out.println();


      UtilityClass que = new UtilityClass(102);
      que.qEnqueue(23);
      que.qEnqueue(17);
      que.qEnqueue(33);

      System.out.println("Queue Test: ");
      System.out.println(que.qDequeue());
      System.out.println(que.qDequeue());
      System.out.println(que.qDequeue());
      System.out.println();

      UtilityClass stack = new UtilityClass(103);
      stack.sPush(11);
      stack.sPush(22);
      stack.sPush(33);

      System.out.println("Stack Test: ");
      System.out.println(stack.sPeek());
      System.out.println(stack.sPop());
      System.out.println(stack.sPop());
      System.out.println(stack.sPop());
      System.out.println();
   }
}
