package control;

import da.StudentDA;
import domain.Student;

/***
 * 
 * This class basically will contains business logic and using data access layer class to access db.
 * So for our case, it just simply use StudentDA class to perform CRUD operation due to not extra business logic needed.
 */
public class MaintainStudent {

    private StudentDA studDA;

    public MaintainStudent() {
        studDA = new StudentDA();
    }
    
    public Student selectRecord(String id) {
        return studDA.getRecord(id);
    }
    
    public void addRecord(Student p) {
        studDA.addRecord(p);
    }
    
    public void updateRecord(Student p) {
        studDA.updateRecord(p);
    }
    
    public void deleteRecord(String id) {
        studDA.deleteRecord(id);
    }

}