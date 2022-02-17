package control;

import da.ProgrammeDA;
import domain.Programme;

/**
 * 
 * This class basically will contains business logic and using data access layer class to access db.
 * So for our case, it just simply use ProgrammeDA class to perform CRUD operation due to not extra business logic needed.
 */
public class MaintainProgramme {

    private ProgrammeDA progDA;

    public MaintainProgramme() {
        progDA = new ProgrammeDA();
    }
    
    public Programme selectRecord(String code) {
        return progDA.getRecord(code);
    }
    
    public void addRecord(Programme p) {
        progDA.addRecord(p);
    }
    
    public void updateRecord(Programme p) {
        progDA.updateRecord(p);
    }
    
    public void deleteRecord(String code) {
        progDA.deleteRecord(code);
    }

}