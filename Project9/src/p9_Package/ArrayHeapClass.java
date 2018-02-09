package p9_Package;

import java.util.Random;

/**
 * Array-based Heap Class - Configured as MIN heap
 * <p>
 * Provides display during data addition and removal
 * 
 * @author Logan Behnke
 *
 */
public class ArrayHeapClass
{

   /**
    * Management data for array
    */
   private int arrayCapacity;

   /**
    * Management data for array
    */
   private int arraySize;

   /**
    * Initial array capacity
    */
   int DEFAULT_ARRAY_CAPACITY = 10;

   /**
    * Display flag can be set to observe bubble up and trickle down operations
    */
   private boolean displayFlag;

   /**
    * Array for heap
    */
   private String[] heapArray;

   /**
    * Constant for setting priority in OS operation
    */
   int HIGH_PRIORITY = 999;

   /**
    * Constant for setting cycle time in OS operation
    */
   int HIGH_PROCESS_TIME = 25;

   /**
    * Constant for setting priority in OS operation
    */
   int LOW_PRIORITY = 100;

   /**
    * Constant for setting cycle time in OS operation
    */
   int LOW_PROCESS_TIME = 5;

   /**
    * Default constructor sets up array management conditions and default
    * display flag setting
    */
   public ArrayHeapClass()
   {
      arraySize = 0;
      arrayCapacity = DEFAULT_ARRAY_CAPACITY;
      heapArray = new String[ arrayCapacity ];
      displayFlag = false;
   }

   /**
    * Accepts new OS operation in string form and adds it to heap
    * <p>
    * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data
    * addition
    * 
    * @param newItem String OS operation to be added
    */
   public void addItem( String newItem )
   {
      checkForArrayResize();
      heapArray[ arraySize ] = newItem;
      bubbleUpArrayHeap( arraySize );
      arraySize++;
      if (displayFlag)
      {
         System.out.println();
         System.out.println( "Adding new Process: " + newItem );
      }
   }

   /**
    * Recursive operation to reset data in the correct order for the min heap
    * after new data addition
    * 
    * @param currentIndex index of current item being assessed, and moved up as
    *        required
    */
   private void bubbleUpArrayHeap( int currentIndex )
   {
      if (currentIndex != 0)
      {
         int parIndex = ( currentIndex - 1 ) / 2;
         int childPriorty = getPriority( heapArray[ currentIndex ] );
         int parPriorty = getPriority( heapArray[ parIndex ] );
         if (childPriorty < parPriorty)
         {
            String temp = heapArray[ parIndex ];
            heapArray[ parIndex ] = heapArray[ currentIndex ];
            heapArray[ currentIndex ] = temp;
            if (displayFlag)
            {
               System.out.println( "  -Bubble Up:" );
               System.out
                     .println( "   - Swapping parent:  " + heapArray[ parIndex ]
                           + " with child: " + heapArray[ currentIndex ] );
            }
         }
         bubbleUpArrayHeap( parIndex );
      }
   }

   /**
    * Automatic resize operation used prior to any new data addition in the heap
    * <p>
    * Tests for full heap array, and resizes to twice the current capacity as
    * required
    */
   private void checkForArrayResize()
   {
      if (arraySize + 1 == arrayCapacity)
      {
         int index;
         arrayCapacity *= 2;
         String[] temp = new String[ arrayCapacity ];
         for (index = 0; index < arraySize; index++)
         {
            temp[ index ] = heapArray[ index ];
         }
         heapArray = temp;
      }
   }

   /**
    * Creates a randomly generated OS Run operation with the form:
    * "P(RunMMM)NN;" shows a text-based Operating System operation where MMM is
    * the priority and NN is the cycle time of the operation
    * <p>
    * Note: the method generates unique operations that do not repeat the same
    * priority; uses method IsInHeap to accomplish this
    * 
    * @return String OS run operation
    */
   public String createHeapDataItem()
   {
      int priority = getRandBetween( LOW_PRIORITY, HIGH_PRIORITY );
      if (arraySize > 1)
      {
         while (isInHeap( priority ))
         {
            priority = getRandBetween( LOW_PRIORITY, HIGH_PRIORITY );
         }
      }
      int time = getRandBetween( LOW_PROCESS_TIME, HIGH_PROCESS_TIME );
      if (time < 10)
      {
         return "P(Run" + priority + ")0" + time + ";";
      }
      return "P(Run" + priority + ")" + time + ";";
   }

