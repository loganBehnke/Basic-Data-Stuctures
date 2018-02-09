package p5_Package;

/**
 * a utility class that makes a iterator, stack, or a queue using an array
 * 
 * @author Logan Behnke
 *
 */
public class UtilityClass
{
   private int capacity;
   public static int FAILED_ACCESS = - 999999;
   public static int ITER_TYPE = 101;
   private int iteratorIndex;
   public static int QUEUE_TYPE = 102;
   private int queueHeadIndex;
   private int queueTailIndex;
   private int size;
   public static int STACK_TYPE = 103;
   private int stackTopIndex;
   private int[] storage;
   private int utilityType;

   UtilityClass( int capacitySetting, int utilityTypeSetting )
   {
      capacity = capacitySetting;
      size = 0;
      storage = new int[ capacity ];
      utilityType = utilityTypeSetting;
      if (utilityType == ITER_TYPE)
      {
         iteratorIndex = 0;
      }
      else if (utilityType == QUEUE_TYPE)
      {
         queueHeadIndex = 0;
         queueTailIndex = 0;
      }
      else if (utilityType == STACK_TYPE)
      {
         stackTopIndex = -1;
      }
   }

   /**
    * Checks for resize and resizes to twice the current capacity if needed
    * <p>
    * Note: Returns true if resize is necessary and is conducted; returns false
    * if no action is taken
    * 
    * @return success of operation
    */
   private boolean checkForReSize()
   {
      if (size == capacity)
      {
         if (utilityType == QUEUE_TYPE)
         {
            int[] temp = new int[ capacity ];
            int tempIndex = 0;
            int tempSize = size;
            while (tempIndex < tempSize)
            {
               temp[ tempIndex ] = qDequeue();
               tempIndex++;
            }
            capacity *= 2;
            storage = new int[ capacity ];
            queueHeadIndex = 0;
            queueTailIndex = 0;
            for (int index = 0; index < tempSize; index++)
            {
               qEnqueue( temp[ index ] );
            }

            return true;
         }
         int[] temp = new int[ capacity ];
         for (int index = 0; index < capacity; index++)
         {
            temp[ index ] = storage[ index ];
         }
         capacity *= 2;
         storage = new int[ capacity ];
         for (int index = 0; index < size; index++)
         {
            storage[ index ] = temp[ index ];
         }
         return true;
      }
      return false;
   }

   /**
    * Adds (appends) item to end of iterator list
    * 
    * @param newValue Value to be added to list
    * @return Boolean success of operation
    */
   boolean iAdd( int newValue )
   {
      if (utilityType == ITER_TYPE)
      {
         checkForReSize();
         storage[ size ] = newValue;
         size++;
         return true;
      }
      return false;
   }

   /**
    * Gets value at current location of iterator
    * 
    * @return Value if successful, FAILED_ACCESS if not
    */
   int iGetAtCurrent()
   {
      if (utilityType == ITER_TYPE)
      {
         if ( ! isEmpty())
         {
            return storage[ iteratorIndex ];
         }
      }
      return FAILED_ACCESS;
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
         if (iteratorIndex < size)
         {
            iteratorIndex++;
            return true;
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
         if (iteratorIndex > 0)
         {
            iteratorIndex--;
            return true;
         }
      }
      return false;
   }

   /**
    * Removes and returns value from list at current iterator position
    * <p>
    * Note: If index is greater than zero, sets iterator index to previous item
    * after removal action
    * 
    * @return Value returned if successful, FAILED_ACCESS if not
    */
   int iRemoveAtCurrent()
   {
      if (utilityType == ITER_TYPE)
      {
         if ( ! isEmpty())
         {
            int temp = storage[ iteratorIndex ];
            for (int index = iteratorIndex; index < size - 1; index++)
            {
               storage[ index ] = storage[ index + 1 ];
            }
            size--;
            iMovePrevious();
            return temp;
         }
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
      return ( size == 0 );
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
         iteratorIndex = 0;
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
         iteratorIndex = size - 1;
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
            int temp = storage[ queueHeadIndex ];
            updateQueueHeadIndex();
            size--;
            return temp;
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
         checkForReSize();
         storage[ queueTailIndex ] = newValue;
         updateQueueTailIndex();
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
         if ( ! isEmpty())
         {
            return storage[ queueHeadIndex ];
         }
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
            return storage[ stackTopIndex ];
         }
      }
      return FAILED_ACCESS;
   }

   /**
    * Removes and returns data from top of stack if stack mode engaged
    * 
    * @return value if successful, FAILED_ACCESS if not
    */
   public int sPop()
   {
      if (utilityType == STACK_TYPE)
      {
         if ( ! isEmpty())
         {
            int temp = storage[ stackTopIndex ];
            stackTopIndex--;
            size--;
            return temp;
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
         checkForReSize();
         storage[ stackTopIndex + 1 ] = newValue;
         size++;
         stackTopIndex++;
         return true;
      }
      return false;
   }

   /**
    * Updates queue head index to wrap around array as needed
    */
   private void updateQueueHeadIndex()
   {
      if (queueHeadIndex > capacity - 1)
      {
         queueHeadIndex = 0;
      }
      queueHeadIndex++;
   }

   /**
    * Updates queue tail index to wrap around array as needed
    */
   private void updateQueueTailIndex()
   {
      if (queueTailIndex >= capacity - 1)
      {

         queueTailIndex = 0;
      }
      else
      {
         queueTailIndex++;
      }
   }
}
