/**
 * 
 */
package p10_Package;

/**
 * @author Logan Behnke
 *
 */
public class SimpleStudentClass
{
   char gender;
   double gpa;
   String name;
   int studentID;
   protected SimpleStudentClass leftChildRef;
   protected SimpleStudentClass rightChildRef;

   /**
    * Initialization constructor for data
    * 
    * @param inName name of student to be input into object
    * @param inStudentID ID number of student to be input into object
    * @param inGender gender of student to be input into object
    * @param inGPA gpa of student to be input into object
    */
   SimpleStudentClass( String inName, int inStudentID, char inGender,
         double inGPA )
   {
      gpa = inGPA;
      name = inName;
      gender = inGender;
      studentID = inStudentID;
   }

   @Override
   public String toString()
   {
      return name + '/' + studentID + '/' + gender + '/' + gpa;
   }
}
