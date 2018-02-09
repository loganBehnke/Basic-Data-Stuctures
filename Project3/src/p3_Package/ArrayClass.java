package p3_Package;

import java.util.Random;

/**
 * Wrapper class for Java array operations, with additional tools
 * <p>
 * Note: Maintains a capacity value for maximum number of items that can be
 * stored, and a size value for the number of valid or viable data items in the
 * array
 * 
 * @author Logan Behnke
 *
 */
public class ArrayClass
{
   private int arrayCapacity;
   private int arraySize;
   private static final int DEFAULT_CAPACITY = 10;
   public static final int FAILED_ACCESS = - 999999;
   private int[] localArray;

   /**
    * Default constructor, initializes array to default capacity (10)
    */
   ArrayClass()
   {
      arraySize = 0;
      arrayCapacity = DEFAULT_CAPACITY;
      localArray = new int[ arrayCapacity ];
   }

   /**
    * Copy constructor, initializes array to size and capacity of copied array,
    * then copies only the elements up to the given size
    * 
    * @param copied ArrayClass object to be copied
    */
   ArrayClass( ArrayClass copied )
   {
      arraySize = copied.getCurrentSize();
      arrayCapacity = copied.getCurrentCapacity();
      localArray = new int[ arrayCapacity ];
      for (int index = 0; index < arraySize; index++)
      {
         localArray[ index ] = copied.accessItemAt( index );
      }

   }

   /**
    * Initializing constructor, initializes array to specified capacity
    * 
    * @param capacity maximum capacity specification for the array
    */
   ArrayClass( int capacity )
   {
      arraySize = 0;
      arrayCapacity = capacity;
      localArray = new int[ arrayCapacity ];
   }

   /**
    * Initializing constructor, initializes array to specified capacity, size to
    * specified value, then fills all elements with specified size value
    * 
    * @param capacity maximum capacity specification for the array
    * @param size sets the number of items to be filled in array, and sets the
    *        size of the ArrayClass object
    * @param fillValue value to be placed in all elements of initialized array
    *        up to the size
    */
   ArrayClass( int capacity, int size, int fillValue )
   {
      arraySize = size;
      arrayCapacity = capacity;
      localArray = new int[ arrayCapacity ];
      for (int index = 0; index < arraySize; index++)
      {
         localArray[ index ] = fillValue;
      }
   }

   /**
    * Accesses item in array at specified index if index within array size
    * bounds
    * 
    * @param accessIndex index of requested element value
    * @return accessed value if successful, FAILED_ACCESS (-999999) if not
    */
   public int accessItemAt( int accessIndex )
   {
      if (accessIndex <= ( arraySize - 1 ) && accessIndex >= 0)
      {
         return localArray[ accessIndex ];
      }
      return FAILED_ACCESS;
   }

   /**
    * Appends item to end of array, if array is not full, e.g., no more values
    * can be added
    * 
    * @param newValue value to be appended to array
    * @return Boolean success if appended, or failure if array was full
    */
   public boolean appendItem( int newValue )
   {
      if (isFull() == false)
      {
         localArray[ arraySize ] = newValue;
         arraySize++;
         return true;
      }
      return false;
   }

   /**
    * Clears array of all valid values by setting array size to zero, values
    * remain in array but are not accessible
    */
   public void clear()
   {
      arraySize = 0;
   }

   /**
    * Description: Gets current capacity of array
    * <P>
    * Note: capacity of array indicates number of values the array can hold
    * 
    * @return capacity of array
    */
   public int getCurrentCapacity()
   {
      return arrayCapacity;
   }

   /**
    * Description: Gets current size of array
    * <P>
    * Note: size of array indicates number of valid or viable values in the
    * array
    * 
    * @return size of array
    */
   public int getCurrentSize()
   {
      return arraySize;
   }

