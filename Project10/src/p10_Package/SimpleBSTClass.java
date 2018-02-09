
package p10_Package;

/**
 * Simple Binary Search Tree class that can store SimpleStudentClass data
 * 
 * @author Logan Behnke
 *
 */
public class SimpleBSTClass
{
   private SimpleStudentClass BST_Root;
   private SimpleStudentClass foundData;
   private SimpleStudentClass removedData;
   static int INORDER_TRAVERSE = 102;
   static int POSTORDER_TRAVERSE = 103;
   static int PREORDER_TRAVERSE = 101;

   /**
    * Default class constructor, root is initialized
    */
   public SimpleBSTClass()
   {
      BST_Root = null;
   }

   /**
    * 
    * Insert method for BST
    * <p>
    * Note: Overloaded insert uses insert helper method with individual student
    * information data items
    * 
    * @param inName name data to be added to BST
    * @param inStudentID student ID data to be added to BST
    * @param inGender gender data to be added to BST
    * @param inGPA gpa data to be added to BST
    * @return Boolean result of action
    */
   public boolean insert( String inName, int inStudentID, char inGender,
         double inGPA )
   {
      SimpleStudentClass data = new SimpleStudentClass( inName, inStudentID,
            inGender, inGPA );
      if (isEmpty() == false)
      {
         return insertHelper( BST_Root, data );
      }
      BST_Root = new SimpleStudentClass( inName, inStudentID, inGender, inGPA );
      return true;
   }

   /**
    * Insert method for BST
    * <p>
    * Note: Overloaded insert uses insert helper method with a
    * SimpleStudentClass data item
    * 
    * @param newNode SimpleStudentClass object to be added to BST
    * @return Boolean result of action
    */
   public boolean insert( SimpleStudentClass newNode )
   {
      if (isEmpty() == false)
      {
         return insertHelper( BST_Root, newNode );
      }
      BST_Root = newNode;
      return true;
   }

   /**
    * Insert helper method for BST insert action
    * <p>
    * Note: Does not allow duplicate entried (i.e., duplicate student IDs)
    * 
    * @param localRoot SimpleStudentClass tree root reference at the current
    *        recursion level
    * @param newNode SimpleStudentClass object to be added to BST
    * @return Boolean result of insertion action
    */
   private boolean insertHelper( SimpleStudentClass localRoot,
         SimpleStudentClass newNode )
   {
      int inData = newNode.studentID;
      int local = localRoot.studentID;
      if (inData < local)
      {
         if (localRoot.leftChildRef == null)
         {
            localRoot.leftChildRef = newNode;
            return true;
         }
         return insertHelper( localRoot.leftChildRef, newNode );
      }
      if (inData > local)
      {
         if (localRoot.rightChildRef == null)
         {
            localRoot.rightChildRef = newNode;
            return true;
         }
         return insertHelper( localRoot.rightChildRef, newNode );
      }
      return false;
   }

   /**
    * Removes data node from tree using given key
    * <p>
    * Note: uses remove helper method
    * 
    * @param studentID item that is used for search/removal
    * @return GenericData result of remove action
    */
   public SimpleStudentClass removeNode( int studentID )
   {
      if ( ! isEmpty())
      {
         SimpleStudentClass search = search( studentID );
         if (search != null)
         {
            if (search == BST_Root && BST_Root.leftChildRef == null
                  && BST_Root.rightChildRef == null)
            {
               removedData = BST_Root;
               BST_Root = null;
               return removedData;

            }
            removeNodeHelper( BST_Root, studentID );
            return removedData;
         }
      }
      return null;
   }

   /**
    * Remove helper for BST remove action
    * <p>
    * Note: uses removeFromMin method
    * <p>
    * Note: this method sets the class removedData variable so the data can be
    * provided to the user
    * 
    * @param localRoot SimpleStudentClass tree root reference at the current
    *        recursion level
    * @param studentID item that is used for search/removal
    * @return SimpleStudentClass reference result of remove helper action
    */
   private SimpleStudentClass removeNodeHelper( SimpleStudentClass localRoot,
         int studentID )
   {
      if (studentID > localRoot.studentID)
      {
         localRoot.rightChildRef = removeNodeHelper( localRoot.rightChildRef,
               studentID );
      }
      if (studentID < localRoot.studentID)
      {
         localRoot.leftChildRef = removeNodeHelper( localRoot.leftChildRef,
               studentID );
      }
      if (studentID == localRoot.studentID)
      {

         removedData = localRoot;
         if (localRoot.leftChildRef == null)
         {
            if (BST_Root.studentID == studentID)
            {
               BST_Root = localRoot.rightChildRef;
            }
            return localRoot.rightChildRef;
         }
         else if (localRoot.rightChildRef == null)
         {
            if (BST_Root.studentID == studentID)
            {
               BST_Root = localRoot.leftChildRef;
            }
            return localRoot.leftChildRef;
         }
         SimpleStudentClass temp = removeFromMin( localRoot.rightChildRef,
               localRoot.rightChildRef.leftChildRef );
         temp.leftChildRef = localRoot.leftChildRef;
         temp.rightChildRef = localRoot.rightChildRef;
         return temp;
      }
      return localRoot;
   }

