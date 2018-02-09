/**
 * Description: Class wrapper for a Java array of generic data (classes), with
 * additional management operations
 * <p>
 * Note: Only works with class that extends Comparable, as shown in class
 * declaration
 * <p>
 * Note: Maintains a capacity value for maximum number of items that can be
 * stored, and a size value for the number of valid or viable data items in the
 * array
 * 
 * @author Logan Behnke
 *
 */
class GenericArrayClass<GenericData extends java.lang.Comparable<GenericData>>
{
   /**
    * private int arrayCapacity
    */
   private int arrayCapacity;

   /**
    * private int arraySize
    */
   private int arraySize;

   /**
    * private static final int DEFAULT_CAPACITY
    */
   private static int DEFAULT_CAPACITY;

   /**
    * private java.lang.Object[] localArray
    */
   private java.lang.Object[] localArray;

   /**
    * Default constructor, initializes array to default capacity (10)
    */
   GenericArrayClass()
   {
      arraySize = 0;
      arrayCapacity = DEFAULT_CAPACITY;
      localArray = new java.lang.Object[ arrayCapacity ];
   }

   /**
    * Copy constructor, initializes array to size and capacity of copied array,
    * then copies only the elements up to the given size
    * 
    * @param copied ArrayClass object to be copied
    */
   GenericArrayClass( GenericArrayClass<GenericData> copied )
   {
      arraySize = copied.getCurrentSize();
      arrayCapacity = copied.getCurrentCapacity();
      localArray = new java.lang.Object[ arrayCapacity ];
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
   GenericArrayClass( int capacity )
   {
      arraySize = 0;
      arrayCapacity = capacity;
      localArray = new java.lang.Object[ arrayCapacity ];
   }

   /**
    * Accesses item in array at specified index if index within array size
    * bounds
    * 
    * @param accessIndex index of requested element value
    * @return accessed value if successful, FAILED_ACCESS (-999999) if not
    */
   @SuppressWarnings( "unchecked" )
   GenericData accessItemAt( int accessIndex )
   {
      if (accessIndex >= 0 && accessIndex <= ( arraySize - 1 ))
      {
         return ( GenericData ) localArray[ accessIndex ];
      }
      return null;
   }

   /**
    * Appends item to end of array, if array is not full, e.g., no more values
    * can be added
    * 
    * @param newValue value to be appended to array
    * @return Boolean success if appended, or failure if array was full
    */
   boolean appendItem( GenericData newValue )
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
   void clear()
   {
      arraySize = 0;
   }

   /**
    * Description: Gets current capacity of array
    * <p>
    * Note: capacity of array indicates number of values the array can hold
    * 
    * @return capacity of array
    */
   int getCurrentCapacity()
   {
      return arrayCapacity;
   }

   /**
    * Description: Gets current size of array
    * <p>
    * Note: size of array indicates number of valid or viable values in the
    * array
    * 
    * @return size of array
    */
   int getCurrentSize()
   {
      return arraySize;
   }

   /**
    * Description: Inserts item to array at specified index if array is not
    * full, e.g., no more values can be added
    * <p>
    * Note: Value is inserted at given index, all data from that index to the
    * end of the array is shifted up by one
    * 
    * @param insertIndex index of element into which value is to be inserted
    * @param newValue value to be inserted into array
    * @return Boolean success if inserted, or failure if array was full
    */
   boolean insertItemAt( int insertIndex, GenericData newValue )
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
   boolean isEmpty()
   {
      return (arraySize == 0);
   }

   /**
    * Tests for size of array equal to capacity, no more values can be added
    * 
    * @return Boolean result of test for full
    */
   boolean isFull()
   {
      return (arraySize == arrayCapacity);
   }
   /**
    * Description: Removes item from array at specified index if index within
    * array size bounds
    * <p>
    * Note: Each data item from the element immediately above the remove index
    * to the end of the array is moved down by one element
    * 
    * @param removeIndex index of element value to be removed
    * @return removed value if successful, FAILED_ACCESS (-999999) if not
    */
   @SuppressWarnings( "unchecked" )
   GenericData removeItemAt( int removeIndex )
   {
      if (removeIndex >= 0)
      {
         if (removeIndex < arraySize)
         {
            GenericData removedValue = ( GenericData ) localArray[ removeIndex ];
            for (int iterator = removeIndex; iterator < ( arraySize
                  - 1 ); iterator++)
            {
               localArray[ iterator ] = localArray[ iterator + 1 ];
            }
            arraySize--;
            return removedValue;
         }
      }
      return null;
   }

   /**
    * Description: Resets array capacity, copies current size and current size
    * number of elements
    * <p>
    * Exception: Method will not resize capacity below current array size,
    * returns false if this is attempted, true otherwise
    * 
    * @param newCapacity new capacity to be set; must be larger than current
    *        capacity
    * @return Boolean condition of resize success or failure
    */
   boolean resize( int newCapacity )
   {
      if (newCapacity > arrayCapacity)
      {
         arrayCapacity = newCapacity;
         GenericArrayClass<GenericData> copy = new GenericArrayClass<GenericData>(
               this );
         localArray = new java.lang.Object[ arrayCapacity ];
         for (int size = 0; size < arraySize; size++)
         {
            localArray[ size ] = copy.accessItemAt( size );
         }
         return true;
      }
      return false;
   }

   /**
    * Description: Sorts elements using the bubble sort algorithm
    * <p>
    * Note: The data is sorted using the compareTo method of the given data set;
    * the compareTo method decides the key and the order resulting
    */
   void runBubbleSort()
   {
      for (int indexer = 0; indexer < arraySize - 1; indexer++)
      {
         for (int index = 0; index < arraySize - indexer - 1; index++)
         {
            if (accessItemAt( index )
                  .compareTo( accessItemAt( index + 1 ) ) > 0)
            {
               swapElements( index, index + 1 );
            }
         }
      }
   }

   /**
    * Description: Sorts elements using the insertion sort algorithm
    * <p>
    * Note: The data is sorted using the compareTo method of the given data set;
    * the compareTo method decides the key and the order resulting
    */
   @SuppressWarnings( "unchecked" )
   void runInsertionSort()
   {
      for (int indexer = 0; indexer < arraySize; indexer++)
      {
         Object value = accessItemAt( indexer );
         int subindex = indexer - 1;
         while (subindex >= 0 && ( accessItemAt( subindex )
               .compareTo( ( GenericData ) value ) > 0 ))
         {
            localArray[ subindex + 1 ] = localArray[ subindex ];
            subindex--;
         }
         localArray[ subindex + 1 ] = value;
      }
   }

   /**
    * Description: Sorts elements using the selection sort algorithm
    * <p>
    * Note: The data is sorted using the compareTo method of the given data set;
    * the compareTo method decides the key and the order resulting
    */
   void runSelectionSort()
   {
      for (int outerIndex = 0; outerIndex < arraySize - 1; outerIndex++)
      {
         int lowestIndex = outerIndex;
         for (int innerIndex = lowestIndex
               + 1; innerIndex < arraySize; innerIndex++)
         {
            if (accessItemAt( innerIndex )
                  .compareTo( accessItemAt( lowestIndex ) ) < 0)
            {
               lowestIndex = innerIndex;
            }
         }
         swapElements( outerIndex, lowestIndex );
      }
   }

   /**
    * Swaps one element in the local array at a given index with another element
    * in the array at the other given element
    * 
    * @param oneIndex index of one of two elements to be swapped
    * @param otherIndex index of second of two elements to be swapped
    */
   private void swapElements( int oneIndex, int otherIndex )
   {
      Object temp = localArray[ oneIndex ];
      localArray[ oneIndex ] = localArray[ otherIndex ];
      localArray[ otherIndex ] = temp;

   }
}