   /**
    * Description: Inserts item to array at specified index if array is not
    * full, e.g., no more values can be added
    * <P>
    * Note: Value is inserted at given index, all data from that index to the
    * end of the array is shifted up by one
    * <P>
    * Note: Value can be inserted after the last valid element but not at any
    * index past that point
    * 
    * @param insertIndex index of element into which value is to be inserted
    * @param newValue value to be inserted into array
    * @return Boolean success if inserted, or failure if array was full
    */
   public boolean insertItemAt( int insertIndex, int newValue )
   {
      if (isFull() == false)
      {
         if (insertIndex >= 0)
         {
            if (insertIndex < ( arraySize + 1 ))
            {
               for (int index = arraySize; index > insertIndex; index--)
               {
                  localArray[ index ] = localArray[ index - 1 ];
               }
               localArray[ insertIndex ] = newValue;
               arraySize++;
               return true;
            }
         }
      }

      return false;
   }

   /**
    * Tests for size of array equal to zero, no valid values stored in array
    * 
    * @return Boolean result of test for empty
    */
   public boolean isEmpty()
   {
      if (arraySize == 0)
      {
         return true;
      }
      return false;
   }

   /**
    * Tests for size of array equal to capacity, no more values can be added
    * 
    * @return Boolean result of test for full
    */
   public boolean isFull()
   {
      if (arraySize == arrayCapacity)
      {
         return true;
      }
      return false;
   }

   /**
    * Description: Removes item from array at specified index if index within
    * array size bounds
    * <P>
    * Note: Each data item from the element immediately above the remove index
    * to the end of the array is moved down by one element
    * 
    * @param removeIndex index of element value to be removed
    * @return removed value if successful, FAILED_ACCESS (-999999) if not
    */
   public int removeItemAt( int removeIndex )
   {
      if (removeIndex >= 0)
      {
         if (removeIndex < arraySize)
         {
            int removedValue = localArray[ removeIndex ];
            for (int iterator = removeIndex; iterator < ( arraySize
                  - 1 ); iterator++)
            {
               localArray[ iterator ] = localArray[ iterator + 1 ];
            }
            arraySize--;
            return removedValue;
         }
      }
      return FAILED_ACCESS;
   }

   /**
    * Description: Resets array capacity, copies current size and current size
    * number of elements
    * <P>
    * Exception: Method will not resize capacity below current array size,
    * returns false if this is attempted, true otherwise
    * 
    * @param newCapacity new capacity to be set; must be larger than current
    *        capacity
    * @return Boolean condition of resize success or failure
    */
   public boolean resize( int newCapacity )
   {
      if (newCapacity > arrayCapacity)
      {
         arrayCapacity = newCapacity;
         ArrayClass copy = new ArrayClass( this );
         localArray = new int[ arrayCapacity ];
         for (int size = 0; size < arraySize; size++)
         {
            localArray[ size ] = copy.accessItemAt( size );
         }
         return true;
      }
      return false;
   }

   /**
    * Loads a specified number of unique random numbers in object
    * <p>
    * Note: This method overwrites all data in the array up to the number of
    * randoms requested
    * <p>
    * Note: If requested number of randoms is greater than the array capacity,
    * the array is resized
    * <p>
    * Note: Size is set to number of random numbers requested
    * <p>
    * Exceptional Condition: If more values are requested than are possible
    * given the range of numbers, method returns false, otherwise, it returns
    * true
    * 
    * @param numRands number of random values requested
    * @param lowLimit lowest value to be generated
    * @param highLimit highest value to be generated
    * @return boolean true if method sucessful; false otherwise
    */
   public boolean loadUniqueRandoms( int numRands, int lowLimit, int highLimit )
   {
      if (lowLimit < highLimit && numRands > 0)
      {
         if (numRands > arrayCapacity)
         {
            resize( numRands );
         }

         int range = highLimit - lowLimit;
         if (numRands <= range)
         {
            arraySize = numRands;
            Random rand = new Random();
            int counter;
            for (counter = 0; counter <= numRands - 1; counter++)
            {
               int randint = rand.nextInt( range ) + lowLimit;
               while (isInArray( randint ) == true)
               {
                  randint = rand.nextInt( range ) + lowLimit;
               }
               localArray[ counter ] = randint;
            }

            return true;
         }
      }
      return false;
   }