   /**
    * Utility method that captures the priority value from the OS operation
    * string
    * 
    * @param value String input to be parsed for priority value
    * @return priority value
    */
   int getPriority( String value )
   {
      String temp = "" + value.charAt( 5 ) + value.charAt( 6 )
            + value.charAt( 7 );
      return Integer.parseInt( temp );
   }

   /**
    * Random generation of values between two numbers, inclusive
    * 
    * @param lowInclusive lowest value of result
    * @param highInclusive highest value of result
    * @return randomly generated value between the two given values, inclusive
    */
   private int getRandBetween( int lowInclusive, int highInclusive )
   {
      int range = highInclusive - lowInclusive;
      Random temp = new Random();
      return temp.nextInt( range ) + lowInclusive;
   }

   /**
    * Recursive helper method for isInHeap
    * 
    * @param priorityValue value to search for in heap array
    * @param currentIndex index at current level to manage recursion
    * @return Boolean result, depending on the search
    */
   private boolean isInArrayHeap( int priorityValue, int currentIndex )
   {
      String data = heapArray[ currentIndex ];
      int value = getPriority( data );
      if (value == priorityValue)
      {
         return true;
      }
      if (currentIndex + 1 < arraySize)
      {
         return isInArrayHeap( priorityValue, currentIndex + 1 );
      }
      return false;
   }

   /**
    * Support method for createHeapDataItem
    * 
    * @param priorityValue value to search for in heap array to verify no
    *        repeated priority values
    * @return Boolean result, depending on the search
    */
   private boolean isInHeap( int priorityValue )
   {
      return isInArrayHeap( priorityValue, 0 );
   }

   /**
    * Removes OS operation from top of min heap, thus being the operation with
    * the lowest priority value
    * <p>
    * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data
    * removal
    * 
    * @return OS operation as string
    */
   String removeItem()
   {
      if (arraySize > 0)
      {
         String data = heapArray[ 0 ];
         heapArray[ 0 ] = heapArray[ arraySize - 1 ];
         arraySize--;
         System.out.println();
         System.out.println( "Removing process: " + data );
         trickleDownArrayHeap( 0 );
         return data;
      }
      return null;
   }

   /**
    * Utility method to set the display flag for displaying internal operations
    * of the heap bubble and trickle operations
    * 
    * @param setState flag used to set the state to display, or not
    */
   void setDisplayFlag( boolean setState )
   {
      displayFlag = setState;
   }

   /**
    * Recursive operation to reset data in the correct order for the min heap
    * after data removal
    * 
    * @param currentIndex index of current item being assessed, and moved down
    *        as required
    */
   private void trickleDownArrayHeap( int currentIndex )
   {
      int left = currentIndex * 2 + 1;
      int right = currentIndex * 2 + 2;
      int currentPriority = getPriority( heapArray[ currentIndex ] );

      if (right < arraySize)
      {
         int leftPriority = getPriority( heapArray[ left ] );
         int rightPriority = getPriority( heapArray[ right ] );
         if (leftPriority < rightPriority)
         {
            if (leftPriority < currentPriority)
            {
               String temp = heapArray[ left ];
               heapArray[ left ] = heapArray[ currentIndex ];
               heapArray[ currentIndex ] = temp;
               if (displayFlag)
               {
                  System.out.println( "  - Trickle down:" );
                  System.out.println(
                        "   - Swapping parent: " + heapArray[ currentIndex ]
                              + " with left child: " + heapArray[ left ] );
               }
               trickleDownArrayHeap( left );
            }
         }
         else if (rightPriority < currentPriority)
         {
            String temp = heapArray[ right ];
            heapArray[ right ] = heapArray[ currentIndex ];
            heapArray[ currentIndex ] = temp;
            if (displayFlag)
            {
               System.out.println( "  - Trickle down:" );
               System.out.println(
                     "   - Swapping parent: " + heapArray[ currentIndex ]
                           + " with right child: " + heapArray[ right ] );
            }
            trickleDownArrayHeap( right );
         }
      }
      else if (left < arraySize)
      {
         int leftPriority = getPriority( heapArray[ left ] );
         if (leftPriority < currentPriority)
         {
            String temp = heapArray[ left ];
            heapArray[ left ] = heapArray[ currentIndex ];
            heapArray[ currentIndex ] = temp;
            if (displayFlag)
            {
               System.out.println( "  - Trickle down:" );
               System.out.println(
                     "   - Swapping parent: " + heapArray[ currentIndex ]
                           + " with left child: " + heapArray[ left ] );
            }
            trickleDownArrayHeap( left );
         }
      }
   }

}
