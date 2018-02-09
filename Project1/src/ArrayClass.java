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
    * 
    */
   private static final int DEFAULT_CAPACITY = 10;

   /**
    * public static final int FAILED_ACCESS
    * 
    */
   public static final int FAILED_ACCESS = - 999999;

   /**
    * private int[] localArray
    */
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
      if (accessIndex <= ( arraySize - 1 ))
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
}