   /**
    * Data sorted using merge sort algorithm
    * <p>
    * Note: Call runMergeSortHelper with lower and upper indices of array to be
    * sorted
    */
   public void runMergeSort()
   {
      runMergeSortHelper( 0, arraySize - 1 );
   }

   /**
    * breaks down the array to
    * 
    * @param lowIndex
    * @param highIndex
    */
   private void runMergeSortHelper( int lowIndex, int highIndex )
   {
      if (lowIndex < highIndex)
      {
         int midIndex = ( highIndex + lowIndex ) / 2;
         runMergeSortHelper( lowIndex, midIndex );
         runMergeSortHelper( midIndex + 1, highIndex );
         Merge( lowIndex, midIndex, highIndex );
      }
   }

   /**
    * merges the brokenbown array
    * 
    * @param low low index
    * @param mid the mid index
    * @param high the high index
    */
   private void Merge( int low, int mid, int high )
   {
      ArrayClass temp = new ArrayClass( high - low + 1 );
      int leftSide = low;
      int rightSide = mid + 1;
      while (leftSide <= mid && rightSide <= high)
      {
         if (localArray[ leftSide ] <= localArray[ rightSide ])
         {
            temp.appendItem( localArray[ leftSide ] );
            leftSide++;
         }
         if (localArray[ rightSide ] < localArray[ leftSide ])
         {
            temp.appendItem( localArray[ rightSide ] );
            rightSide++;
         }
      }
      while (leftSide <= mid)
      {
         temp.appendItem( localArray[ leftSide ] );
         leftSide++;
      }
      while (rightSide <= high)
      {
         temp.appendItem( localArray[ rightSide ] );
         rightSide++;
      }
      for (int counter = 0; counter < temp.getCurrentSize(); counter++)
      {
         localArray[ low ] = temp.accessItemAt( counter );
         low++;
      }
   }

   /**
    * Data sorted using quick sort algorithm
    * <p>
    * Note: Call runQuickSortHelper with lower and upper indices of array to be
    * sorted
    */
   public void runQuickSort()
   {
      runQuickSortHelper( 0, arraySize - 1 );
   }

   /**
    * finds the pivot point and breaks down each side of the array
    * @param lowIndex the lowIndex 
    * @param highIndex the highIndex
    */
   private void runQuickSortHelper( int lowIndex, int highIndex )
   {
      if (lowIndex < highIndex)
      {
         int partitionIndex = partition( lowIndex, highIndex );
         runQuickSortHelper( lowIndex, partitionIndex - 1 );
         runQuickSortHelper( partitionIndex + 1, highIndex );
      }
   }

   /**
    * Finds the piviot point and moves it to its positon. 
    * @param lowIndex
    * @param highIndex
    * @return the spot of the partition
    */
   private int partition( int lowIndex, int highIndex )
   {
      int pivot = localArray[highIndex];
      int smallIndex = lowIndex -1;
      for(int counter = lowIndex; counter < highIndex; counter++)
      {
         if(localArray[counter] <= pivot)
         {
            smallIndex++;
            swapElements(smallIndex, counter);
         }
      }
      swapElements(smallIndex+1, highIndex);
      return smallIndex+1;
   }
   
   /**
    * swaps the two elements
    * @param oneIndex one element to be swapped
    * @param otherIndex the other  element to be swapped
    */
   private void swapElements(int oneIndex, int otherIndex)
   {
      int temp = localArray[oneIndex];
      localArray[oneIndex] = localArray[otherIndex];
      localArray[otherIndex] = temp;
   }
   
   /**
    * Tests for value found in object array; returns true if value within array,
    * false otherwise
    * 
    * @param testVal value to be tested
    * @return boolean true if value is found in array, false otherwise
    */
   public boolean isInArray( int testVal )
   {
      int counter;
      for (counter = 0; counter < arraySize - 1; counter++)
      {
         if (accessItemAt( counter ) == testVal)
         {
            return true;
         }
      }
      return false;
   }

}