   /**
    * Searches tree from given node to minimum value node, stores data value
    * found, and then unlinks the node
    * 
    * @param minParent SimpleStudentClass reference to current node
    * @param minLoc SimpleStudentClass reference to child node to be tested
    * @return SimpleStudentClass reference containing removed node
    */
   private SimpleStudentClass removeFromMin( SimpleStudentClass minParent,
         SimpleStudentClass minLoc )
   {
      if (minLoc != null)
      {
         if (minLoc.leftChildRef != null)
         {
            return removeFromMin( minLoc, minLoc.leftChildRef );
         }
         if (minLoc.rightChildRef != null)
         {
            minParent.leftChildRef = minLoc.rightChildRef;
            return minLoc;
         }
         minParent.leftChildRef = null;
         return minLoc;
      }
      return minParent;
   }

   /**
    * Searches for data in BST given GenericData with necessary key
    * 
    * @param searchData search value
    * @return GenericData reference to found data
    */
   SimpleStudentClass search( int studentID )
   {
      if (searchHelper( BST_Root, studentID ))
      {
         return foundData;
      }
      return null;
   }

   /**
    * Helper method for BST search action
    * <p>
    * Note: this function sets class variable foundData so the data is made
    * available, but then returns a Boolean success
    * 
    * @param localRoot SimpleStudentClass tree root reference at the current
    *        recursion level
    * @param searchData item containing key (studentID)
    * @return Boolean result of search
    */
   private boolean searchHelper( SimpleStudentClass localRoot, int studentID )
   {
      if (localRoot != null)
      {

         if (studentID == localRoot.studentID)
         {
            foundData = localRoot;
            return true;
         }
         if (studentID > localRoot.studentID)
         {
            return searchHelper( localRoot.rightChildRef, studentID );
         }
         if (studentID < localRoot.studentID)
         {
            return searchHelper( localRoot.leftChildRef, studentID );
         }
      }
      return false;
   }

   /**
    * Provides inOrder traversal action
    * 
    * @param localRoot SimpleStudentClass tree root reference at the current
    *        recursion level
    */
   private void displayInOrder( SimpleStudentClass localRoot )
   {
      if (localRoot != null)
      {
         displayInOrder( localRoot.leftChildRef );
         System.out.println( localRoot );
         displayInOrder( localRoot.rightChildRef );
      }
   }

   /**
    * Provides postOrder traversal action
    * 
    * @param localRoot SimpleStudentClass tree root reference at the current
    *        recursion level
    */
   private void displayPostOrder( SimpleStudentClass localRoot )
   {
      if (localRoot != null)
      {
         displayPostOrder( localRoot.leftChildRef );
         displayPostOrder( localRoot.rightChildRef );
         System.out.println( localRoot );
      }
   }

   /**
    * Provides preOrder traversal action
    * 
    * @param localRoot SimpleStudentClass tree root reference at the current
    *        recursion level
    */
   private void displayPreOrder( SimpleStudentClass localRoot )
   {
      if (localRoot != null)
      {
         System.out.println( localRoot );
         displayPostOrder( localRoot.leftChildRef );
         displayPostOrder( localRoot.rightChildRef );
      }
   }

   /**
    * Provides user with three ways to display BST data
    * 
    * @param traverseCode int code for selecting BST traversal method, accepts
    *        PREORDER_TRAVERSE, INORDER_TRAVERSE, POSTORDER_TRAVERSE
    */
   void displayTree( int traverseCode )
   {
      if (traverseCode == INORDER_TRAVERSE)
      {
         displayInOrder( BST_Root );
      }
      else if (traverseCode == POSTORDER_TRAVERSE)
      {
         displayPostOrder( BST_Root );
      }
      else if (traverseCode == PREORDER_TRAVERSE)
      {
         displayPreOrder( BST_Root );
      }
   }

   /**
    * Returns larger of two values
    * <p>
    * Note: used by terrHeightHelper
    * 
    * @param one one of the two values to be tested
    * @param other the other value to be tested
    * @return larger of the two input values
    */
   private int getMax( int one, int other )
   {
      if (one > other)
      {
         return one;
      }
      return other;
   }

   /**
    * Finds height of BST
    * <p>
    * Note: empty tree: -1; root node only: 0; number of edges thereafter
    * 
    * @return maximum number of edges from root node to lowest part of tree
    */
   public int getTreeHeight()
   {
      return treeHeightHelper( BST_Root );
   }

   /**
    * Helper method to find height of BST
    * 
    * @param localRoot SimpleStudentClass used at each level of recursion
    * @return height of tree - maximum number of edges from root node to lowest
    *         part of tree
    */
   private int treeHeightHelper( SimpleStudentClass localRoot )
   {
      if (localRoot != null)
      {
         int right = treeHeightHelper( localRoot.rightChildRef );
         int left = treeHeightHelper( localRoot.leftChildRef );
         return getMax( right, left ) + 1;
      }
      return - 1;
   }

   /**
    * Clears tree of all data
    */
   public void clearTree()
   {
      BST_Root = null;
   }

   /**
    * Tests for empty tree
    * 
    * @return Boolean result of test
    */
   boolean isEmpty()
   {
      if (BST_Root == null)
      {
         return true;
      }
      return false;
   }
}
