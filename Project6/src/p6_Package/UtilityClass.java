package p6_Package;

/**
 * UtilityClass for a LinkedList that acts like a queue, itorator, or stack
 * 
 * @author Logan Behnke
 *
 */
public class UtilityClass
{
   private NodeClass current;
   static int FAILED_ACCESS = - 999999;
   private NodeClass head;
   static int ITER_TYPE = 101;
   static int QUEUE_TYPE = 102;
   private int size;
   static int STACK_TYPE = 103;
   private NodeClass tail;
   private int utilityType;

   /**
    * Initialization constructor
    * <p>
    * Selectable utilities that persist for the lifetime of the object
    * 
    * @param utilityTypeSetting user selectable between ITER_TYPE, QUEUE_TYPE,
    *        STACK_TYPE
    */
   public UtilityClass( int utilityTypeSetting )
   {
      utilityType = utilityTypeSetting;
   }

   /**
    * 
    * Adds (appends) item to end of iterator list
    * <p>
    * Note: Unless the first item is being appended to the list, the current
    * cursor is not changed by this action
    * 
    * @param newValue Value to be added to list
    * @return Boolean success of operation
    */
   boolean iAdd( int newValue )
   {
      if (utilityType == ITER_TYPE)
      {
         NodeClass newNode = new NodeClass( newValue );
         if (isEmpty())
         {
            tail = newNode;
            head = tail;
            current = head;
         }
         else
         {
            tail.next = newNode;
            tail = newNode;
         }
         size++;
         return true;
      }
      return false;
   }

   /**
    * Gets value at current location of iterator
    * 
    * @return Gets value at current location of iterator
    */
   int iGetAtCurrent()
   {
      if (utilityType == ITER_TYPE)
      {
         if ( ! isEmpty())
         {
            return current.data;
         }
      }
      return FAILED_ACCESS;
   }

   /**
    * Inserts item at current node of iterator list
    * <p>
    * Note: current cursor points at inserted item at completion of this action
    * 
    * @param newValue Value to be added to list
    * @return Boolean success of operation
    */
   boolean iInsert( int newValue )
   {
      if (utilityType == ITER_TYPE)
      {
         NodeClass newNode = new NodeClass( newValue );
         newNode.next = current.next;
         current.next = newNode;
         current = newNode;
         if (isEmpty())
         {
            head = current;
         }
         size++;
         return true;
      }
      return false;
   }

   /**
    * Move iterator cursor to next item if not currently at end
    * 
    * @return Boolean success of operation
    */
   boolean iMoveNext()
   {
      if (utilityType == ITER_TYPE)
      {
         if ( ! isEmpty())
         {
            if (current != tail)
            {
               current = current.next;
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Move iterator cursor to previous item if not currently at beginning
    * 
    * @return Boolean success of operation
    */
   boolean iMovePrevious()
   {
      if (utilityType == ITER_TYPE)
      {
         if (current != head)
         {
            int index;
            NodeClass temp = head;
            for (index = 0; index < size; index++)
            {
               if (temp.next == current)
               {
                  current = temp;
                  return true;
               }
               temp = temp.next;
            }
         }
      }
      return false;
   }

   /**
    * Removes and returns value from list at current iterator position
    * <p>
    * Note: If current iterator is not at the beginning of the list, sets
    * iterator index to previous item after removal action
    * 
    * @return Value returned if successful, FAILED_ACCESS if not
    */
   int iRemoveAtCurrent()
   {
      if (utilityType == ITER_TYPE)
      {
         NodeClass currentNode = current;
         NodeClass nextNode = current.next;
         iMovePrevious();
         current.next = nextNode;
         return currentNode.data;
      }
      return FAILED_ACCESS;
   }

   /**
    * Reports empty for any of the utilities
    * 
    * @return Boolean evidence of empty list
    */
   boolean isEmpty()
   {
      if (size == 0)
      {
         return true;
      }
      return false;
   }

   /**
    * Sets iterator to beginning of list
    * 
    * @return Boolean evidence that iterator is at beginning of list
    */
   boolean iSetToBeginning()
   {
      if (utilityType == ITER_TYPE)
      {
         current = head;
         return true;
      }
      return false;
   }

   /**
    * Sets iterator to end of list
    * 
    * @return Boolean evidence that iterator is at end of list
    */
   boolean iSetToEnd()
   {
      if (utilityType == ITER_TYPE)
      {
         current = tail;
         return true;
      }
      return false;
   }

   /**
    * Removes and returns value from front of queue if queue mode is engaged
    * 
    * @return Value if successful, FAILED_ACCESS if not
    */
   int qDequeue()
   {
      if (utilityType == QUEUE_TYPE)
      {
         if ( ! isEmpty())
         {
            NodeClass temp = head;
            head = head.next;
            size--;
            return temp.data;
         }
      }
      return FAILED_ACCESS;
   }

   /**
    * Appends value to end of queue if queue mode is engaged
    * 
    * @param newValue Value to be enqueued
    * @return Boolean success of operation
    */
   boolean qEnqueue( int newValue )
   {
      if (utilityType == QUEUE_TYPE)
      {
         NodeClass newNode = new NodeClass( newValue );
         if (isEmpty())
         {
            tail = newNode;
            head = newNode;
         }
         else
         {
            tail.next = newNode;
            tail = newNode;
         }
         size++;
         return true;
      }
      return false;
   }

   /**
    * Provides peek at front of queue if queue mode engaged
    * 
    * @return Value if successful, FAILED_ACCESS if not
    */
   int qPeek()
   {
      if (utilityType == QUEUE_TYPE)
      {
         return head.data;
      }
      return FAILED_ACCESS;
   }

   /**
    * provides peek at top of stack if stack mode engaged
    * 
    * @return value if successful, FAILED_ACCESS if not
    */
   int sPeek()
   {
      if (utilityType == STACK_TYPE)
      {
         if ( ! isEmpty())
         {
            return head.data;
         }
      }
      return FAILED_ACCESS;
   }

   /**
    * Removes and returns data from top of stack if stack mode engaged
    * 
    * @return value if successful, FAILED_ACCESS if not
    */
   int sPop()
   {
      if (utilityType == STACK_TYPE)
      {
         if ( ! isEmpty())
         {
            NodeClass temp = head;
            head = head.next;
            size--;
            return temp.data;
         }
      }
      return FAILED_ACCESS;
   }

   /**
    * Pushes value onto stack if stack mode engaged
    * 
    * @param newValue Value to be pushed onto stack
    * @return Boolean evidence of success
    */
   boolean sPush( int newValue )
   {
      if (utilityType == STACK_TYPE)
      {
         NodeClass newNode = new NodeClass( newValue );
         newNode.next = head;
         head = newNode;
         size++;
         return true;
      }
      return false;
   }
}
